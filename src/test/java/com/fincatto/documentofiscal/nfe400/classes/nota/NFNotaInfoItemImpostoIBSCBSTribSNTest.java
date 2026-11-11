package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.nfe400.classes.NFNotaInfoImpostoTributacaoIBSCBS;
import com.fincatto.documentofiscal.utils.DFPersister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NFNotaInfoItemImpostoIBSCBSTribSNTest {

    @Test
    public void naoDevePermitirPercentualIBSSNComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNIBS().setPercentualIBSSimplesNacional(new BigDecimal("1000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNIBS().setPercentualIBSSimplesNacional(new BigDecimal("1.12345")));
    }

    @Test
    public void naoDevePermitirValorIBSSNComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNIBS().setValorIBSSimplesNacional(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNIBS().setValorIBSSimplesNacional(new BigDecimal("1.123")));
    }

    @Test
    public void naoDevePermitirPercentualCBSSNComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNCBS().setPercentualCBSSimplesNacional(new BigDecimal("1000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNCBS().setPercentualCBSSimplesNacional(new BigDecimal("1.12345")));
    }

    @Test
    public void naoDevePermitirValorCBSSNComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNCBS().setValorCBSSimplesNacional(new BigDecimal("10000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNCBS().setValorCBSSimplesNacional(new BigDecimal("1.123")));
    }

    @Test
    public void naoDevePermitirGrupoCBSSNNulo() {
        final NFNotaInfoItemImpostoIBSCBSTribSN tribSN = new NFNotaInfoItemImpostoIBSCBSTribSN();
        tribSN.setGrupoIBSSimplesNacional(getGrupoIBSSN());
        Assertions.assertThrows(IllegalStateException.class, tribSN::toString);
    }

    @Test
    public void naoDevePermitirCamposDosGruposNulos() {
        Assertions.assertThrows(IllegalStateException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNIBS().toString());
        Assertions.assertThrows(IllegalStateException.class, () -> new NFNotaInfoItemImpostoIBSCBSTribSNCBS().toString());
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final String xmlEsperado = "<NFNotaInfoItemImpostoIBSCBSTribSN><gIBSSN><pIBSSN>1.2345</pIBSSN><vIBSSN>12.34</vIBSSN></gIBSSN><gCBSSN><pCBSSN>999.99</pCBSSN><vCBSSN>9999999999999.99</vCBSSN></gCBSSN></NFNotaInfoItemImpostoIBSCBSTribSN>";
        Assertions.assertEquals(xmlEsperado, getGrupoTribSN().toString());
    }

    @Test
    public void deveOmitirGrupoIBSSNQuandoNulo() {
        final NFNotaInfoItemImpostoIBSCBSTribSN tribSN = new NFNotaInfoItemImpostoIBSCBSTribSN();
        tribSN.setGrupoCBSSimplesNacional(getGrupoCBSSN());
        Assertions.assertEquals("<NFNotaInfoItemImpostoIBSCBSTribSN><gCBSSN><pCBSSN>999.99</pCBSSN><vCBSSN>9999999999999.99</vCBSSN></gCBSSN></NFNotaInfoItemImpostoIBSCBSTribSN>", tribSN.toString());
    }

    @Test
    public void naoDeveGerarGrupoTribSNNoIBSCBSQuandoNulo() {
        Assertions.assertEquals("<NFNotaInfoItemImpostoIBSCBS><CST>410</CST><cClassTrib>410001</cClassTrib></NFNotaInfoItemImpostoIBSCBS>", getIBSCBS().toString());
    }

    @Test
    public void deveGerarGrupoTribSNComoUltimoElementoDoIBSCBS() {
        final NFNotaInfoItemImpostoIBSCBS ibscbs = getIBSCBS();
        final NFNotaInfoIBSCBSGrupoEstornoCredito estorno = new NFNotaInfoIBSCBSGrupoEstornoCredito();
        estorno.setValorIbsEstornado(BigDecimal.ONE);
        estorno.setValorCbsEstornado(BigDecimal.TEN);
        ibscbs.setGrupoEstornoCredito(estorno);
        ibscbs.setGrupoTributacaoSimplesNacional(getGrupoTribSN());

        Assertions.assertEquals("<NFNotaInfoItemImpostoIBSCBS><CST>410</CST><cClassTrib>410001</cClassTrib><gEstornoCred><vIBSEstCred>1.00</vIBSEstCred><vCBSEstCred>10.00</vCBSEstCred></gEstornoCred>"
                + "<gTribSN><gIBSSN><pIBSSN>1.2345</pIBSSN><vIBSSN>12.34</vIBSSN></gIBSSN><gCBSSN><pCBSSN>999.99</pCBSSN><vCBSSN>9999999999999.99</vCBSSN></gCBSSN></gTribSN></NFNotaInfoItemImpostoIBSCBS>", ibscbs.toString());
    }

    @Test
    public void deveLerGrupoTribSNDoXML() throws Exception {
        final NFNotaInfoItemImpostoIBSCBS ibscbs = getIBSCBS();
        ibscbs.setGrupoTributacaoSimplesNacional(getGrupoTribSN());

        final NFNotaInfoItemImpostoIBSCBS lido = new DFPersister().read(NFNotaInfoItemImpostoIBSCBS.class, ibscbs.toString());
        final NFNotaInfoItemImpostoIBSCBSTribSN tribSN = lido.getGrupoTributacaoSimplesNacional();
        Assertions.assertEquals("1.2345", tribSN.getGrupoIBSSimplesNacional().getPercentualIBSSimplesNacional());
        Assertions.assertEquals("12.34", tribSN.getGrupoIBSSimplesNacional().getValorIBSSimplesNacional());
        Assertions.assertEquals("999.99", tribSN.getGrupoCBSSimplesNacional().getPercentualCBSSimplesNacional());
        Assertions.assertEquals("9999999999999.99", tribSN.getGrupoCBSSimplesNacional().getValorCBSSimplesNacional());
        Assertions.assertEquals(ibscbs.toString(), lido.toString());
    }

    private static NFNotaInfoItemImpostoIBSCBS getIBSCBS() {
        final NFNotaInfoItemImpostoIBSCBS ibscbs = new NFNotaInfoItemImpostoIBSCBS();
        ibscbs.setCst(NFNotaInfoImpostoTributacaoIBSCBS.CST_410);
        ibscbs.setcClassTrib("410001");
        return ibscbs;
    }

    private static NFNotaInfoItemImpostoIBSCBSTribSN getGrupoTribSN() {
        final NFNotaInfoItemImpostoIBSCBSTribSN tribSN = new NFNotaInfoItemImpostoIBSCBSTribSN();
        tribSN.setGrupoIBSSimplesNacional(getGrupoIBSSN());
        tribSN.setGrupoCBSSimplesNacional(getGrupoCBSSN());
        return tribSN;
    }

    private static NFNotaInfoItemImpostoIBSCBSTribSNIBS getGrupoIBSSN() {
        final NFNotaInfoItemImpostoIBSCBSTribSNIBS ibs = new NFNotaInfoItemImpostoIBSCBSTribSNIBS();
        ibs.setPercentualIBSSimplesNacional(new BigDecimal("1.2345"));
        ibs.setValorIBSSimplesNacional(new BigDecimal("12.34"));
        return ibs;
    }

    private static NFNotaInfoItemImpostoIBSCBSTribSNCBS getGrupoCBSSN() {
        final NFNotaInfoItemImpostoIBSCBSTribSNCBS cbs = new NFNotaInfoItemImpostoIBSCBSTribSNCBS();
        cbs.setPercentualCBSSimplesNacional(new BigDecimal("999.99"));
        cbs.setValorCBSSimplesNacional(new BigDecimal("9999999999999.99"));
        return cbs;
    }
}
