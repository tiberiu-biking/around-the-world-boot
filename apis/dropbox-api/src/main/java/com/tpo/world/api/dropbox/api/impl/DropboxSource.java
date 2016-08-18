package com.tpo.world.api.dropbox.api.impl;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxRequestUtil;
import com.master.pam.geo.coding.api.IGeoCodingAPI;
import com.tpo.world.api.dropbox.api.IDropboxSource;
import com.tpo.world.api.dropbox.gson.MediaInfoMetadata;
import com.tpo.world.api.dropbox.handler.MetadataRequestHandler;
import com.tpo.world.domain.entity.MarkerEntity;
import master.pam.crosscutting.date.DateFormatUtil;
import master.pam.crosscutting.dto.impl.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class DropboxSource implements IDropboxSource {

    private final static Logger logger = LoggerFactory.getLogger(DropboxSource.class);

    private IGeoCodingAPI geoCodingAPI;

    public DropboxSource(IGeoCodingAPI geoCodingAPI) {
        this.geoCodingAPI = geoCodingAPI;
    }

    @Override
    public List<MarkerEntity> getMarkers(String aToken, List<String> aFiles, long aUserId) throws DbxException {

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());

        DbxClient client = new DbxClient(config, aToken);

        logger.debug("Linked account: " + client.getAccountInfo().displayName);

        List<MarkerEntity> resultList = new ArrayList<>();

        for (String dropboxFile : aFiles) {
            MediaInfoMetadata metadata = getMediaMetadata(client, dropboxFile);

            if (metadata.getPhoto_info() != null) {
                List<Double> latLongList = metadata.getPhoto_info().getLat_long();

                Date dropboxDate = DateFormatUtil.parseDropboxDate(metadata.getPhoto_info().getTime_taken());

                MarkerEntity markerEntity = new MarkerEntity();
                markerEntity.setLatitude(BigDecimal.valueOf(latLongList.get(0)));
                markerEntity.setLongitude(BigDecimal.valueOf(latLongList.get(1)));
                markerEntity.setDate(Calendar.getInstance().getTime());
                markerEntity.setExternalId(dropboxFile);

                AddressDto address = geoCodingAPI.getAddress(markerEntity.getLatitude(), markerEntity.getLongitude());
                markerEntity.setName(address.getShortAddress());
                markerEntity.setUserId(aUserId);
                markerEntity.setNote("Imported from Dropbox picture");
                markerEntity.setDate(dropboxDate);
                resultList.add(markerEntity);
            }
        }

        return resultList;
    }

    private MediaInfoMetadata getMediaMetadata(DbxClient aClient, String aDropboxFile) throws DbxException {
        String apiPath = "1/metadata/dropbox/" + aDropboxFile;

        String[] params = {"list", "true", "file_limit", "25000", "include_media_info", "true"};
        MetadataRequestHandler handler = new MetadataRequestHandler();
        DbxRequestUtil.doGet(aClient.getRequestConfig(), aClient.getAccessToken(), "api.dropbox.com", apiPath, params, null, handler);
        return handler.getMetadata();
    }
}
