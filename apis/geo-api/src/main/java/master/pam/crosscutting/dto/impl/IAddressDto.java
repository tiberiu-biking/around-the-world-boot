package master.pam.crosscutting.dto.impl;

public interface IAddressDto {

    String getFormattedAddress();

    String getCountry();

    String getCity();

    String getShortAddress();
}
