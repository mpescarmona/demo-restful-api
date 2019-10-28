package com.mpescarmona.demorestfulapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long phoneId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @NotEmpty
    private Integer number;
    @NotEmpty
    private Integer citycode;
    @NotEmpty
    private Integer countrycode;
}
