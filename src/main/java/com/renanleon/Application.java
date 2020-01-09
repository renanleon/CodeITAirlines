package com.renanleon;

import com.renanleon.domain.local.Aviao;
import com.renanleon.domain.local.Terminal;
import com.renanleon.domain.tripulante.*;
import com.renanleon.domain.viagem.Viagem;

public class Application {

    public static void main(String[] args) {
        Piloto piloto = new Piloto("André", Boolean.TRUE);
        Oficial oficial1 = new Oficial("Jorge", Boolean.FALSE);
        Oficial oficial2 = new Oficial("Bento", Boolean.FALSE);

        ChefeServico chefeServico = new ChefeServico("Vicente", Boolean.TRUE);
        Comissaria comissaria1 = new Comissaria("Maria", Boolean.FALSE);
        Comissaria comissaria2 = new Comissaria("Marcela", Boolean.FALSE);

        Policial policial = new Policial("Policial", Boolean.TRUE);
        Presidiario presidiario = new Presidiario("Presidiário", Boolean.FALSE);

        Terminal terminal = new Terminal(Boolean.TRUE, piloto, oficial1, oficial2, chefeServico, comissaria1, comissaria2, policial, presidiario);
        Aviao aviao = new Aviao(Boolean.FALSE);

        System.out.println("Começando o embarque...");
        System.out.println(terminal.getClass().getSimpleName() + " = " + terminal.getTripulantesNoLocal());
        System.out.println(aviao.getClass().getSimpleName() + " = " + aviao.getTripulantesNoLocal());
        System.out.println();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(piloto, oficial1)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(piloto)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(piloto, oficial2)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(piloto)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(piloto, chefeServico)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(chefeServico)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(policial, presidiario)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(piloto)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(piloto, chefeServico)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(chefeServico)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(chefeServico, comissaria1)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(aviao)
                .withDestino(terminal)
                .withTripulantes(chefeServico)
                .build()
                .viajar();

        new Viagem.ViagemBuilder()
                .withOrigem(terminal)
                .withDestino(aviao)
                .withTripulantes(chefeServico, comissaria2)
                .build()
                .viajar();

        System.out.println("================Fim do embarque=====================");
    }
}
