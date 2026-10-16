package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * UB92 gMonoReten do gIBSMonoAdValorem / UB102 gMonoReten do gCBSMonoAdValorem.
 * IBS: vBCMonoReten, pAliqMonoReten, vIBSMonoReten. CBS: vBCMonoReten, pAliqMonoReten, vCBSMonoReten.
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten extends DFBase {

    private static final long serialVersionUID = -2873015649128374560L;

    @Element(required = false)
    private String vBCMonoReten; // UB92a / UB102a

    @Element(required = false)
    private String pAliqMonoReten; // UB92b / UB102b

    @Element(required = false)
    private String vIBSMonoReten; // UB92c

    @Element(required = false)
    private String vCBSMonoReten; // UB102c

    public String getVBCMonoReten() {
        return vBCMonoReten;
    }

    public void setVBCMonoReten(BigDecimal vBCMonoReten) {
        this.vBCMonoReten = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vBCMonoReten, "Valor tributado sujeito à retenção na monofasia");
    }

    public String getPAliqMonoReten() {
        return pAliqMonoReten;
    }

    public void setPAliqMonoReten(BigDecimal pAliqMonoReten) {
        this.pAliqMonoReten = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pAliqMonoReten, "Alíquota ad valorem sujeita à retenção");
    }

    public String getVIBSMonoReten() {
        return vIBSMonoReten;
    }

    public void setVIBSMonoReten(BigDecimal vIBSMonoReten) {
        this.vIBSMonoReten = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSMonoReten, "Valor do IBS monofásico sujeito à retenção");
    }

    public String getVCBSMonoReten() {
        return vCBSMonoReten;
    }

    public void setVCBSMonoReten(BigDecimal vCBSMonoReten) {
        this.vCBSMonoReten = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vCBSMonoReten, "Valor da CBS monofásica sujeita à retenção");
    }
}
