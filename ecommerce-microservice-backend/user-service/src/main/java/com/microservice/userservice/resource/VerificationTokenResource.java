package com.microservice.userservice.resource;

import com.microservice.userservice.dto.VerificationTokenDto;
import com.microservice.userservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.userservice.service.VerificationTokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = {"/api/verificationTokens"})
public class VerificationTokenResource {
    private final VerificationTokenService verificationTokenService;

    public VerificationTokenResource(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<VerificationTokenDto>> findAll(){
        log.info("*** VerificationTokenDto List, controller; fetch all verification tokens *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(verificationTokenService.findAll()));
    }

    @GetMapping("/{verificationTokenId}")
    public ResponseEntity<VerificationTokenDto> findById(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "Input must not blank")
            @Valid final String verificationTokenId
    ){
        log.info("*** VerificationTokenDto, resource; fetch verificationToken by id *");
        return ResponseEntity.ok((this.verificationTokenService.findById(Integer.parseInt(verificationTokenId.strip()))));
    }

    @PostMapping
    public ResponseEntity<VerificationTokenDto> save(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final VerificationTokenDto verificationTokenDto
    ){
        log.info("*** VerificationTokenDto, resource; save verificationToken *");
        return ResponseEntity.ok(this.verificationTokenService.save(verificationTokenDto));
    }

    @PutMapping
    public ResponseEntity<VerificationTokenDto> update(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final VerificationTokenDto verificationTokenDto
    ){
        log.info("*** VerificationTokenDto, resource; update verificationToken *");
        return ResponseEntity.ok(this.verificationTokenService.update(verificationTokenDto));
    }

    @PutMapping("/{verificationTokenId}")
    public ResponseEntity<VerificationTokenDto> update(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "Input must not blank") final String verificationId,
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final VerificationTokenDto verificationTokenDto
    ){
        log.info("*** VerificationTokenDto, resource; update verificationToken with verificationTokenId *");
        return ResponseEntity.ok(this.verificationTokenService.update(Integer.parseInt(verificationId.strip()),verificationTokenDto));
    }

    @DeleteMapping("/{verificationTokenId}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "Input must not blank") final String verificationTokenId) {
        log.info("*** Boolean, resource; delete verificationToken by id *");
        this.verificationTokenService.deleteById(Integer.parseInt(verificationTokenId));
        return ResponseEntity.ok(true);
    }
}
