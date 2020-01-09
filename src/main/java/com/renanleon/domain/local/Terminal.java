package com.renanleon.domain.local;

import com.renanleon.domain.tripulante.Tripulante;

public class Terminal extends Local {

    public Terminal(Boolean isVeiculoNoLocal, Tripulante... tripulantesNoLocal) {
        super(isVeiculoNoLocal, tripulantesNoLocal);
    }
}
