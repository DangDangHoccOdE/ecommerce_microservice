package com.microservice.userservice.resource;

import com.microservice.userservice.dto.CredentialDto;
import com.microservice.userservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.userservice.service.CredentialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/credentials"})
@Slf4j
@RequiredArgsConstructor
public class CredentialResource {
    private final CredentialService credentialService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<CredentialDto>> findAll(){
        log.info("*** CredentialDto List, controller; fetch all credentials");
        return ResponseEntity.ok(new DtoCollectionResponse<>(credentialService.findAll()));
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<CredentialDto> findById(@PathVariable("credentialId")
                                                  @NotBlank(message = "Input must not blank")
                                                  @Valid final String credentialId) {
        log.info("*** CredentialDto, resource; fetch credential by id *");
        return ResponseEntity.ok(this.credentialService.findById(Integer.parseInt(credentialId.strip())));
    }

    @PostMapping
    public ResponseEntity<CredentialDto> save(@RequestBody
                                              @NotNull(message = "Input must not NULL")
                                              @Valid final CredentialDto credentialDto){
        log.info("*** CredentialDto, resource; save credential *");
        return ResponseEntity.ok(this.credentialService.save(credentialDto));
    }

    @PutMapping
    public ResponseEntity<CredentialDto> update(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final CredentialDto credentialDto) {
        log.info("*** CredentialDto, resource; update credential *");
        return ResponseEntity.ok(this.credentialService.update(credentialDto));
    }

    @PutMapping("/{credentialId}")
    public ResponseEntity<CredentialDto> update(
            @PathVariable("credentialId")
            @NotBlank(message = "Input must not blank") final String credentialId,
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final CredentialDto credentialDto
    ){
        log.info("*** CredentialDto, resource; update credential with credentialId");
        return ResponseEntity.ok(this.credentialService.update(Integer.parseInt(credentialId.strip()), credentialDto));
    }

    @DeleteMapping("/{credentialId}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("credentialId")
            @NotBlank(message = "Input must not blank")
            @Valid final String credentialId
    ){
        log.info("*** Boolean, resource; delete credential by id *");
        this.credentialService.deleteById(Integer.parseInt(credentialId.strip()));
        return ResponseEntity.ok(true);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CredentialDto> findByUserName(
            @PathVariable("username")
            @NotBlank(message = "Input must not blank")
            @Valid final String username
    ){
        log.info("*** CredentialDto, resource; update credential by credentialId *");
        return ResponseEntity.ok(this.credentialService.findByUsername(username));
    }
}
