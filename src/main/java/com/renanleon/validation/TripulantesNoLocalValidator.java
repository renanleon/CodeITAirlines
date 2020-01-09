package com.renanleon.validation;

import com.renanleon.domain.tripulante.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TripulantesNoLocalValidator implements Validator {

    @Override
    public void validate(List<Tripulante> tripulantes) {
        List<String> tipoTripulantes = tripulantes
                .stream()
                .map(tripulante -> tripulante.getClass().getSimpleName())
                .collect(Collectors.toList());

        if (tipoTripulantes.size() == 2) {
            oficialComChefeServicoValidation(tipoTripulantes);
            comissariaComPilotoValidation(tipoTripulantes);
        }

        presidiarioSemPolicialValidation(tipoTripulantes);
    }

    private void oficialComChefeServicoValidation(List<String> tripulantes){
        List<String> oficialEChefeServico = Arrays.asList(Oficial.class.getSimpleName(), ChefeServico.class.getSimpleName());

        if (tripulantes.containsAll(oficialEChefeServico))
            throw new RuntimeException("Oficial não pode ficar sozinho com Chefe de Servico de Bordo");
    }

    private void comissariaComPilotoValidation(List<String> tripulantes){
        List<String> comissariaEPiloto = Arrays.asList(Comissaria.class.getSimpleName(), Piloto.class.getSimpleName());

        if (tripulantes.containsAll(comissariaEPiloto))
            throw new RuntimeException("Comissária não pode ficar sozinha com Piloto");
    }

    private void presidiarioSemPolicialValidation(List<String> tripulantes){
        if (tripulantes.size() > 1
                && tripulantes.contains(Presidiario.class.getSimpleName())
                && !tripulantes.contains(Policial.class.getSimpleName()))
            throw new RuntimeException("Presidiário com outros tripulantes sem a presença do policial");
    }
}
