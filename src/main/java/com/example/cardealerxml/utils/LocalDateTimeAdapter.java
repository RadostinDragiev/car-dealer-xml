package com.example.cardealerxml.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public String marshal(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.toString();
    }

    @Override
    public LocalDateTime unmarshal(String string) {
        return LocalDateTime.parse(string);
    }
}
