package de.randsbergerhof.carta.service;


import de.randsbergerhof.carta.dto.DBFile;
import de.randsbergerhof.carta.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.io.IOException;


@Service
public class DBFileService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public void storeFile(MultipartFile file, int day) throws IOException {
        String filename = "tageskarte_" + getDay(day);

        DBFile dbfile = new DBFile(filename, file.getBytes(), day);

        dbFileRepository.save(dbfile);
    }

    public void deleteFileByDay(int day) {
        List<DBFile> all = dbFileRepository.findAll();

        for (DBFile file : all) {
            if (file.getDay() == day) {
                dbFileRepository.delete(file);
            }
        }
    }

    public DBFile getFileByDay(int day) {
        List<DBFile> all = dbFileRepository.findAll();

        for (DBFile file : all) {
            if (file.getDay() == day) {
                return file;
            }
        }
        return null;
    }

    private String getDay(int dayOfWeek) {

        switch (dayOfWeek) {
            case 1:
                return "sonntag";
            case 2:
                return "montag";
            case 3:
                return "dienstag";
            case 4:
                return "mittwoch";
            case 5:
                return "donnerstag";
            case 6:
                return "freitag";
            case 7:
                return "samstag";
        }
        return null;
    }

}
