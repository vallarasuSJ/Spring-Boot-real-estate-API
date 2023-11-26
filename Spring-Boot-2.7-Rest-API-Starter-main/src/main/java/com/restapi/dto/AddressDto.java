package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.repository.AddressRepository;
import com.restapi.request.AddressRequest;
import com.restapi.request.PropertyRequest;
import com.restapi.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDto {
    public static Address mapToAddress(PropertyRequest propertyRequest) {
        Address address = new Address();
        address.setAddress(propertyRequest.getAddress());
        address.setCity(propertyRequest.getCity());
        address.setZipcode(propertyRequest.getZipcode());
        return address;
    }



    public AddressResponse mapToAddressResponse(List<Address> addressList) {
        return new AddressResponse(addressList);
    }
}
