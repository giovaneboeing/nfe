package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

/**
 * gpBioDiferenca (UB89/UB94/UB99/UB104) - mistura de EAC (LC 214/2025, Art. 179, II, a e b).
 * IBS: qBCBioComb, vIBSDiferenca. CBS: qBCBioComb, vCBSDiferenca.
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasicaGpBioDiferenca extends DFBase {

    private static final long serialVersionUID = 4091726358120947316L;

    @Element(required = false)
    private String qBCBioComb;

    @Element(required = false)
    private String vIBSDiferenca;

    @Element(required = false)
    private String vCBSDiferenca;

    public String getQBCBioComb() {
        return qBCBioComb;
    }

    public void setQBCBioComb(BigDecimal qBCBioComb) {
        this.qBCBioComb = DFBigDecimalValidador.tamanho11Com4CasasDecimais(qBCBioComb, "Quantidade de Biocombustível (EAC)");
    }

    public String getVIBSDiferenca() {
        return vIBSDiferenca;
    }

    public void setVIBSDiferenca(BigDecimal vIBSDiferenca) {
        this.vIBSDiferenca = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSDiferenca, "Valor do IBS da diferença de mistura de EAC");
    }

    public String getVCBSDiferenca() {
        return vCBSDiferenca;
    }

    public void setVCBSDiferenca(BigDecimal vCBSDiferenca) {
        this.vCBSDiferenca = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vCBSDiferenca, "Valor da CBS da diferença de mistura de EAC");
    }
}
