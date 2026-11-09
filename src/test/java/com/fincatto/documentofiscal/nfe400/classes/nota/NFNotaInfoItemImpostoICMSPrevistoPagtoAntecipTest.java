package com.fincatto.documentofiscal.nfe400.classes.nota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NFNotaInfoItemImpostoICMSPrevistoPagtoAntecipTest {

    @Test
    public void naoDevePermitirValorICMSPrevistoComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().setValorICMSPrevisto(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().setValorICMSPrevisto(new BigDecimal("1.123")));
    }

    @Test
    public void naoDevePermitirValorICMSPrevistoNulo() {
        Assertions.assertThrows(IllegalStateException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().toString());
    }

    @Test
    public void devePermitirValorICMSPrevistoZero() {
        final NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip previsto = new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip();
        previsto.setValorICMSPrevisto(BigDecimal.ZERO);
        Assertions.assertEquals("0.00", previsto.getValorICMSPrevisto());
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip previsto = new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip();
        previsto.setValorICMSPrevisto(new BigDecimal("999999999999.99"));
        final String xmlEsperado = "<NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip><vICMSPrevisto>999999999999.99</vICMSPrevisto></NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip>";
        Assertions.assertEquals(xmlEsperado, previsto.toString());
    }
}
