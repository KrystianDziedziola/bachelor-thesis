package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.Address;
import lombok.Data;

@Data
public class AddressReadModel {

    private final String street;
    private final String city;
    private final String postCode;

    public static AddressReadModel from(final Address address) {
        return new AddressReadModel(address.getStreet(), address.getCity(), address.getPostCode());
    }
}
