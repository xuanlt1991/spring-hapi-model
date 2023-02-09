package com.example.springhapimodeldemo.Config;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializationConfig {

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ObjectMapper getObjectMapper(@Autowired final FhirContext context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Resource.class, new FhirHapiSerializer<Resource>(Resource.class, context));

        simpleModule.setDeserializerModifier(new BeanDeserializerModifier() {
            public JsonDeserializer<?> modifyDeserializer(
                    DeserializationConfig config,
                    BeanDescription beanDesc,
                    JsonDeserializer<?> deserializer) {
                if (Resource.class.isAssignableFrom(beanDesc.getBeanClass())) {
                    return new FhirHapiDeserializer(beanDesc.getBeanClass(), context);
                }
                return deserializer;
            }
        });

        mapper.registerModule(simpleModule);
        return mapper;
    }
}
