package com.tpo.world.web.api;

import com.tpo.world.web.domain.ServerAction;

import java.util.List;

public interface ServerRequest {

    ServerAction getAction();

    void setAction(ServerAction aActionName);

    /**
     * Adds a new parameter to the request with the specified value.
     */
    void addField(String aParamName, Object aParamValue);

    void addLong(String aParamName, Long aParamValue);

    Object getObject(String aParamName);

    String getString(String aParamName);

    Long getLong(String aParamName);

    <T> T getDto(Class<T> aClass);

    <T> T get(String aParamName, Class<T> aResultClass);

    <T> List<T> getDtoList(Class<T> aClass);
}
