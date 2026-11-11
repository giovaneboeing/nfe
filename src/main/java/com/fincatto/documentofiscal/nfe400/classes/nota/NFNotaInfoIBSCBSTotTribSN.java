package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * W59h - gTribSN - Grupo de totais da composicao do valor do IBS e da CBS para o Simples Nacional (NT 2026.005).<br>
 * Filho do grupo IBSCBSTot (W34). Obrigatorio quando algum item informar gTribSN (UB115a) e proibido caso contrario.
 */
public class NFNotaInfoIBSCBSTotTribSN extends DFBase {
    private static final long serialVersionUID = -7385297164905162741L;

    @Element(name = "vIBSSN")
    private String valorTotalIBSSimplesNacional; // W59i

    @Element(name = "vCBSSN")
    private String valorTotalCBSSimplesNacional; // W59j

    public String getValorTotalIBSSimplesNacional() {
        return this.valorTotalIBSSimplesNacional;
    }

    /**
     * Valor total do IBS para os optantes do Simples Nacional (vIBSSN - W59i, 13v2): soma dos vIBSSN dos itens.
     */
    public void setValorTotalIBSSimplesNacional(final BigDecimal valorTotalIBSSimplesNacional) {
        this.valorTotalIBSSimplesNacional = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorTotalIBSSimplesNacional, "Valor total do IBS para os optantes do Simples Nacional");
    }

    public String getValorTotalCBSSimplesNacional() {
        return this.valorTotalCBSSimplesNacional;
    }

    /**
     * Valor total da CBS para os optantes do Simples Nacional (vCBSSN - W59j, 13v2): soma dos vCBSSN dos itens.
     */
    public void setValorTotalCBSSimplesNacional(final BigDecimal valorTotalCBSSimplesNacional) {
        this.valorTotalCBSSimplesNacional = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorTotalCBSSimplesNacional, "Valor total da CBS para os optantes do Simples Nacional");
    }
}
