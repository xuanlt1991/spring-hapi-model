package com.example.springhapimodeldemo.Response;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hl7.fhir.r4.model.*;

import java.util.List;

@ResourceDef(name="Patient")
public class PatientResponse extends Patient {

    @JsonBackReference
    public Resource setIdElement(IdType value) {
        this.id = value;
        return this;
    }

    @JsonBackReference
    public DomainResource setExtension(List<Extension> theExtension) {
        this.extension = theExtension;
        return this;
    }

    @JsonBackReference
    public DomainResource setModifierExtension(List<Extension> theModifierExtension) {
        this.modifierExtension = theModifierExtension;
        return this;
    }

}
