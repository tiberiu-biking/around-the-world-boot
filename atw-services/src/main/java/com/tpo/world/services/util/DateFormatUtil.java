package com.tpo.world.services.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Slf4j
public final class DateFormatUtil {

    private DateFormatUtil() {
    }

    public static Integer getYear(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return cal.get(Calendar.YEAR);
    }

    public static Date parseDropboxDate(String aDropboxDate) {
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        try {
            return df.parse(aDropboxDate);
        } catch (ParseException e) {
            log.error("Cannot parse Dropbox date: " + aDropboxDate);
            return Calendar.getInstance().getTime();
        }
    }
}
