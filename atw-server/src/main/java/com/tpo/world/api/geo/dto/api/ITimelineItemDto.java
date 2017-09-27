package com.tpo.world.api.geo.dto.api;


import com.tpo.world.persistence.entity.MarkerEntity;

import java.util.List;

public interface ITimelineItemDto {

    Integer getYear();

    List<MarkerEntity> getMarkers();
}
