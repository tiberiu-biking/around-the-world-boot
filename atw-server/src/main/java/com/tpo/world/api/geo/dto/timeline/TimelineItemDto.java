package com.tpo.world.api.geo.dto.timeline;

import com.tpo.world.api.geo.dto.api.ITimelineItemDto;
import com.tpo.world.domain.entity.MarkerEntity;

import java.util.ArrayList;
import java.util.List;

public class TimelineItemDto implements ITimelineItemDto {

    private Integer year;

    private List<MarkerEntity> markers = new ArrayList<>();


    public TimelineItemDto(Integer aYear) {
        setYear(aYear);
    }

    public void addMarkers(List<MarkerEntity> aMarkers) {
        getMarkers().addAll(aMarkers);
    }

    @Override
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
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
