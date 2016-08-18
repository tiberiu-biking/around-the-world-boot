package com.tpo.world.geo.coding.api;

import com.tpo.world.model.address.Address;
import com.tpo.world.model.geo.GeoPoint;

import java.math.BigDecimal;

public interface IGeoCodingAPI {

    Address getAddress(BigDecimal aLatitude, BigDecimal aLongitude);

    Address getAddress(GeoPoint aGeoPoint);
}
