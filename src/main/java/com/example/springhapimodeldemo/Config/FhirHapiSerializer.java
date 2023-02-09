package com.example.springhapimodeldemo.Config;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FhirHapiSerializer<K extends IBaseResource> extends StdSerializer<K> {

    private static final long serialVersionUID = 1L;

    private FhirContext fhirContext;

    public FhirHapiSerializer(final Class<K> classType, final FhirContext fhirContext) {
        super(classType);
        this.fhirContext = fhirContext;
    }

    public FhirHapiSerializer() {
        this(null, null);
    }


    @Override
    public void serialize(K fhirResource, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final String stringFieldFhirResource = fhirContext.newJsonParser().encodeResourceToString(fhirResource);
        jsonGenerator.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        jsonGenerator.writeRaw(stringFieldFhirResource);
    }
}
