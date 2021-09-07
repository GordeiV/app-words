package domain;

import javax.ejb.Local;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    public static final String PATTERN = "dd.MM.yyyy HH:mm:ss";

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(PATTERN));
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.format(DateTimeFormatter.ofPattern(PATTERN));
    }
}
