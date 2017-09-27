package com.tpo.world.core.util;

import com.google.gson.GsonBuilder;

public final class GsonHelper {

    private static final String DATE_PATTERN_MASTER_PAM = "yyyy-MM-dd";

    private static GsonBuilder gsonBuilder = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat(DATE_PATTERN_MASTER_PAM);

    private GsonHelper() {
    }

    public static String toGson(Object aObject) {
        return gsonBuilder.create().toJson(aObject);
    }

    public static <T> T fromGson(String aGson, Class<T> aClass) {
        return gsonBuilder.create().fromJson(aGson, aClass);
    }
}
