package com.renanleon.validation;

import com.renanleon.domain.tripulante.Tripulante;

import java.util.List;

public interface Validator {

    void validate(List<Tripulante> tripulantes);
}
