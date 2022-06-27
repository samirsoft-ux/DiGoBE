package com.acme.digobe.business.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("businessMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ReservationMapper reservationMapper(){
        return new ReservationMapper();
    }

    @Bean
    public SportFieldMapper sportFieldMapper(){
        return new SportFieldMapper();
    }
}
