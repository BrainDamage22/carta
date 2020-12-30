package de.randsbergerhof.carta.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "files")
public class DBFile {

    @Id
    @GeneratedValue
    private long id;

    private String filename;

    private byte[] data;

    private int day;

    public DBFile() {
    }

    public DBFile(String filename, byte[] data, int day) {
        this.filename = filename;
        this.data = data;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
