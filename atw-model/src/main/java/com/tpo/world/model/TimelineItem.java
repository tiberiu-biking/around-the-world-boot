package com.tpo.world.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TimelineItem {

    private Integer year;
    private List<Marker> markers;

    public TimelineItem(Integer year) {
        setYear(year);
        markers = new ArrayList<>();
    }

    public void addMarkers(List<Marker> aMarkers) {
        markers.addAll(aMarkers);
    }

    public void addMarker(Marker markerEntity) {
        markers.add(markerEntity);
    }

}
