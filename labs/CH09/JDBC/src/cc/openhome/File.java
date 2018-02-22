package cc.openhome;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class File implements Serializable {
    private Long id;
    private String filename;
    private Long savedTime;
    private byte[] bytes;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public Long getSavedTime() {
        return savedTime;
    }
    public void setSavedTime(Long savedTime) {
        this.savedTime = savedTime;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public LocalDateTime getLocalDateTime() {
        return Instant.ofEpochMilli(savedTime)
                .atZone(ZoneId.of("Asia/Taipei"))
                .toLocalDateTime();
    }
}
