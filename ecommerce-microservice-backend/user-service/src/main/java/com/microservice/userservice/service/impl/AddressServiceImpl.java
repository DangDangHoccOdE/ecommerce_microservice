package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.AddressDto;
import com.microservice.userservice.helper.AddressMappingHelper;
import com.microservice.userservice.repository.AddressRepository;
import com.microservice.userservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> findAll() {
        log.info("**** AddressDto List, service; fetch all address *");
        return this.addressRepository.findAll()
                .stream()
                .map(AddressMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public AddressDto findById(Integer addressId) {
        log.info("*** AddressDto, service: fetch address by id *");
        return this.addressRepository.findById(addressId)
                .map(AddressMappingHelper::map)
                .orElseThrow(() -> new AddressN)
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        return null;
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        return null;
    }

    @Override
    public void deleteById(Integer addressId) {

    }

    @Override
    public AddressDto update(Integer addressId, AddressDto addressDto) {
        return null;
    }
}
