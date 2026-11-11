package com.fincatto.documentofiscal.nfe400.transformers;

import com.fincatto.documentofiscal.nfe400.classes.NFTipoRegimeIBSCBS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NFTipoRegimeIBSCBSTransformerTest {

    @Test
    public void deveTestarLeitura() {
        Assertions.assertNull(new NFTipoRegimeIBSCBSTransformer().read("0"));
        Assertions.assertEquals(NFTipoRegimeIBSCBS.SIMPLES_NACIONAL_COM_REGIME_REGULAR, new NFTipoRegimeIBSCBSTransformer().read("1"));
        Assertions.assertEquals(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_COM_REGIME_REGULAR, new NFTipoRegimeIBSCBSTransformer().read("4"));
    }

    @Test
    public void deveTestarEscrita() {
        Assertions.assertEquals("2", new NFTipoRegimeIBSCBSTransformer().write(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_SEM_REGIME_REGULAR));
        Assertions.assertEquals("3", new NFTipoRegimeIBSCBSTransformer().write(NFTipoRegimeIBSCBS.NORMAL_PENDENTE_SIMPLES_NACIONAL_EXCESSO_SUBLIMITE_SEM_REGIME_REGULAR));
    }
}
