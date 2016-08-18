package master.pam.crosscutting.dto.api;


import com.tpo.world.domain.entity.MarkerEntity;

import java.util.List;

public interface ITimelineItemDto {

    Integer getYear();

    List<MarkerEntity> getMarkers();
}
