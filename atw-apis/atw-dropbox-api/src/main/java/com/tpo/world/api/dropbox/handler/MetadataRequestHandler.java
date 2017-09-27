package com.tpo.world.api.dropbox.handler;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestUtil.ResponseHandler;
import com.dropbox.core.http.HttpRequestor.Response;
import com.tpo.world.api.dropbox.gson.MediaInfoMetadata;
import com.tpo.world.domain.util.GsonHelper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;

public class MetadataRequestHandler extends ResponseHandler<DbxEntry> {

    private MediaInfoMetadata metadata;

    @Override
    public DbxEntry handle(Response aResponse) throws DbxException {

        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(aResponse.body, writer, null);
            String theString = writer.toString();
            metadata = GsonHelper.fromGson(theString, MediaInfoMetadata.class);

        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
        return null;
    }

    public MediaInfoMetadata getMetadata() {
        return metadata;
    }

}
