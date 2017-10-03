package com.tpo.world.web.impl.response.base.envelope;

public interface ResponseEnvelope {

    /**
     * Get result error, meaning there was an error during the request.
     */
    Object getError();

    /**
     * Get result errors. This is a message as part of the result.
     */
    Object getErrors();

    /**
     * Get result data.
     */
    Object getData();

    /**
     * Set result errors. This is a message as part of the result.
     */
    void setErrors(Object aErrors);

    /**
     * Set the request error, meaning there was an error during the request.
     */
    void setError(Object aError);

    /**
     * Add data to the result.
     */
    ResponseEnvelope addData(String aName, Object aData);

    <T> T getData(String aName, Class<T> aClass);

    void addDataMessage(String string);

    String getDataMessage();

}
