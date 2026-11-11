package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;

/**
 * UB115a - gTribSN - Grupo de informacoes da composicao do valor do IBS e da CBS para o Simples Nacional (NT 2026.005).<br>
 * Filho do grupo IBSCBS (UB12). O grupo gCBSSN e obrigatorio; o gIBSSN e opcional.
 */
public class NFNotaInfoItemImpostoIBSCBSTribSN extends DFBase {
    private static final long serialVersionUID = 3166205046371268337L;

    @Element(name = "gIBSSN", required = false)
    private NFNotaInfoItemImpostoIBSCBSTribSNIBS grupoIBSSimplesNacional; // UB115b

    @Element(name = "gCBSSN")
    private NFNotaInfoItemImpostoIBSCBSTribSNCBS grupoCBSSimplesNacional; // UB115e

    public NFNotaInfoItemImpostoIBSCBSTribSNIBS getGrupoIBSSimplesNacional() {
        return this.grupoIBSSimplesNacional;
    }

    /**
     * Grupo de informacoes da composicao do valor do IBS para o Simples Nacional (gIBSSN - UB115b).
     */
    public void setGrupoIBSSimplesNacional(final NFNotaInfoItemImpostoIBSCBSTribSNIBS grupoIBSSimplesNacional) {
        this.grupoIBSSimplesNacional = grupoIBSSimplesNacional;
    }

    public NFNotaInfoItemImpostoIBSCBSTribSNCBS getGrupoCBSSimplesNacional() {
        return this.grupoCBSSimplesNacional;
    }

    /**
     * Grupo de informacoes da composicao do valor da CBS para o Simples Nacional (gCBSSN - UB115e). Obrigatorio.
     */
    public void setGrupoCBSSimplesNacional(final NFNotaInfoItemImpostoIBSCBSTribSNCBS grupoCBSSimplesNacional) {
        this.grupoCBSSimplesNacional = grupoCBSSimplesNacional;
    }
}
