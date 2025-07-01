package com.microservice.userservice.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String firstName;

    private String lastName;

    private String imageUrl;

    private String email;

    private String phone;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<AddressDto> addressDtos;

    @JsonProperty("credential")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Create
}
