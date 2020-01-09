package com.renanleon.domain.local;

import com.renanleon.domain.tripulante.Tripulante;
import com.renanleon.validation.TripulantesNoLocalValidator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Local {

    private List<Tripulante> tripulantesNoLocal;
    private Boolean isVeiculoNoLocal;

    private TripulantesNoLocalValidator tripulantesNoLocalValidator = new TripulantesNoLocalValidator();

    public Local(Boolean isVeiculoNoLocal, Tripulante... tripulantesNoLocal) {
        this.isVeiculoNoLocal = isVeiculoNoLocal;
        this.tripulantesNoLocal = new LinkedList<>(Arrays.asList(tripulantesNoLocal));
    }

    public Boolean getIsVeiculoNoLocal() {
        return isVeiculoNoLocal;
    }

    public void setIsVeiculoNoLocal(Boolean veiculoNoLocal) {
        isVeiculoNoLocal = veiculoNoLocal;
    }

    public List<Tripulante> getTripulantesNoLocal() {
        return tripulantesNoLocal;
    }

    public void entraTripulante(Tripulante tripulante) {
        tripulantesNoLocal.add(tripulante);
    }

    public void saiTripulante(Tripulante tripulante) {
        tripulantesNoLocal.remove(tripulante);
    }

    public void validaTripulates(){
        tripulantesNoLocalValidator.validate(this.getTripulantesNoLocal());
    }
}
