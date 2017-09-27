package com.tpo.world.api.dropbox.api;

import com.dropbox.core.DbxException;
import com.tpo.world.domain.entity.MarkerEntity;

import java.util.List;

public interface IDropboxSource {

    List<MarkerEntity> getMarkers(String aToken, List<String> aFiles, long aUserId) throws DbxException;

}
