package master.pam.crosscutting.dto.api;

import master.pam.crosscutting.dto.base.IIdDto;

import java.math.BigDecimal;
import java.util.Date;

public interface IMarkerDto extends IIdDto {

    public Date getDate();

    public String getNote();

    public BigDecimal getLongitude();

    public BigDecimal getLatitude();

    public String getName();

    public Long getUserId();

    public String getExternalId();

    public Integer getRating();

}
