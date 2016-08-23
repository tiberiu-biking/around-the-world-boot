package com.tpo.world.model.timeline;

import com.tpo.world.model.entity.MarkerEntity;

import java.util.ArrayList;
import java.util.List;

public class TimelineItem {

    private Integer year;

    private List<MarkerEntity> markers = new ArrayList<>();

    public TimelineItem(Integer aYear) {
        setYear(aYear);
    }

    public void addMarkers(List<MarkerEntity> aMarkers) {
        getMarkers().addAll(aMarkers);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<MarkerEntity> getMarkers() {
        return markers;
    }

    public void setMarkers(List<MarkerEntity> aMarkers) {
        this.markers = aMarkers;
    }

    public void addMarker(MarkerEntity markerEntity) {
        markers.add(markerEntity);
    }

}
