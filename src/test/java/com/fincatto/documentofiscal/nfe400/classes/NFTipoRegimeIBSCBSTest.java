package com.fincatto.documentofiscal.nfe400.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NFTipoRegimeIBSCBSTest {

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assertions.assertEquals("1", NFTipoRegimeIBSCBS.SIMPLES_NACIONAL_COM_REGIME_REGULAR.getCodigo());
        Assertions.assertEquals("2", NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_SEM_REGIME_REGULAR.getCodigo());
        Assertions.assertEquals("3", NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_EXCESSO_SUBLIMITE_SEM_REGIME_REGULAR.getCodigo());
        Assertions.assertEquals("4", NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_COM_REGIME_REGULAR.getCodigo());
    }

    @Test
    public void deveObterPeloCodigoCorretamente() {
        Assertions.assertEquals(NFTipoRegimeIBSCBS.SIMPLES_NACIONAL_COM_REGIME_REGULAR, NFTipoRegimeIBSCBS.valueOfCodigo("1"));
        Assertions.assertEquals(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_SEM_REGIME_REGULAR, NFTipoRegimeIBSCBS.valueOfCodigo("2"));
        Assertions.assertEquals(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_EXCESSO_SUBLIMITE_SEM_REGIME_REGULAR, NFTipoRegimeIBSCBS.valueOfCodigo("3"));
        Assertions.assertEquals(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_COM_REGIME_REGULAR, NFTipoRegimeIBSCBS.valueOfCodigo("4"));
    }

    @Test
    public void deveObterNuloCasoNaoExistaCodigo() {
        Assertions.assertNull(NFTipoRegimeIBSCBS.valueOfCodigo("0"));
        Assertions.assertNull(NFTipoRegimeIBSCBS.valueOfCodigo("5"));
    }

    @Test
    public void deveRetornarStringficadoComCodigoEDescricao() {
        Assertions.assertTrue(NFTipoRegimeIBSCBS.SIMPLES_NACIONAL_COM_REGIME_REGULAR.toString().startsWith("1 - Contribuinte do Simples Nacional"));
    }
}
