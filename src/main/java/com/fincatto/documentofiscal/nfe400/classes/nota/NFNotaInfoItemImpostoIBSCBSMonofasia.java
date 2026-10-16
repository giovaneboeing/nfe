package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import java.math.BigDecimal;
import org.simpleframework.xml.Element;

/**
 * UB84
 *
 * @author Edivaldo Merlo Stens
 * @author Ommega Data
 */
public class NFNotaInfoItemImpostoIBSCBSMonofasia extends DFBase {

  private static final long serialVersionUID = -366528394939416671L;

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoPadrao gMonoPadrao; // UB84a

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoReten gMonoReten; // UB90

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoRet gMonoRet; // UB94

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoDif gMonoDif; // UB99

  // leiaute reformulado (NT 2025.002 v1.50): substitui os grupos acima a partir da data de transicao
  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem gIBSMonoAdRem; // UB85a

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem gIBSMonoAdValorem; // UB90

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem gCBSMonoAdRem; // UB95a

  @Element(required = false)
  private NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem gCBSMonoAdValorem; // UB100

  // UB84 -x- Sequência XML
  @Element(required = true)
  private String vTotIBSMonoItem; // UB104

  @Element(required = true)
  private String vTotCBSMonoItem; // UB105

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

  public NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoDif getgMonoDif() {
    return gMonoDif;
  }

  public void setgMonoDif(NFNotaInfoItemImpostoIBSCBSMonofasicaGMonoDif gMonoDif) {
    this.gMonoDif = gMonoDif;
  }

  public NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem getgIBSMonoAdRem() {
    return gIBSMonoAdRem;
  }

  public void setgIBSMonoAdRem(NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem gIBSMonoAdRem) {
    this.gIBSMonoAdRem = gIBSMonoAdRem;
  }

  public NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem getgIBSMonoAdValorem() {
    return gIBSMonoAdValorem;
  }

  public void setgIBSMonoAdValorem(NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem gIBSMonoAdValorem) {
    this.gIBSMonoAdValorem = gIBSMonoAdValorem;
  }

  public NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem getgCBSMonoAdRem() {
    return gCBSMonoAdRem;
  }

  public void setgCBSMonoAdRem(NFNotaInfoItemImpostoIBSCBSMonofasicaAdRem gCBSMonoAdRem) {
    this.gCBSMonoAdRem = gCBSMonoAdRem;
  }

  public NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem getgCBSMonoAdValorem() {
    return gCBSMonoAdValorem;
  }

  public void setgCBSMonoAdValorem(NFNotaInfoItemImpostoIBSCBSMonofasicaAdValorem gCBSMonoAdValorem) {
    this.gCBSMonoAdValorem = gCBSMonoAdValorem;
  }

  public String getVTotIBSMonoItem() {
    return vTotIBSMonoItem;
  }

  public void setVTotIBSMonoItem(BigDecimal vTotIBSMonoItem) {
    this.vTotIBSMonoItem = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vTotIBSMonoItem, "Total de IBS Monofásico");
  }

  public String getVTotCBSMonoItem() {
    return vTotCBSMonoItem;
  }

  public void setVTotCBSMonoItem(BigDecimal vTotCBSMonoItem) {
    this.vTotCBSMonoItem = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vTotCBSMonoItem, "Total da CBS Monofásica");
  }

}
