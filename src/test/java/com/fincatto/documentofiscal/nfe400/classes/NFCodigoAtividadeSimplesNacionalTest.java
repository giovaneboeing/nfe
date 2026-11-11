package com.fincatto.documentofiscal.nfe400.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NFCodigoAtividadeSimplesNacionalTest {

    @Test
    public void deveRepresentarOCodigoCorretamente() {
        Assertions.assertEquals("01", NFCodigoAtividadeSimplesNacional.REVENDA_OU_INDUSTRIALIZADAS_ANEXO_I.getCodigo());
        Assertions.assertEquals("02", NFCodigoAtividadeSimplesNacional.DEMAIS_BENS_MATERIAIS_SEM_ICMS_ANEXO_I.getCodigo());
        Assertions.assertEquals("03", NFCodigoAtividadeSimplesNacional.INDUSTRIALIZADAS_ZFM_ANEXO_II.getCodigo());
        Assertions.assertEquals("04", NFCodigoAtividadeSimplesNacional.DEMAIS_BENS_MATERIAIS_ZFM_SEM_ICMS_ANEXO_II.getCodigo());
        Assertions.assertEquals("90", NFCodigoAtividadeSimplesNacional.OPERACOES_NAO_TRIBUTAVEIS.getCodigo());
    }

    @Test
    public void deveObterPeloCodigoCorretamente() {
        for (final NFCodigoAtividadeSimplesNacional atividade : NFCodigoAtividadeSimplesNacional.values()) {
            Assertions.assertEquals(atividade, NFCodigoAtividadeSimplesNacional.valueOfCodigo(atividade.getCodigo()));
        }
    }

    @Test
    public void deveObterNuloCasoNaoExistaCodigo() {
        Assertions.assertNull(NFCodigoAtividadeSimplesNacional.valueOfCodigo("1"));
        Assertions.assertNull(NFCodigoAtividadeSimplesNacional.valueOfCodigo("05"));
    }

    @Test
    public void deveRetornarStringficadoComCodigoEDescricao() {
        Assertions.assertEquals("90 - Operações não Tributáveis", NFCodigoAtividadeSimplesNacional.OPERACOES_NAO_TRIBUTAVEIS.toString());
    }
}
