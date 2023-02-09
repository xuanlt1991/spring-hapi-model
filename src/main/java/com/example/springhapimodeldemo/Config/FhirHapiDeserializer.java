package com.example.springhapimodeldemo.Config;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FhirHapiDeserializer<K extends IBaseResource> extends StdDeserializer<K> {
    private static final long serialVersionUID = 1L;

    private FhirContext fhirContext;

    public FhirHapiDeserializer(final Class<K> classType, final FhirContext fhirContext) {
        super(classType);
        this.fhirContext = fhirContext;
    }

    public FhirHapiDeserializer() {
        this(null, null);
    }


    @Override
    @SuppressWarnings("unchecked")
    public K deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final String fhirResourceTree = jsonParser.getCodec().readTree(jsonParser).toString();
        return fhirContext.newJsonParser().parseResource((Class<K>) super.handledType(), fhirResourceTree);
    }
}
