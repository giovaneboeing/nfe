package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * UB115b - gIBSSN - Grupo de informacoes da composicao do valor do IBS para o Simples Nacional (NT 2026.005).
 */
public class NFNotaInfoItemImpostoIBSCBSTribSNIBS extends DFBase {
    private static final long serialVersionUID = -4693061582514327809L;

    @Element(name = "pIBSSN")
    private String percentualIBSSimplesNacional; // UB115c

    @Element(name = "vIBSSN")
    private String valorIBSSimplesNacional; // UB115d

    public String getPercentualIBSSimplesNacional() {
        return this.percentualIBSSimplesNacional;
    }

    /**
     * Aliquota efetiva do IBS para os optantes do Simples Nacional (pIBSSN - UB115c, 3v2-4).
     */
    public void setPercentualIBSSimplesNacional(final BigDecimal percentualIBSSimplesNacional) {
        this.percentualIBSSimplesNacional = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualIBSSimplesNacional, "Aliquota efetiva do IBS para os optantes do Simples Nacional");
    }

    public String getValorIBSSimplesNacional() {
        return this.valorIBSSimplesNacional;
    }

    /**
     * Valor do IBS para os optantes do Simples Nacional (vIBSSN - UB115d, 13v2).
     */
    public void setValorIBSSimplesNacional(final BigDecimal valorIBSSimplesNacional) {
        this.valorIBSSimplesNacional = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorIBSSimplesNacional, "Valor do IBS para os optantes do Simples Nacional");
    }
}
