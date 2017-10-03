package com.tpo.world.services.util;

import com.tpo.world.model.geo.GeoPoint;
import com.tpo.world.persistence.entity.MarkerEntity;

import java.util.List;

public final class Geo {

    private Geo() {
    }

    public static GeoPoint calculateCenterPoint(List<MarkerEntity> markers) {
        double minLatitude = 9999;
        double maxLatitude = -9999;
        double minLong = 9999;
        double maxLong = -9999;

        for (MarkerEntity marker : markers) {
            if (marker.getLatitude().doubleValue() > maxLatitude)
                maxLatitude = marker.getLatitude().doubleValue();
            if (marker.getLatitude().doubleValue() < minLatitude)
                minLatitude = marker.getLatitude().doubleValue();

            if (marker.getLongitude().doubleValue() > maxLong)
                maxLong = marker.getLongitude().doubleValue();
            if (marker.getLongitude().doubleValue() < minLong)
                minLong = marker.getLongitude().doubleValue();
        }

        return new GeoPoint((maxLatitude + minLatitude) / 2, (maxLong + minLong) / 2);
    }

}
