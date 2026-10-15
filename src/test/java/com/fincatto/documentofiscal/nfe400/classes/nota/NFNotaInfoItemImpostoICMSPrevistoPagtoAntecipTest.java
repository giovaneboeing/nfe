package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.nfe400.FabricaDeObjetosFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NFNotaInfoItemImpostoICMSPrevistoPagtoAntecipTest {

    @Test
    public void naoDevePermitirValorICMSPrevistoNegativo() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().setValorICMSPrevisto(new BigDecimal("-0.01")));
    }

    @Test
    public void naoDevePermitirValorICMSPrevistoComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().setValorICMSPrevisto(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().setValorICMSPrevisto(new BigDecimal("1.001")));
    }

    @Test
    public void naoDevePermitirValorICMSPrevistoNulo() {
        Assertions.assertThrows(IllegalStateException.class, () -> new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip().toString());
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip grupo = new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip();
        grupo.setValorICMSPrevisto(BigDecimal.ZERO);
        Assertions.assertEquals("<NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip><vICMSPrevisto>0.00</vICMSPrevisto></NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip>", grupo.toString());
    }

    @Test
    public void deveGerarGrupoDepoisDoGrupoDeICMSSelecionado() {
        final NFNotaInfoItemImpostoICMS icms = FabricaDeObjetosFake.getNFNotaInfoItemImpostoICMS();
        final NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip grupo = new NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip();
        grupo.setValorICMSPrevisto(new BigDecimal("12.34"));
        icms.setIcmsPrevistoPagtoAntecip(grupo);

        final String xml = icms.toString();
        Assertions.assertTrue(xml.endsWith("<ICMSPrevistoPagtoAntecip><vICMSPrevisto>12.34</vICMSPrevisto></ICMSPrevistoPagtoAntecip></NFNotaInfoItemImpostoICMS>"), xml);
    }

    @Test
    public void naoDeveGerarGrupoQuandoNaoInformado() {
        Assertions.assertFalse(FabricaDeObjetosFake.getNFNotaInfoItemImpostoICMS().toString().contains("ICMSPrevistoPagtoAntecip"));
    }
}
