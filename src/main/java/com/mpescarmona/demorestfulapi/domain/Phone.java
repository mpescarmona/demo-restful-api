package com.mpescarmona.demorestfulapi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Phone {
    private Integer number;
    private Integer citycode;
    private Integer countrycode;
}
