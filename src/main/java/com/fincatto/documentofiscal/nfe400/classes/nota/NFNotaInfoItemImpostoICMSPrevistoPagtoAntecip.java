package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * NB01 - Grupo do ICMS Previsto no Pagamento Antecipado (NT 2026.008).<br>
 * Utilizado exclusivamente na NF-e (modelo 55) de pagamento antecipado: tpNFDebito = 06 ou tpOperGov = 4.
 */
public class NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip extends DFBase {
    private static final long serialVersionUID = -2416307912358441735L;

    @Element(name = "vICMSPrevisto")
    private String valorICMSPrevisto;

    /**
     * Valor do ICMS que incidira no momento do fornecimento futuro (NB02).<br>
     * Informar 0 quando nao houver ICMS devido no fornecimento.
     */
    public void setValorICMSPrevisto(final BigDecimal valorICMSPrevisto) {
        this.valorICMSPrevisto = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorICMSPrevisto, "Valor do ICMS Previsto no Pagamento Antecipado");
    }

    public String getValorICMSPrevisto() {
        return this.valorICMSPrevisto;
    }
}
