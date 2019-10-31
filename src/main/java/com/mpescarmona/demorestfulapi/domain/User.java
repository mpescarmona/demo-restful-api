package com.mpescarmona.demorestfulapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "userId", updatable = false, nullable = false)
    private String userId;
    @NotEmpty(message = "User name must not be empty")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(message = "Invalid password. Must contains at least one uppercase letter, two lowercase letters, two numbers",
            regexp = "^(?=.*[0-9]){2,}(?=.*[a-z])+(?=.*[A-Z])+(?=\\S+$)([0-9a-zA-Z]){5,}$")
    private String password;
    private Date created;
    private Date updated;
    private Date lastLogin;
    private String token;
    @Getter
    private boolean active;
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();
}
