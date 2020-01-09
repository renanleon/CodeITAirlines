package com.renanleon;

import com.renanleon.domain.local.Aviao;
import com.renanleon.domain.local.Terminal;
import com.renanleon.domain.tripulante.*;
import com.renanleon.domain.viagem.Viagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeITAirlinesTest {

    Piloto piloto = new Piloto("Piloto", Boolean.TRUE);
    Oficial oficial1 = new Oficial("Oficial 1", Boolean.FALSE);
    Oficial oficial2 = new Oficial("Oficial 2", Boolean.FALSE);

    ChefeServico chefeServico = new ChefeServico("Chefe de Serviço", Boolean.TRUE);
    Comissaria comissaria1 = new Comissaria("Comissária 1", Boolean.FALSE);
    Comissaria comissaria2 = new Comissaria("Comissária 2", Boolean.FALSE);

    Policial policial = new Policial("Policial", Boolean.TRUE);
    Presidiario presidiario = new Presidiario("Presidiário", Boolean.FALSE);

    @Test
    public void testShouldThrowExceptionByOrigemNaoInformada() {
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withDestino(aviao)
                        .withTripulantes(piloto)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Local de partida não informado");
    }

    @Test
    public void testShouldThrowExceptionByDestinoNaoInformado() {
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(aviao)
                        .withTripulantes(piloto)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Local de destino não informado");
    }

    @Test
    public void testShouldThrowExceptionByTripulanteNaoInformado() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto);
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Tripulantes não informados");
    }

    @Test
    public void testShouldThrowExceptionByTripulanteNaoEstaNaOrigem() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto);
        Aviao aviao = new Aviao(Boolean.FALSE, chefeServico);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(chefeServico)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Tripulante informado não está no " + terminal.getClass().getSimpleName());
    }

    @Test
    public void testShouldThrowExceptionByVeiculoNaoEstaNaOrigem() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto);
        Aviao aviao = new Aviao(Boolean.FALSE, chefeServico);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(aviao)
                        .withDestino(terminal)
                        .withTripulantes(chefeServico)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Veículo não está no " + aviao.getClass().getSimpleName());
    }

    @Test
    public void testShouldThrowExceptionByExcedeuMaximoTripulante() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto, oficial1, oficial2);
        Aviao aviao = new Aviao(Boolean.FALSE, chefeServico);
        Integer MAXIMO_TRIPULANTES = 2;

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(piloto, oficial1, oficial2)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Não é permitido mais que " + MAXIMO_TRIPULANTES + " tripulantes por viagem");
    }

    @Test
    public void testShouldThrowExceptionByNecessarioUmTripulanteQueDirige() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto, oficial1, oficial2);
        Aviao aviao = new Aviao(Boolean.FALSE, chefeServico);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(oficial1, oficial2)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "É necessário que ao menos um tripulante possa dirigir o veículo");
    }

    @Test
    public void testShouldThrowExceptionByOficialSozinhoComChefe() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto, oficial1, oficial2, chefeServico);
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(piloto, oficial1)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Oficial não pode ficar sozinho com Chefe de Servico de Bordo");
    }

    @Test
    public void testShouldThrowExceptionByComissariaSozinhaComPiloto() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto, comissaria1, comissaria2, chefeServico);
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(chefeServico, comissaria1)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Comissária não pode ficar sozinha com Piloto");
    }

    @Test
    public void testShouldThrowExceptionByPresidiarioSemPolicial() {
        Terminal terminal = new Terminal(Boolean.TRUE, piloto, comissaria1, presidiario, policial);
        Aviao aviao = new Aviao(Boolean.FALSE);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                new Viagem.ViagemBuilder()
                        .withOrigem(terminal)
                        .withDestino(aviao)
                        .withTripulantes(policial, piloto)
                        .build()
                        .viajar());

        Assertions.assertEquals(exception.getLocalizedMessage(), "Presidiário com outros tripulantes sem a presença do policial");
    }
}
