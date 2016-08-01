package master.pam.crud.impl.dao;

import master.pam.crosscutting.dto.api.IMarkerDto;
import master.pam.crud.api.dao.IMarkerDao;
import master.pam.crud.impl.dao.base.BaseDao;
import master.pam.crud.impl.entity.business.MarkerEntity;
import master.pam.crud.impl.filter.FilterBuilder;
import master.pam.crud.impl.filter.FilterBuilderConstants;
import master.pam.crud.impl.iface.ICrud;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class MarkerDao extends BaseDao implements IMarkerDao {

    private final static Logger logger = LoggerFactory.getLogger(MarkerDao.class);

    public MarkerDao(ICrud crud) {
        super(crud);
    }

    @Override
    public List<IMarkerDto> getMarkers(Long aUserId, Long aMarkerId) {
        logger.trace("getMarkers(userId = " + aUserId + ", markerId = " + aMarkerId + ")");

        FilterBuilder filter = new FilterBuilder().buildFilter(FilterBuilderConstants.USER_ID, aUserId);

        if (aMarkerId != null)
            filter.buildFilter(FilterBuilderConstants.MARKER_ID, aMarkerId).getFilter();

        return crud.select(IMarkerDto.class, filter.getFilter());
    }

    @Override
    public IMarkerDto update(IMarkerDto aDto) {
        logger.trace("updateMarker()");

        MarkerEntity entity = crud.find(MarkerEntity.class, aDto.getId());
        try {
            BeanUtils.copyProperties(entity, aDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        crud.update(entity);
        return entity;
    }

    @Override
    public void delete(long aId) {
        logger.trace("deleteMarker()");

        crud.delete(MarkerEntity.class, aId);
    }

    @Override
    public IMarkerDto create(IMarkerDto aDto) {
        logger.trace("createMarker()");

        MarkerEntity entity = new MarkerEntity();
        try {
            BeanUtils.copyProperties(entity, aDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        crud.insert(entity);
        return entity;
    }

    @Override
    public IMarkerDto getByExternalId(String aExternalId, Long aUserId) {
        logger.trace("getByExternalId()");

        Map<String, Object> filter = new FilterBuilder().buildFilter(FilterBuilderConstants.EXTERNAL_ID, aExternalId)
                .buildFilter(FilterBuilderConstants.USER_ID, aUserId)
                .getFilter();
        List<IMarkerDto> resultList = crud.select(IMarkerDto.class, filter);
        if (resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

}
