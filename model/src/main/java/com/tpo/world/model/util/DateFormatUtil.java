package com.tpo.world.model.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    public static final String DATE_PATTERN_MASTER_PAM = "yyyy-MM-dd";

    private final static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

    public static String formatDate(Date aDate) {
        return DateFormatUtils.format(aDate, DATE_PATTERN_MASTER_PAM);
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
            logger.error("Cannot parse Dropbox date: " + aDropboxDate);
            return Calendar.getInstance().getTime();
        }
    }
}
