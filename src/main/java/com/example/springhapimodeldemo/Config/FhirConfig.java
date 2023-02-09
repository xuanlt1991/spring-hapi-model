package com.example.springhapimodeldemo.Config;

import ca.uhn.fhir.context.FhirContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FhirConfig {
    @Bean
    public FhirContext getFhirContext() {
        return FhirContext.forR4();
    }
}