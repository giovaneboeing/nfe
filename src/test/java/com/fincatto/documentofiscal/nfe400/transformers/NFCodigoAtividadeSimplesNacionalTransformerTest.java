package com.fincatto.documentofiscal.nfe400.transformers;

import com.fincatto.documentofiscal.nfe400.classes.NFCodigoAtividadeSimplesNacional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NFCodigoAtividadeSimplesNacionalTransformerTest {

    @Test
    public void deveTestarLeitura() {
        Assertions.assertNull(new NFCodigoAtividadeSimplesNacionalTransformer().read("1"));
        Assertions.assertEquals(NFCodigoAtividadeSimplesNacional.REVENDA_OU_INDUSTRIALIZADAS_ANEXO_I, new NFCodigoAtividadeSimplesNacionalTransformer().read("01"));
        Assertions.assertEquals(NFCodigoAtividadeSimplesNacional.OPERACOES_NAO_TRIBUTAVEIS, new NFCodigoAtividadeSimplesNacionalTransformer().read("90"));
    }

    @Test
    public void deveTestarEscrita() {
        Assertions.assertEquals("03", new NFCodigoAtividadeSimplesNacionalTransformer().write(NFCodigoAtividadeSimplesNacional.INDUSTRIALIZADAS_ZFM_ANEXO_II));
        Assertions.assertEquals("90", new NFCodigoAtividadeSimplesNacionalTransformer().write(NFCodigoAtividadeSimplesNacional.OPERACOES_NAO_TRIBUTAVEIS));
    }
}
