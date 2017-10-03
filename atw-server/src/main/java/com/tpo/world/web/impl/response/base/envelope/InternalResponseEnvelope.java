package com.tpo.world.web.impl.response.base.envelope;

import com.tpo.world.web.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class InternalResponseEnvelope implements ResponseEnvelope {

    private Object error;
    private Object errors;
    private Map<String, Object> data;

    public InternalResponseEnvelope() {
        data = new HashMap<>();
    }

    @Override
    public Object getErrors() {
        return errors;
    }

    @Override
    public void setErrors(Object errors) {
        this.errors = errors;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public ResponseEnvelope addData(String aName, Object aData) {
        data.put(aName, aData);
        return this;
    }

    @Override
    public <T> T getData(String aName, Class<T> aClass) {
        return (T) data.get(aName);
    }

    @Override
    public Object getError() {
        return error;
    }

    @Override
    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public void addDataMessage(String aMessage) {
        addData(Constants.MESSAGE, aMessage);
    }

    @Override
    public String getDataMessage() {
        return getData(Constants.MESSAGE, String.class);
    }

}
