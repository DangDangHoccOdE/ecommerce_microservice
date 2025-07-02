package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.VerificationTokenDto;
import com.microservice.userservice.exception.wrapper.VerificationTokenNotFoundException;
import com.microservice.userservice.helper.VerificationTokenMappingHelper;
import com.microservice.userservice.repository.VerificationTokenRepository;
import com.microservice.userservice.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public List<VerificationTokenDto> findAll() {
        log.info("*** VerificationTokenDto List, service; fetch all verificationTokens *");
        return this.verificationTokenRepository.findAll()
                .stream()
                .map(VerificationTokenMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public VerificationTokenDto findById(final Integer verificationTokenId) {
        log.info("*** VerificationTokenDto, service; fetch verificationToken by ids *");
        return this.verificationTokenRepository.findById(verificationTokenId)
                .map(VerificationTokenMappingHelper::map)
                .orElseThrow(() -> new VerificationTokenNotFoundException(String
                        .format("#### VerificationToken with id: %d not found! ####", verificationTokenId)));
    }

    @Override
    public VerificationTokenDto save(final VerificationTokenDto verificationTokenDto) {
        log.info("*** VerificationTokenDto, service; save verificationToken *");
        return VerificationTokenMappingHelper.map(this.verificationTokenRepository
                .save(VerificationTokenMappingHelper.map(verificationTokenDto)));
    }

    @Override
    public VerificationTokenDto update(final VerificationTokenDto verificationTokenDto) {
        log.info("*** VerificationTokenDto, service; update verificationToken *");
        return VerificationTokenMappingHelper.map(this.verificationTokenRepository
                .save(VerificationTokenMappingHelper.map(verificationTokenDto)));
    }

    @Override
    public void deleteById(final Integer verificationTokenId) {
        log.info("*** Void, service; delete verificationToken by id *");
        this.verificationTokenRepository.deleteById(verificationTokenId);
    }

    @Override
    public VerificationTokenDto update(final Integer verificationTokenId, final VerificationTokenDto verificationTokenDto) {
        log.info("*** VerificationTokenDto, service; update verificationToken with verificationTokenId *");
        return VerificationTokenMappingHelper.map(this.verificationTokenRepository.save(
                VerificationTokenMappingHelper.map(this.findById(verificationTokenId))));
    }
}
