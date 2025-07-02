package com.microservice.userservice.service;

import com.microservice.userservice.dto.VerificationTokenDto;

import java.util.List;

public interface VerificationTokenService {
    List<VerificationTokenDto> findAll();
    VerificationTokenDto findById(final Integer verificationTokenId);
    VerificationTokenDto save(final VerificationTokenDto verificationTokenDto);
    VerificationTokenDto update(final VerificationTokenDto verificationTokenDto);
    VerificationTokenDto update(final Integer verificationTokenId, final VerificationTokenDto verificationTokenDto);
    void deleteById(final Integer verificationTokenId);
}
