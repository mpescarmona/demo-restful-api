package com.mpescarmona.demorestfulapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    Long userId;
    private String name;
    private String email;
    private String password;
    @OneToMany(targetEntity = Phone.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Phone> phones;
}
