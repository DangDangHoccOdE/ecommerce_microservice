package com.microservice.userservice.resource;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/users"})
@Slf4j
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<UserDto>> findAll(){
        log.info("*** UserDto List, controller; fetch all users *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.userService.findAll()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("userId")
            @NotBlank(message = "Input must not blank")
            @Valid final String userId){
        log.info("*** UserDto, resource' fetch user by id *");
        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(userId)));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final UserDto userDto
    ){
        log.info("*** UserDto, save user *");
        return ResponseEntity.ok(this.userService.save(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> update(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final UserDto userDto
    ){
        log.info("*** UserDto, update user *");
        return ResponseEntity.ok(this.userService.update(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> update(
            @PathVariable("userId")
            @NotBlank(message = "Input must not blank") final String userId,
            @RequestBody
            @NotNull(message = "Input must not ")
            @Valid final UserDto userDto
    ){
        log.info("*** UserDto, update user by id *");
        return ResponseEntity.ok(this.userService.update(Integer.parseInt(userId.strip()), userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId")
                                              @NotBlank(message = "Input must not blank")
                                              @Valid final String userId){
        log.info("*** Boolean, resource; delete user by id *");
        this.userService.deleteById(Integer.parseInt(userId));
        return ResponseEntity.ok(true);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(
            @PathVariable("username")
            @NotBlank(message = "Input must not blank")
            @Valid final String username
    ){
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

}
