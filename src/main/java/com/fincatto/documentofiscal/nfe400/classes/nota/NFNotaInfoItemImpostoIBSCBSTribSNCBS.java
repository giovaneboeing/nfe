package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * UB115e - gCBSSN - Grupo de informacoes da composicao do valor da CBS para o Simples Nacional (NT 2026.005).
 */
public class NFNotaInfoItemImpostoIBSCBSTribSNCBS extends DFBase {
    private static final long serialVersionUID = 1928458036870771566L;

    @Element(name = "pCBSSN")
    private String percentualCBSSimplesNacional; // UB115f

    @Element(name = "vCBSSN")
    private String valorCBSSimplesNacional; // UB115g

    public String getPercentualCBSSimplesNacional() {
        return this.percentualCBSSimplesNacional;
    }

    /**
     * Aliquota efetiva da CBS para os optantes do Simples Nacional (pCBSSN - UB115f, 3v2-4).
     */
    public void setPercentualCBSSimplesNacional(final BigDecimal percentualCBSSimplesNacional) {
        this.percentualCBSSimplesNacional = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(percentualCBSSimplesNacional, "Aliquota efetiva da CBS para os optantes do Simples Nacional");
    }

    public String getValorCBSSimplesNacional() {
        return this.valorCBSSimplesNacional;
    }

    /**
     * Valor da CBS para os optantes do Simples Nacional (vCBSSN - UB115g, 13v2).
     */
    public void setValorCBSSimplesNacional(final BigDecimal valorCBSSimplesNacional) {
        this.valorCBSSimplesNacional = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorCBSSimplesNacional, "Valor da CBS para os optantes do Simples Nacional");
    }
}
