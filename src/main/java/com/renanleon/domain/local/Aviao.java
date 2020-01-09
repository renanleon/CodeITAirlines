package com.renanleon.domain.local;

import com.renanleon.domain.tripulante.Tripulante;

public class Aviao extends Local {

    public Aviao(Boolean isVeiculoNoLocal, Tripulante... tripulantesNoLocal) {
        super(isVeiculoNoLocal, tripulantesNoLocal);
    }
}
