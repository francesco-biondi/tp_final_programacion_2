package com.tpfinalprogramacion2.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Save implements Serializable {

    private final String saveName;
    private String saveDate;

    public Save(String saveName) {
        this.saveName = saveName;
        this.saveDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getSaveName() {
        return saveName;
    }

    @Override
    public String toString() {
        return saveName + " - " + saveDate;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
