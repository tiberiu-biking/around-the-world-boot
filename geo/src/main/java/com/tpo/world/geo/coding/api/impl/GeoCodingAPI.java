package com.tpo.world.geo.coding.api.impl;

import com.google.code.geocoder.AdvancedGeoCoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import com.tpo.world.geo.coding.api.IGeoCodingAPI;
import com.tpo.world.model.address.Address;
import com.tpo.world.model.geo.GeoPoint;

import java.math.BigDecimal;
import java.util.List;

public class GeoCodingAPI implements IGeoCodingAPI {

    public Address getAddress(BigDecimal aLatitude, BigDecimal aLongitude) {
        return getAddress(new GeoPoint(aLatitude, aLongitude));
    }

    public Address getAddress(GeoPoint aGeoPoint) {

        final AdvancedGeoCoder geocoder = new AdvancedGeoCoder();

        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(
                new LatLng(aGeoPoint.getLatitude(), aGeoPoint.getLongitude()))
                .setLanguage("en")
                .getGeocoderRequest();

        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        List<GeocoderResult> results = geocoderResponse.getResults();

        if (results.size() > 0) {
            GeocoderResult firstResult = results.get(0);

            Address resultAddress = new Address(firstResult.getFormattedAddress());

            List<GeocoderAddressComponent> addressComponents = firstResult.getAddressComponents();

            for (GeocoderAddressComponent comp : addressComponents) {
                List<String> types = comp.getTypes();

                for (String type : types) {
                    GeocoderResultType geoCoderType = GeocoderResultType.fromValue(type);
                    if (geoCoderType.equals(GeocoderResultType.COUNTRY)) {
                        resultAddress.setCountry(comp.getLongName());
                    } else if (geoCoderType.equals(GeocoderResultType.LOCALITY)) {
                        resultAddress.setCity(comp.getLongName());
                    }
                }
            }

            return resultAddress;
        } else
            return null;
    }
}
