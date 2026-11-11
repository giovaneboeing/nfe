package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.utils.DFPersister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NFNotaInfoIBSCBSTotTribSNTest {

    @Test
    public void naoDevePermitirValoresComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoIBSCBSTotTribSN().setValorTotalIBSSimplesNacional(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoIBSCBSTotTribSN().setValorTotalIBSSimplesNacional(new BigDecimal("1.123")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoIBSCBSTotTribSN().setValorTotalCBSSimplesNacional(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoIBSCBSTotTribSN().setValorTotalCBSSimplesNacional(new BigDecimal("1.123")));
    }

    @Test
    public void naoDevePermitirValoresNulos() {
        final NFNotaInfoIBSCBSTotTribSN semCBS = new NFNotaInfoIBSCBSTotTribSN();
        semCBS.setValorTotalIBSSimplesNacional(BigDecimal.ONE);
        Assertions.assertThrows(IllegalStateException.class, semCBS::toString);

        final NFNotaInfoIBSCBSTotTribSN semIBS = new NFNotaInfoIBSCBSTotTribSN();
        semIBS.setValorTotalCBSSimplesNacional(BigDecimal.ONE);
        Assertions.assertThrows(IllegalStateException.class, semIBS::toString);
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        Assertions.assertEquals("<NFNotaInfoIBSCBSTotTribSN><vIBSSN>12.34</vIBSSN><vCBSSN>9999999999999.99</vCBSSN></NFNotaInfoIBSCBSTotTribSN>", getTotalTribSN().toString());
    }

    @Test
    public void naoDeveGerarGrupoTribSNNoTotalQuandoNulo() {
        Assertions.assertEquals("<NFNotaInfoIBSCBSTot><vBCIBSCBS>100.00</vBCIBSCBS></NFNotaInfoIBSCBSTot>", getIBSCBSTot().toString());
    }

    @Test
    public void deveGerarGrupoTribSNAposEstornoCreditoNoTotal() {
        final NFNotaInfoIBSCBSTot total = getIBSCBSTot();
        final NFNotaInfoIBSCBSGrupoEstornoCredito estorno = new NFNotaInfoIBSCBSGrupoEstornoCredito();
        estorno.setValorIbsEstornado(BigDecimal.ONE);
        estorno.setValorCbsEstornado(BigDecimal.TEN);
        total.setGrupoEstornoCredito(estorno);
        total.setGrupoTributacaoSimplesNacional(getTotalTribSN());

        Assertions.assertEquals("<NFNotaInfoIBSCBSTot><vBCIBSCBS>100.00</vBCIBSCBS><gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>10.00</vCBSEstCred></gEstornoCred>"
                + "<gTribSN><vIBSSN>12.34</vIBSSN><vCBSSN>9999999999999.99</vCBSSN></gTribSN></NFNotaInfoIBSCBSTot>", total.toString());
    }

    @Test
    public void deveLerGrupoTribSNDoTotalDoXML() throws Exception {
        final NFNotaInfoIBSCBSTot total = getIBSCBSTot();
        total.setGrupoTributacaoSimplesNacional(getTotalTribSN());

        final NFNotaInfoIBSCBSTot lido = new DFPersister().read(NFNotaInfoIBSCBSTot.class, total.toString());
        Assertions.assertEquals("12.34", lido.getGrupoTributacaoSimplesNacional().getValorTotalIBSSimplesNacional());
        Assertions.assertEquals("9999999999999.99", lido.getGrupoTributacaoSimplesNacional().getValorTotalCBSSimplesNacional());
        Assertions.assertEquals(total.toString(), lido.toString());
    }

    private static NFNotaInfoIBSCBSTot getIBSCBSTot() {
        final NFNotaInfoIBSCBSTot total = new NFNotaInfoIBSCBSTot();
        total.setVBCIBSCBS(new BigDecimal("100"));
        return total;
    }

    private static NFNotaInfoIBSCBSTotTribSN getTotalTribSN() {
        final NFNotaInfoIBSCBSTotTribSN tribSN = new NFNotaInfoIBSCBSTotTribSN();
        tribSN.setValorTotalIBSSimplesNacional(new BigDecimal("12.34"));
        tribSN.setValorTotalCBSSimplesNacional(new BigDecimal("9999999999999.99"));
        return tribSN;
    }
}
