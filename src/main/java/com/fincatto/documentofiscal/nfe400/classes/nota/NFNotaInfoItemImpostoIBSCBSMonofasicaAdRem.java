package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

/**
 * UB85a gIBSMonoAdRem / UB95a gCBSMonoAdRem - leiaute reformulado da tributacao monofasica
 * (NT 2025.002 v1.50). O mesmo grupo atende IBS e CBS: preencher apenas os campos do tributo
 * correspondente (ex: adRemIBS/vIBSMono no gIBSMonoAdRem; adRemCBS/vCBSMono no gCBSMonoAdRem).
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem extends DFBase {

    private static final long serialVersionUID = 2318470953861207415L;

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao gMonoPadrao; // UB86 / UB96

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten gMonoReten; // UB87 / UB97

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet gMonoRet; // UB88 / UB98 (somente vIBSMonoRet ou vCBSMonoRet)

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca gpBioDiferenca; // UB89 / UB99

    public NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao getgMonoPadrao() {
        return gMonoPadrao;
    }

    public void setgMonoPadrao(NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao gMonoPadrao) {
        this.gMonoPadrao = gMonoPadrao;
    }

    public NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten getgMonoReten() {
        return gMonoReten;
    }

    public void setgMonoReten(NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten gMonoReten) {
        this.gMonoReten = gMonoReten;
    }

    public NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet getgMonoRet() {
        return gMonoRet;
    }

    public void setgMonoRet(NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet gMonoRet) {
        this.gMonoRet = gMonoRet;
    }

    public NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca getGpBioDiferenca() {
        return gpBioDiferenca;
    }

    public void setGpBioDiferenca(NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca gpBioDiferenca) {
        this.gpBioDiferenca = gpBioDiferenca;
    }
}
