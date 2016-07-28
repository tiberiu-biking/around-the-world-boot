package master.pam.crosscutting.dto.api;

public interface IAddressDto {

    public String getFormattedAddress();

    public String getCountry();

    public String getCity();

    public String getShortAddress();
}
