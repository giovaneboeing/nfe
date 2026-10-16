package com.fincatto.documentofiscal.validadores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DFXMLValidadorPacoteNfe400Test {

    @Test
    public void deveUsarPacoteAtualParaLeiauteAntigoDaMonofasia() {
        final String xml = "<NFe><gIBSCBSMono><gMonoPadrao><qBCMono>1.0000</qBCMono></gMonoPadrao></gIBSCBSMono></NFe>";
        Assertions.assertEquals("schemas/PL_010e_NT2025.002_v1.01", DFXMLValidador.pacoteNfe400(xml));
    }

    @Test
    public void deveUsarPacoteAtualParaNotaSemMonofasia() {
        Assertions.assertEquals("schemas/PL_010e_NT2025.002_v1.01", DFXMLValidador.pacoteNfe400("<NFe><infNFe/></NFe>"));
    }

    @Test
    public void deveUsarPacotePL010fParaLeiauteReformuladoDaMonofasia() {
        Assertions.assertEquals("schemas/PL_010f_v1.04", DFXMLValidador.pacoteNfe400("<NFe><gIBSCBSMono><gIBSMonoAdRem><gMonoPadrao/></gIBSMonoAdRem></gIBSCBSMono></NFe>"));
        Assertions.assertEquals("schemas/PL_010f_v1.04", DFXMLValidador.pacoteNfe400("<NFe><gIBSCBSMono><gIBSMonoAdValorem>"));
        Assertions.assertEquals("schemas/PL_010f_v1.04", DFXMLValidador.pacoteNfe400("<NFe><gIBSCBSMono><gCBSMonoAdRem>"));
        Assertions.assertEquals("schemas/PL_010f_v1.04", DFXMLValidador.pacoteNfe400("<nfe:NFe><nfe:gCBSMonoAdValorem>"));
    }

    @Test
    public void deveEncontrarOsSchemasDeNotaELoteNoPacotePL010f() {
        Assertions.assertNotNull(DFXMLValidador.class.getClassLoader().getResource("schemas/PL_010f_v1.04/nfe_v4.00.xsd"));
        Assertions.assertNotNull(DFXMLValidador.class.getClassLoader().getResource("schemas/PL_010f_v1.04/enviNFe_v4.00.xsd"));
    }
}
