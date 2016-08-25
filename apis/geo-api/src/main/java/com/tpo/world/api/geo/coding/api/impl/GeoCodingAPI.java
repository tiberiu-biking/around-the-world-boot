package com.tpo.world.api.geo.coding.api.impl;

import com.google.code.geocoder.AdvancedGeoCoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import com.tpo.world.api.geo.coding.api.IGeoCodingAPI;
import com.tpo.world.api.geo.dto.impl.AddressDto;
import com.tpo.world.api.geo.geo.GeoPoint;

import java.math.BigDecimal;
import java.util.List;

public class GeoCodingAPI implements IGeoCodingAPI {

    public AddressDto getAddress(BigDecimal aLatitude, BigDecimal aLongitude) {
        return getAddress(new GeoPoint(aLatitude, aLongitude));
    }

    public AddressDto getAddress(GeoPoint aGeoPoint) {

        final AdvancedGeoCoder geocoder = new AdvancedGeoCoder();

        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(
                new LatLng(aGeoPoint.getLatitude(), aGeoPoint.getLongitude()))
                .setLanguage("en")
                .getGeocoderRequest();

        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        List<GeocoderResult> results = geocoderResponse.getResults();

        if (results.size() > 0) {
            GeocoderResult firstResult = results.get(0);

            AddressDto resultAddressDto = new AddressDto(firstResult.getFormattedAddress());

            List<GeocoderAddressComponent> addressComponents = firstResult.getAddressComponents();

            for (GeocoderAddressComponent comp : addressComponents) {
                List<String> types = comp.getTypes();

                for (String type : types) {
                    GeocoderResultType geoCoderType = GeocoderResultType.fromValue(type);
                    if (geoCoderType.equals(GeocoderResultType.COUNTRY))
                        resultAddressDto.setCountry(comp.getLongName());
                    else if (geoCoderType.equals(GeocoderResultType.LOCALITY))
                        resultAddressDto.setCity(comp.getLongName());
                }
            }

            return resultAddressDto;
        } else
            return null;
    }
}
