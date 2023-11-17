package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressDto addressDto;


    public AddressResponse findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressDto.mapToAddressResponse(addressList);
    }

    public AddressResponse create(AddressRequest addressRequest) {
        Address address=AddressDto.mapToAddress(addressRequest);
        addressRepository.save(address);
        return findAll();
    }
}
