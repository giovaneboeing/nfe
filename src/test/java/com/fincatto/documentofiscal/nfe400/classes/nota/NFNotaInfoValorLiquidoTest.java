package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.nfe400.FabricaDeObjetosFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Campos de valor liquido do produto da NT 2026.008 (I11b, I11c e W01a).
 */
public class NFNotaInfoValorLiquidoTest {

    @Test
    public void deveGerarValoresLiquidosDoItemLogoDepoisDoVProd() {
        final NFNotaInfoItemProduto produto = FabricaDeObjetosFake.getNFNotaInfoItemProduto();
        produto.setValorUnitarioLiquido(new BigDecimal("3.3333333333"));
        produto.setValorTotalLiquido(new BigDecimal("10.00"));

        final String xml = produto.toString();
        Assertions.assertTrue(xml.contains("</vProd><vUnComLiq>3.3333333333</vUnComLiq><vProdLiq>10.00</vProdLiq>"), xml);
    }

    @Test
    public void naoDeveGerarValoresLiquidosDoItemQuandoNaoInformados() {
        final String xml = FabricaDeObjetosFake.getNFNotaInfoItemProduto().toString();
        Assertions.assertFalse(xml.contains("vUnComLiq"));
        Assertions.assertFalse(xml.contains("vProdLiq"));
    }

    @Test
    public void naoDevePermitirValoresLiquidosDoItemInvalidos() {
        final NFNotaInfoItemProduto produto = new NFNotaInfoItemProduto();
        Assertions.assertThrows(NumberFormatException.class, () -> produto.setValorUnitarioLiquido(new BigDecimal("1.00000000001")));
        Assertions.assertThrows(NumberFormatException.class, () -> produto.setValorUnitarioLiquido(new BigDecimal("-1")));
        Assertions.assertThrows(NumberFormatException.class, () -> produto.setValorTotalLiquido(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> produto.setValorTotalLiquido(new BigDecimal("1.001")));
    }

    @Test
    public void deveGerarValorTotalLiquidoComoPrimeiroFilhoDoTotal() {
        final NFNotaInfoTotal total = FabricaDeObjetosFake.getNFNotaInfoTotal();
        total.setValorTotalLiquidoProdutos(new BigDecimal("1234.56"));

        final String xml = total.toString();
        Assertions.assertTrue(xml.startsWith("<NFNotaInfoTotal><vProdLiqTot>1234.56</vProdLiqTot><ICMSTot>"), xml);
    }

    @Test
    public void naoDeveGerarValorTotalLiquidoQuandoNaoInformado() {
        Assertions.assertFalse(FabricaDeObjetosFake.getNFNotaInfoTotal().toString().contains("vProdLiqTot"));
    }

    @Test
    public void naoDevePermitirValorTotalLiquidoInvalido() {
        final NFNotaInfoTotal total = new NFNotaInfoTotal();
        Assertions.assertThrows(NumberFormatException.class, () -> total.setValorTotalLiquidoProdutos(new BigDecimal("-0.01")));
        Assertions.assertThrows(NumberFormatException.class, () -> total.setValorTotalLiquidoProdutos(new BigDecimal("1.001")));
    }
}
