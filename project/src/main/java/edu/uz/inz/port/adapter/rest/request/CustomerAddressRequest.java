package edu.uz.inz.port.adapter.rest.request;

import edu.uz.inz.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerAddressRequest {

    private final String street;
    private final String city;
    private final String postCode;

    public Address toAddress() {
        return new Address(street, city, postCode);
    }
}
