package master.pam.foursquare.api.impl;

import com.tpo.world.model.entity.MarkerEntity;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompleteUser;
import master.pam.foursquare.api.constants.FoursquareConstants;
import master.pam.foursquare.api.util.FoursquareUtil;

import java.util.ArrayList;
import java.util.List;

public class FoursquareSource implements IFoursquareSource {

    private static FoursquareApi instance;

    @Override
    public List<MarkerEntity> getMarkers(String aCode, long aUserId) throws FoursquareApiException {
        ArrayList<MarkerEntity> foursquareMarkers = new ArrayList<>();

        getAPI().authenticateCode(aCode);

        Result<CheckinGroup> checkinsList = getAPI().usersCheckins(null, 99999, null, null, null);

        Checkin[] items = checkinsList.getResult().getItems();
        for (Checkin checkin : items)
            foursquareMarkers.add(FoursquareUtil.checkinToMarker(checkin, aUserId));

        return foursquareMarkers;

    }

    private FoursquareApi getAPI() {
        if (instance == null)
            instance = new FoursquareApi(FoursquareConstants.CLIENT_ID, FoursquareConstants.CLIENT_SECRET, FoursquareConstants.REDIRECT_URL);
        return instance;
    }

    @Override
    public String getUserId(String aCode) throws FoursquareApiException {
        getAPI().authenticateCode(aCode);

        CompleteUser user = getAPI().user(null).getResult();

        return user.getId();
    }
}