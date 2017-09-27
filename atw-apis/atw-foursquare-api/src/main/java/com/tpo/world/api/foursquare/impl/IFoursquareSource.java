package com.tpo.world.api.foursquare.impl;

import com.tpo.world.domain.entity.MarkerEntity;
import fi.foyt.foursquare.api.FoursquareApiException;

import java.util.List;

public interface IFoursquareSource {

    List<MarkerEntity> getMarkers(String aCode, long aUserId) throws FoursquareApiException;

    String getUserId(String aCode) throws FoursquareApiException;

}
