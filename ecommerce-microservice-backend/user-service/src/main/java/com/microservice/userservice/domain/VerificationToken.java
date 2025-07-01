package com.microservice.userservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice.userservice.constant.AppConstant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "verification_tokens")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"credential"})
@Data
@Builder
public final class VerificationToken extends AbstractMappedEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id", unique = true, nullable = false, updatable = false)
    private Integer verificationTokenId;

    @Column(name = "verif_token")
    private String token;

    @JsonFormat(pattern = AppConstant.LOCAL_DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_FORMAT)
    @Column(name = "expite_date")
    private LocalDate expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credential_id")
    private Credential credential;

}
