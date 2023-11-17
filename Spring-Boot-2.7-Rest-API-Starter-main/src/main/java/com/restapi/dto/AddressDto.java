package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDto {
    public static Address mapToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setContactNumber(addressRequest.getContactNumber());
        address.setZipcode(addressRequest.getZipcode());
        return address;
    }

    public AddressResponse mapToAddressResponse(List<Address> addressList) {
        return new AddressResponse(addressList);
    }
}
