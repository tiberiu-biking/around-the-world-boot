package com.master.pam.geo.coding.api;

import master.pam.crosscutting.dto.impl.AddressDto;
import master.pam.crosscutting.geo.GeoPoint;

import java.math.BigDecimal;

public interface IGeoCodingAPI {
    AddressDto getAddress(BigDecimal aLatitude, BigDecimal aLongitude);

    AddressDto getAddress(GeoPoint aGeoPoint);
}
