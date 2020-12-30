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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;


@Controller
public class IndexController {

    @Autowired
    private DBFileService fileService;

    @RequestMapping("/")
    public ResponseEntity<Resource> returnIndex() {

        DBFile dbFile = fileService.getFileByDay(getCurrentDay());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFilename()  + ".pdf" + "\"")
                .body(new ByteArrayResource(dbFile.getData()));

    }

    private int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}

