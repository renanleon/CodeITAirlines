package com.renanleon.domain.viagem;

import com.renanleon.domain.local.Local;
import com.renanleon.domain.tripulante.Tripulante;
import com.renanleon.validation.ViagemTripulantesValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Viagem {

    private Local origem;
    private Local destino;
    private List<Tripulante> tripulantes;

    private ViagemTripulantesValidator viagemTripulantesValidator = new ViagemTripulantesValidator();

    public Viagem(Local origem, Local destino, List<Tripulante> tripulantes) {
        validaParametros(origem, destino, tripulantes);

        this.origem = origem;
        this.destino = destino;
        this.tripulantes = tripulantes;
    }

    private void validaParametros(Local origem, Local destino, List<Tripulante> tripulantes) {
        if (Objects.isNull(origem))
            throw new RuntimeException("Local de partida não informado");

        if (Objects.isNull(destino))
            throw new RuntimeException("Local de destino não informado");

        if (Objects.isNull(tripulantes) || tripulantes.isEmpty())
            throw new RuntimeException("Tripulantes não informados");

        if(!origem.getTripulantesNoLocal().containsAll(tripulantes))
            throw new RuntimeException("Tripulante informado não está no " + origem.getClass().getSimpleName());

        if(!origem.getIsVeiculoNoLocal())
            throw new RuntimeException("Veículo não está no " + origem.getClass().getSimpleName());

        viagemTripulantesValidator.validate(tripulantes);
    }

    public void viajar() {
        tripulantes.forEach(tripulante -> {
            origem.saiTripulante(tripulante);
            destino.entraTripulante(tripulante);
        });
        origem.setIsVeiculoNoLocal(!origem.getIsVeiculoNoLocal());
        destino.setIsVeiculoNoLocal(!destino.getIsVeiculoNoLocal());

        System.out.println(tripulantes.toString()
                + " viajando do " + origem.getClass().getSimpleName()
                + " para " + destino.getClass().getSimpleName());

        origem.validaTripulates();
        destino.validaTripulates();

        System.out.println(origem.getClass().getSimpleName() + " = " + origem.getTripulantesNoLocal());
        System.out.println(destino.getClass().getSimpleName() + " = " + destino.getTripulantesNoLocal());
        System.out.println();
    }

    public static class ViagemBuilder {

        private Local origem;
        private Local destino;
        private List<Tripulante> tripulantes;

        public ViagemBuilder withOrigem(Local origem) {
            this.origem = origem;
            return this;
        }

        public ViagemBuilder withDestino(Local destino) {
            this.destino = destino;
            return this;
        }

        public ViagemBuilder withTripulantes(Tripulante... tripulantes) {
            this.tripulantes = Arrays.asList(tripulantes);
            return this;
        }

        public Viagem build() {
            return new Viagem(this.origem, this.destino, this.tripulantes);
        }
    }
}
