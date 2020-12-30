package de.randsbergerhof.carta.controller;

import de.randsbergerhof.carta.dto.DBFile;
import de.randsbergerhof.carta.service.DBFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class AuthController {

    @Autowired private DBFileService fileService;

    private final String pw = "carta";

    @GetMapping("/auth")
    public String auth () {
        return "auth";
    }

    @PostMapping("/auth")
    public RedirectView authenticate (@RequestParam("input") String password, RedirectAttributes redirectAttributes) {

        if (password.equals(pw)) {
            redirectAttributes.addFlashAttribute("auth", true);
            return new RedirectView("/admin", true);
        } else
            redirectAttributes.addFlashAttribute("fail", true);
            return new RedirectView("/auth", true);
    }

    @GetMapping("/admin")
    public String admin (Model model) {

        if (model.asMap().get("auth") == null) {
            return "error";
        }

        if ((Boolean)model.asMap().get("auth")) {
            return "admin";
        } else {
            return "error";
        }
    }

    @RequestMapping(params = "upload", method = RequestMethod.POST, value = "/admin")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("day") int day, Model model) throws IOException {

        fileService.deleteFileByDay(day);
        fileService.storeFile(file, day);

        model.addAttribute("auth", true);
        return "admin";

    }

    @RequestMapping(params = "test", method = RequestMethod.POST, value = "/admin")
    public ResponseEntity<Resource> downloadFile(@RequestParam("day") int day, Model model) throws IOException {

        DBFile dbFile = fileService.getFileByDay(day);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFilename()  + ".pdf" + "\"")
                .body(new ByteArrayResource(dbFile.getData()));

    }

}
