package com.tpfinalprogramacion2.models.saves;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Save implements Serializable {

    private final String saveName;
    private final String saveDate;

    public Save(String saveName) {
        this.saveName = saveName;
        this.saveDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return saveName + " - " + saveDate;
    }
}
