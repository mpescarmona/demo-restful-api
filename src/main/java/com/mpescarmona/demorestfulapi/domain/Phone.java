package com.mpescarmona.demorestfulapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "phoneId", updatable = false, nullable = false)
    private String phoneId;
    @Positive(message = "Phone number should not be positive")
    private Integer number;
    @Positive(message = "Phone city code should not be positive")
    private Integer citycode;
    @Positive(message = "Phone country code should not be positive")
    private Integer countrycode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
