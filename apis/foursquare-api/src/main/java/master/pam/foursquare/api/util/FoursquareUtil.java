package master.pam.foursquare.api.util;

import com.tpo.world.domain.entity.MarkerEntity;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CompactVenue;

import java.math.BigDecimal;
import java.util.Date;

public class FoursquareUtil {

    public static MarkerEntity checkinToMarker(Checkin aCheckin, Long aUserId) {
        CompactVenue venue = aCheckin.getVenue();

        MarkerEntity markerEntity = new MarkerEntity();

        markerEntity.setLatitude(BigDecimal.valueOf(venue.getLocation().getLat()));
        markerEntity.setLongitude(BigDecimal.valueOf(venue.getLocation().getLng()));
        markerEntity.setDate(new Date(aCheckin.getCreatedAt() * 1000));
        markerEntity.setExternalId(aCheckin.getId());
        markerEntity.setName(venue.getName());
        markerEntity.setUserId(aUserId);
        markerEntity.setNote("Imported from Foursquare");

        return markerEntity;
    }

}
