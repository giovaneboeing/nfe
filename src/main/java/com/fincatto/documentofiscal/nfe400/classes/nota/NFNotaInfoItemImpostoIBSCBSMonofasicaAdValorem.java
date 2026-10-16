package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

/**
 * UB90 gIBSMonoAdValorem / UB100 gCBSMonoAdValorem - leiaute reformulado da tributacao monofasica
 * (NT 2025.002 v1.50). O mesmo grupo atende IBS e CBS: preencher apenas os campos do tributo
 * correspondente.
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem extends DFBase {

    private static final long serialVersionUID = -4826710395812736490L;

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao gMonoPadrao; // UB91 / UB101

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten gMonoReten; // UB92 / UB102

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet gMonoRet; // UB93 / UB103 (somente vIBSMonoRet ou vCBSMonoRet)

    @Element(required = false)
    private NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca gpBioDiferenca; // UB94 / UB104

    public NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao getgMonoPadrao() {
        return gMonoPadrao;
    }

    public void setgMonoPadrao(NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoPadrao gMonoPadrao) {
        this.gMonoPadrao = gMonoPadrao;
    }

    public NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten getgMonoReten() {
        return gMonoReten;
    }

    public void setgMonoReten(NFNotaInfoItemImpostoIBSCBSMonofasicaAdValoremGMonoReten gMonoReten) {
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
