package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.CredentialDto;
import com.microservice.userservice.exception.wrapper.CredentialNotFoundException;
import com.microservice.userservice.exception.wrapper.UserObjectNotFoundException;
import com.microservice.userservice.helper.CredentialMappingHelper;
import com.microservice.userservice.repository.CredentialRepository;
import com.microservice.userservice.service.CredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository credentialRepository;

    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        super();
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<CredentialDto> findAll() {
        log.info("*** CredentialDto List, service; fetch all credentials *");
        return this.credentialRepository.findAll()
                .stream()
                .map(CredentialMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CredentialDto save(CredentialDto credentialDto) {
        log.info("*** CredentialDto, service; save credential *");
        return CredentialMappingHelper.map(
                this.credentialRepository.save(CredentialMappingHelper.map(credentialDto))
        );
    }

    @Override
    public CredentialDto findById(Integer id) {
        log.info("*** CredentialDto, service; fetch credential by ids *");
        return this.credentialRepository.findById(id)
                .map(CredentialMappingHelper::map)
                .orElseThrow(() -> new CredentialNotFoundException(
                        String.format("### Credential with id: %d not found", id)
                ));
    }

    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        log.info("*** CredentialDto, service; update credential *");
        return CredentialMappingHelper.map(this.credentialRepository.save(CredentialMappingHelper.map(credentialDto)));
    }

    @Override
    public void deleteById(Integer credentialId) {
        log.info("*** Void, service; delete credential by id *");
        this.credentialRepository.deleteById(credentialId);
    }

    @Override
    public CredentialDto update(Integer credentialId, CredentialDto credentialDto) {
        log.info("*** CredentialDto, service; update credential by credential id *");
        return CredentialMappingHelper.map(this.credentialRepository.save(
                CredentialMappingHelper.map(credentialDto)
        ));
    }

    @Override
    public CredentialDto findByUsername(final String username) {
        return CredentialMappingHelper.map(this.credentialRepository.findByUsername(username)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("#### Credential with username: %s not found! ####", username))));
    }
}
