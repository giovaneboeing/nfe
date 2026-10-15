package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * NB01 - Grupo do ICMS Previsto no Pagamento Antecipado (NT 2026.008).
 */
public class NFNotaInfoItemImpostoICMSPrevistoPagtoAntecip extends DFBase {
    private static final long serialVersionUID = 6170318752730157412L;

    @Element(name = "vICMSPrevisto")
    private String valorICMSPrevisto; // NB02

    public String getValorICMSPrevisto() {
        return this.valorICMSPrevisto;
    }

    /**
     * Valor do ICMS que incidira no momento do fornecimento futuro. Informar 0 quando nao houver ICMS devido.
     */
    public void setValorICMSPrevisto(final BigDecimal valorICMSPrevisto) {
        this.valorICMSPrevisto = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorICMSPrevisto, "Valor do ICMS Previsto");
    }
}
