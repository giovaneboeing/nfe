package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * UB91 gMonoPadrao do gIBSMonoAdValorem / UB101 gMonoPadrao do gCBSMonoAdValorem.
 * IBS: vBCMono, pAliqMonoUF, vIBSMonoUF, pAliqMonoMun, vIBSMonoMun, vIBSMono.
 * CBS: vBCMono, pAliqMonoCBS, vCBSMono.
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao extends DFBase {

    private static final long serialVersionUID = 6150372918463025871L;

    @Element(required = false)
    private String vBCMono; // UB91a / UB101a

    @Element(required = false)
    private String pAliqMonoUF; // UB91b

    @Element(required = false)
    private String vIBSMonoUF; // UB91c

    @Element(required = false)
    private String pAliqMonoMun; // UB91d

    @Element(required = false)
    private String vIBSMonoMun; // UB91e

    @Element(required = false)
    private String vIBSMono; // UB91f

    @Element(required = false)
    private String pAliqMonoCBS; // UB101b

    @Element(required = false)
    private String vCBSMono; // UB101c

    public String getVBCMono() {
        return vBCMono;
    }

    public void setVBCMono(BigDecimal vBCMono) {
        this.vBCMono = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vBCMono, "Valor tributado na monofasia");
    }

    public String getPAliqMonoUF() {
        return pAliqMonoUF;
    }

    public void setPAliqMonoUF(BigDecimal pAliqMonoUF) {
        this.pAliqMonoUF = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pAliqMonoUF, "Alíquota ad valorem do IBS Estadual");
    }

    public String getVIBSMonoUF() {
        return vIBSMonoUF;
    }

    public void setVIBSMonoUF(BigDecimal vIBSMonoUF) {
        this.vIBSMonoUF = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSMonoUF, "Valor do IBS monofásico Estadual");
    }

    public String getPAliqMonoMun() {
        return pAliqMonoMun;
    }

    public void setPAliqMonoMun(BigDecimal pAliqMonoMun) {
        this.pAliqMonoMun = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pAliqMonoMun, "Alíquota ad valorem do IBS Municipal");
    }

    public String getVIBSMonoMun() {
        return vIBSMonoMun;
    }

    public void setVIBSMonoMun(BigDecimal vIBSMonoMun) {
        this.vIBSMonoMun = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSMonoMun, "Valor do IBS monofásico Municipal");
    }

    public String getVIBSMono() {
        return vIBSMono;
    }

    public void setVIBSMono(BigDecimal vIBSMono) {
        this.vIBSMono = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSMono, "Valor do IBS monofásico");
    }

    public String getPAliqMonoCBS() {
        return pAliqMonoCBS;
    }

    public void setPAliqMonoCBS(BigDecimal pAliqMonoCBS) {
        this.pAliqMonoCBS = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pAliqMonoCBS, "Alíquota ad valorem da CBS");
    }

    public String getVCBSMono() {
        return vCBSMono;
    }

    public void setVCBSMono(BigDecimal vCBSMono) {
        this.vCBSMono = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vCBSMono, "Valor da CBS monofásica");
    }
}
