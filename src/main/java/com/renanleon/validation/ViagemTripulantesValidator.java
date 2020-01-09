package com.renanleon.validation;

import com.renanleon.domain.tripulante.Tripulante;

import java.util.List;

public class ViagemTripulantesValidator implements Validator {

    private static final Integer MAXIMO_TRIPULANTES = 2;

    @Override
    public void validate(List<Tripulante> tripulantes) {
        if (tripulantes.size() > MAXIMO_TRIPULANTES)
            throw new RuntimeException("Não é permitido mais que " + MAXIMO_TRIPULANTES + " tripulantes por viagem");

        if (tripulantes.stream().noneMatch(Tripulante::getPodeDirigir))
            throw new RuntimeException("É necessário que ao menos um tripulante possa dirigir o veículo");
    }
}
