package com.tpo.world.api.geo.coding.api;

import com.tpo.world.api.geo.dto.impl.AddressDto;
import com.tpo.world.api.geo.geo.GeoPoint;

import java.math.BigDecimal;

public interface IGeoCodingAPI {
    AddressDto getAddress(BigDecimal aLatitude, BigDecimal aLongitude);

    AddressDto getAddress(GeoPoint aGeoPoint);
}
