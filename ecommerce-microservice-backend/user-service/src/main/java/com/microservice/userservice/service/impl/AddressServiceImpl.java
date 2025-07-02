package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.AddressDto;
import com.microservice.userservice.exception.wrapper.AddressNotFoundException;
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
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format("### Address with id: %d not found! ###", addressId)
                ));
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        log.info("*** AddressDto, service: save address *");
        return AddressMappingHelper.map(this.addressRepository.save(AddressMappingHelper.map(addressDto)));
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        log.info("**** AddressDto, service; update address *");
        return AddressMappingHelper.map(this.addressRepository.save(AddressMappingHelper.map(addressDto)));
    }

    @Override
    public void deleteById(Integer addressId) {
        log.info("*** Void, service; delete address by id *");
        this.addressRepository.deleteById(addressId);
    }

    @Override
    public AddressDto update(Integer addressId, AddressDto addressDto) {
        log.info("*** AddressDto, service; update address with addressId *");
        return AddressMappingHelper.map(this.addressRepository.save(
                AddressMappingHelper.map(this.findById(addressId))
        ));
    }
}
