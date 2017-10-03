package com.tpo.world.web.impl.request;

import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.domain.ServerAction;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class InternalServerRequest implements ServerRequest {

    private ServerAction action;
    private Map<String, Object> fields;

    @Override
    public void setAction(ServerAction aAction) {
        action = aAction;
        fields = new HashMap<>();
    }

    @Override
    public ServerAction getAction() {
        return action;
    }

    @Override
    public void addField(String aParamName, Object aValue) {
        fields.put(aParamName, aValue);
    }

    @Override
    public Long getLong(String aParamName) {
        return get(aParamName, Long.class);
    }

    @Override
    public Object getObject(String aParamName) {
        return get(aParamName, Object.class);
    }

    @Override
    public String getString(String aParamName) {
        return get(aParamName, String.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String aParamName, Class<T> aResultClass) {
        Object result = fields.get(aParamName);
        try {
            return (T) result;
        } catch (ClassCastException e) {
            log.error("Cannot convert field to " + aResultClass.getCanonicalName());
            // TODO
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T getDto(Class<T> aClass) {
        return get(Constants.DTO, aClass);
    }

    @Override
    public <T> List<T> getDtoList(Class<T> aClass) {
        return get(Constants.DTO_LIST, List.class);
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    @Override
    public void addLong(String aParamName, Long aParamValue) {
        addField(aParamName, aParamValue);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InternalServerRequest [action=");
        builder.append(action);
        builder.append(", fields=");
        builder.append(fields);
        builder.append("]");
        return builder.toString();
    }

}
