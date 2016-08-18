package master.pam.foursquare.api.impl;

import com.tpo.world.model.entity.MarkerEntity;
import fi.foyt.foursquare.api.FoursquareApiException;

import java.util.List;

public interface IFoursquareSource {

    List<MarkerEntity> getMarkers(String aCode, long aUserId) throws FoursquareApiException;

    String getUserId(String aCode) throws FoursquareApiException;

}
