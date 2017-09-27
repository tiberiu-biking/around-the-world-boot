package com.tpo.world.core.util;

import java.util.Calendar;
import java.util.Date;

public final class DateFormatUtil {

    private DateFormatUtil() {
    }

    public static Integer getYear(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return cal.get(Calendar.YEAR);
    }
}
