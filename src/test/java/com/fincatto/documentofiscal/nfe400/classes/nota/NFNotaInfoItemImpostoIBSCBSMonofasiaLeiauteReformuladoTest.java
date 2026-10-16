package com.fincatto.documentofiscal.nfe400.classes.nota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Leiaute reformulado da tributacao monofasica (NT 2025.002 v1.50): o XML gerado deve satisfazer
 * o tipo TMonofasia do pacote PL_010f.
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasiaLeiauteReformuladoTest {

    private static final String PACOTE = "schemas/PL_010f_v1.04/";
    private static final String NAMESPACE = "http://www.portalfiscal.inf.br/nfe";

    @Test
    public void deveGerarIbsAdRemECbsAdValoremNaOrdemDoSchema() throws Exception {
        final NFNotaInfoItemImpostoIBSCBSMonofasia monofasia = new NFNotaInfoItemImpostoIBSCBSMonofasia();
        monofasia.setgIBSMonoAdRem(adRemIbs());
        monofasia.setgCBSMonoAdValorem(adValoremCbs());
        monofasia.setVTotIBSMonoItem(new BigDecimal("16.50"));
        monofasia.setVTotCBSMonoItem(new BigDecimal("13.00"));

        final String xml = monofasia.toString();
        Assertions.assertTrue(xml.contains("<gIBSMonoAdRem><gMonoPadrao><qBCMono>13.0000</qBCMono><adRemIBS>1.10</adRemIBS><vIBSMono>14.30</vIBSMono></gMonoPadrao>"
                + "<gMonoReten><qBCMonoReten>2.0000</qBCMonoReten><adRemIBSReten>1.10</adRemIBSReten><vIBSMonoReten>2.20</vIBSMonoReten></gMonoReten></gIBSMonoAdRem>"), xml);
        Assertions.assertTrue(xml.contains("<gCBSMonoAdValorem><gMonoPadrao><vBCMono>100.00</vBCMono><pAliqMonoCBS>8.80</pAliqMonoCBS><vCBSMono>8.80</vCBSMono></gMonoPadrao>"
                + "<gMonoReten><vBCMonoReten>50.00</vBCMonoReten><pAliqMonoReten>8.40</pAliqMonoReten><vCBSMonoReten>4.20</vCBSMonoReten></gMonoReten></gCBSMonoAdValorem>"), xml);
        this.validaContraSchema(xml);
    }

    @Test
    public void deveGerarIbsAdValoremECbsAdRemComRetidoEBioDiferencaValidosNoSchema() throws Exception {
        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao padraoIbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao();
        padraoIbs.setVBCMono(new BigDecimal("100"));
        padraoIbs.setPAliqMonoUF(new BigDecimal("0.1"));
        padraoIbs.setVIBSMonoUF(new BigDecimal("0.10"));
        padraoIbs.setPAliqMonoMun(new BigDecimal("0.05"));
        padraoIbs.setVIBSMonoMun(new BigDecimal("0.05"));
        padraoIbs.setVIBSMono(new BigDecimal("0.15"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet retidoIbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet();
        retidoIbs.setVIBSMonoRet(new BigDecimal("1.00"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca bioIbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca();
        bioIbs.setQBCBioComb(new BigDecimal("3"));
        bioIbs.setVIBSDiferenca(new BigDecimal("0.30"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem adValoremIbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem();
        adValoremIbs.setgMonoPadrao(padraoIbs);
        adValoremIbs.setgMonoRet(retidoIbs);
        adValoremIbs.setGpBioDiferenca(bioIbs);

        final NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao padraoCbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao();
        padraoCbs.setQBCMono(new BigDecimal("13"));
        padraoCbs.setAdRemCBS(new BigDecimal("0.9"));
        padraoCbs.setVCBSMono(new BigDecimal("11.70"));
        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem adRemCbs = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem();
        adRemCbs.setgMonoPadrao(padraoCbs);

        final NFNotaInfoItemImpostoIBSCBSMonofasia monofasia = new NFNotaInfoItemImpostoIBSCBSMonofasia();
        monofasia.setgIBSMonoAdValorem(adValoremIbs);
        monofasia.setgCBSMonoAdRem(adRemCbs);
        monofasia.setVTotIBSMonoItem(new BigDecimal("0.15"));
        monofasia.setVTotCBSMonoItem(new BigDecimal("11.70"));

        final String xml = monofasia.toString();
        Assertions.assertTrue(xml.contains("<gMonoRet><vIBSMonoRet>1.00</vIBSMonoRet></gMonoRet><gpBioDiferenca><qBCBioComb>3.0000</qBCBioComb><vIBSDiferenca>0.30</vIBSDiferenca></gpBioDiferenca>"), xml);
        this.validaContraSchema(xml);
    }

    @Test
    public void naoDeveValidarIbsAdRemEAdValoremAoMesmoTempo() {
        final NFNotaInfoItemImpostoIBSCBSMonofasia monofasia = new NFNotaInfoItemImpostoIBSCBSMonofasia();
        monofasia.setgIBSMonoAdRem(adRemIbs());
        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem adValorem = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem();
        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten reten = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten();
        reten.setVBCMonoReten(new BigDecimal("10"));
        reten.setPAliqMonoReten(new BigDecimal("1"));
        reten.setVIBSMonoReten(new BigDecimal("0.10"));
        adValorem.setgMonoReten(reten);
        monofasia.setgIBSMonoAdValorem(adValorem);
        monofasia.setVTotIBSMonoItem(new BigDecimal("16.60"));
        monofasia.setVTotCBSMonoItem(BigDecimal.ZERO);

        Assertions.assertThrows(SAXException.class, () -> this.validaContraSchema(monofasia.toString()));
    }

    @Test
    public void naoDevePermitirValoresComTamanhoInvalido() {
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao().setVBCMono(new BigDecimal("100000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao().setPAliqMonoUF(new BigDecimal("1000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten().setPAliqMonoReten(new BigDecimal("1000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten().setVCBSMonoReten(new BigDecimal("100000000000000")));
        Assertions.assertThrows(NumberFormatException.class, () -> new NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca().setQBCBioComb(new BigDecimal("1000000000000")));
    }

    private static NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem adRemIbs() {
        final NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao padrao = new NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao();
        padrao.setQBCMono(new BigDecimal("13"));
        padrao.setAdRemIBS(new BigDecimal("1.1"));
        padrao.setVIBSMono(new BigDecimal("14.30"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten reten = new NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten();
        reten.setqBCMonoReten(new BigDecimal("2"));
        reten.setAdRemIBSReten(new BigDecimal("1.1"));
        reten.setVIBSMonoReten(new BigDecimal("2.20"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem adRem = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem();
        adRem.setgMonoPadrao(padrao);
        adRem.setgMonoReten(reten);
        return adRem;
    }

    private static NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem adValoremCbs() {
        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao padrao = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao();
        padrao.setVBCMono(new BigDecimal("100"));
        padrao.setPAliqMonoCBS(new BigDecimal("8.8"));
        padrao.setVCBSMono(new BigDecimal("8.80"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten reten = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten();
        reten.setVBCMonoReten(new BigDecimal("50"));
        reten.setPAliqMonoReten(new BigDecimal("8.4"));
        reten.setVCBSMonoReten(new BigDecimal("4.20"));

        final NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem adValorem = new NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem();
        adValorem.setgMonoPadrao(padrao);
        adValorem.setgMonoReten(reten);
        return adValorem;
    }

    /**
     * Valida o grupo isolado contra o tipo TMonofasia, via um schema "envelope" no namespace da NF-e
     * que inclui os tipos basicos do pacote PL_010f.
     */
    private void validaContraSchema(final String xmlGerado) throws Exception {
        final String xml = xmlGerado.replace("<NFNotaInfoItemImpostoIBSCBSMonofasia>", "<gIBSCBSMono xmlns=\"" + NAMESPACE + "\">")
                .replace("</NFNotaInfoItemImpostoIBSCBSMonofasia>", "</gIBSCBSMono>");
        final String envelope = "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"" + NAMESPACE + "\" targetNamespace=\"" + NAMESPACE + "\" elementFormDefault=\"qualified\">"
                + "<xs:include schemaLocation=\"" + recurso("tiposBasico_v4.00.xsd") + "\"/>"
                + "<xs:include schemaLocation=\"" + recurso("DFeTiposBasicos_v1.00.xsd") + "\"/>"
                + "<xs:element name=\"gIBSCBSMono\" type=\"TMonofasia\"/>"
                + "</xs:schema>";
        final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource(new StringReader(envelope)));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
    }

    private static String recurso(final String xsd) {
        final URL url = NFNotaInfoItemImpostoIBSCBSMonofasiaLeiauteReformuladoTest.class.getClassLoader().getResource(PACOTE + xsd);
        Assertions.assertNotNull(url, xsd);
        return url.toString();
    }
}
