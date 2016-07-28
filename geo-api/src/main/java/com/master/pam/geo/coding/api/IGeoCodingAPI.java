package com.master.pam.geo.coding.api;

import master.pam.crosscutting.dto.api.IAddressDto;
import master.pam.crosscutting.geo.GeoPoint;

import java.math.BigDecimal;

public interface IGeoCodingAPI {
    public IAddressDto getAddress(BigDecimal aLatitude, BigDecimal aLongitude);

    public IAddressDto getAddress(GeoPoint aGeoPoint);
}
