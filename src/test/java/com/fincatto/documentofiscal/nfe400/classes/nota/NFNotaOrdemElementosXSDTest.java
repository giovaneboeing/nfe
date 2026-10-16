package com.fincatto.documentofiscal.nfe400.classes.nota;

import com.fincatto.documentofiscal.nfe400.FabricaDeObjetosFake;
import com.fincatto.documentofiscal.nfe400.classes.NFNotaMotivoDesoneracaoICMS;
import com.fincatto.documentofiscal.nfe400.classes.NFNotaInfoImpostoTributacaoICMS;
import com.fincatto.documentofiscal.nfe400.classes.NFNotaInfoImpostoTributacaoIBSCBS;
import com.fincatto.documentofiscal.nfe400.classes.NFNotaInfoItemModalidadeBCICMS;
import com.fincatto.documentofiscal.nfe400.classes.NFOrigem;
import com.fincatto.documentofiscal.nfe400.classes.NFTipoDeducaoIcms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Garante a ordem dos elementos exigida pelo XSD da NF-e (PL_010f) em grupos que estavam fora de ordem:
 * a SEFAZ rejeita o XML (erro de schema) quando a sequencia nao e respeitada.
 */
public class NFNotaOrdemElementosXSDTest {

    @Test
    public void dPrevEntregaDeveVirLogoAposDhSaiEnt() {
        final NFNotaInfoIdentificacao identificacao = FabricaDeObjetosFake.getNFNotaInfoIdentificacao();
        identificacao.setDataPrevistaEntrega(LocalDate.of(2026, 11, 3));

        assertOrdem(identificacao.toString(), "<dhSaiEnt>", "<dPrevEntrega>", "<tpNF>");
    }

    @Test
    public void gruposDoIbsCbsDevemSeguirAOrdemDoSchema() {
        final NFNotaInfoItemImpostoIBSCBS.GrupoAjusteCompetencia ajuste = new NFNotaInfoItemImpostoIBSCBS.GrupoAjusteCompetencia();
        ajuste.setDataCompetenciaApuracao(YearMonth.of(2026, 10));
        ajuste.setValorIBS(BigDecimal.ONE);
        ajuste.setValorCBS(BigDecimal.ONE);

        final NFNotaInfoIBSCBSGrupoEstornoCredito estorno = new NFNotaInfoIBSCBSGrupoEstornoCredito();
        estorno.setValorIbsEstornado(BigDecimal.ONE);
        estorno.setValorCbsEstornado(BigDecimal.ONE);

        final NFNotaInfoItemImpostoIBSCBSGCredPresOper.GCredPres credPresIbs = new NFNotaInfoItemImpostoIBSCBSGCredPresOper.GCredPres();
        credPresIbs.setPCredPres(BigDecimal.ONE);
        final NFNotaInfoItemImpostoIBSCBSGCredPresOper.GCredPres credPresCbs = new NFNotaInfoItemImpostoIBSCBSGCredPresOper.GCredPres();
        credPresCbs.setPCredPres(BigDecimal.ONE);
        final NFNotaInfoItemImpostoIBSCBSGCredPresOper credPresOper = new NFNotaInfoItemImpostoIBSCBSGCredPresOper();
        credPresOper.setValorBaseCalculoCreditoPresumido(BigDecimal.TEN);
        credPresOper.setClassificacaoCreditoPresumido("1");
        credPresOper.setGrupoIBSCreditoPresumido(credPresIbs);
        credPresOper.setGrupoCBSCreditoPresumido(credPresCbs);

        final NFNotaInfoItemImpostoIBSCBSCredPresIBSZFM credPresZfm = new NFNotaInfoItemImpostoIBSCBSCredPresIBSZFM();
        credPresZfm.setAnoMesReferenciaApuracao(LocalDate.of(2026, 10, 1));
        credPresZfm.setTpCredPresIBSZFM("1");
        credPresZfm.setVCredPresIBSZFM(BigDecimal.ONE);

        final NFNotaInfoItemImpostoIBSCBS ibsCbs = new NFNotaInfoItemImpostoIBSCBS();
        ibsCbs.setCst(NFNotaInfoImpostoTributacaoIBSCBS.values()[0]);
        ibsCbs.setcClassTrib("000001");
        ibsCbs.setGCredPresIBSZFM(credPresZfm);
        ibsCbs.setGrupoCreditoPresumidoOperacao(credPresOper);
        ibsCbs.setGrupoEstornoCredito(estorno);
        ibsCbs.setGrupoAjusteCompetencia(ajuste);

        assertOrdem(ibsCbs.toString(), "<gAjusteCompet>", "<gEstornoCred>", "<gCredPresOper>", "<gCredPresIBSZFM>");
    }

    @Test
    public void indDeduzDesonDeveVirAposMotDesICMSNoIcms90() {
        final NFNotaInfoItemImpostoICMS90 icms90 = new NFNotaInfoItemImpostoICMS90();
        icms90.setOrigem(NFOrigem.values()[0]);
        icms90.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.valueOfCodigo("90"));
        icms90.setValorICMSDesoneracao(BigDecimal.ONE);
        icms90.setDesoneracao(NFNotaMotivoDesoneracaoICMS.values()[0]);
        icms90.setIndicaDeduzDesoneracao(NFTipoDeducaoIcms.values()[0]);
        icms90.setValorICMSSTDesonerado(BigDecimal.ONE);
        icms90.setMotivoDesoneracaoICMSST(NFNotaMotivoDesoneracaoICMS.values()[0]);

        assertOrdem(icms90.toString(), "<vICMSDeson>", "<motDesICMS>", "<indDeduzDeson>", "<vICMSSTDeson>", "<motDesICMSST>");
    }

    @Test
    public void icms53DeveGerarCamposDoDiferimentoAdRemNoFinalDoGrupo() {
        final NFNotaInfoItemImpostoICMS53 icms53 = new NFNotaInfoItemImpostoICMS53();
        icms53.setOrigem(NFOrigem.values()[0]);
        icms53.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.CST_53);
        icms53.setQuantidadeBaseCalculo(new BigDecimal("13"));
        icms53.setPercentualAliquota(new BigDecimal("1.47"));
        icms53.setValorOperacao(new BigDecimal("19.11"));
        icms53.setPercentualDiferimento(new BigDecimal("50"));
        icms53.setValorTributoDiferido(new BigDecimal("9.56"));
        icms53.setValorTributo(new BigDecimal("9.55"));
        icms53.setQuantidadeBaseCalculoDiferida(new BigDecimal("6.5"));
        icms53.setPercentualAliquotaDiferida(new BigDecimal("1.47"));

        final String xml = icms53.toString();
        assertOrdem(xml, "<qBCMono>", "<adRemICMS>", "<vICMSMonoOp>", "<pDif>", "<vICMSMonoDif>", "<vICMSMono>", "<qBCMonoDif>", "<adRemICMSDif>");
        Assertions.assertTrue(xml.contains("<qBCMonoDif>6.5</qBCMonoDif><adRemICMSDif>1.47</adRemICMSDif>"), xml);
    }

    @Test
    public void icms90DeveGerarCamposDeDiferimentoNaOrdemDoSchema() {
        final NFNotaInfoItemImpostoICMS90 icms90 = new NFNotaInfoItemImpostoICMS90();
        icms90.setOrigem(NFOrigem.values()[0]);
        icms90.setSituacaoTributaria(NFNotaInfoImpostoTributacaoICMS.valueOfCodigo("90"));
        icms90.setModalidadeBCICMS(NFNotaInfoItemModalidadeBCICMS.values()[0]);
        icms90.setValorBC(new BigDecimal("100"));
        icms90.setPercentualReducaoBC(new BigDecimal("10"));
        icms90.setCodigoBeneficioFiscalRBC("SC123456");
        icms90.setPercentualAliquota(new BigDecimal("17"));
        icms90.setValorICMSOperacao(new BigDecimal("15.30"));
        icms90.setPercentualDiferimento(new BigDecimal("100"));
        icms90.setValorICMSDiferimento(new BigDecimal("15.30"));
        icms90.setValorTributo(BigDecimal.ZERO);
        icms90.setValorBCFundoCombatePobreza(new BigDecimal("100"));
        icms90.setPercentualFundoCombatePobreza(new BigDecimal("2"));
        icms90.setValorFundoCombatePobreza(new BigDecimal("2"));
        icms90.setPercentualDiferimentoFundoCombatePobreza(new BigDecimal("100"));
        icms90.setValorDiferimentoFundoCombatePobreza(new BigDecimal("2"));
        icms90.setValorEfetivoFundoCombatePobreza(BigDecimal.ZERO);

        assertOrdem(icms90.toString(), "<modBC>", "<vBC>", "<pRedBC>", "<cBenefRBC>", "<pICMS>", "<vICMSOp>", "<pDif>", "<vICMSDif>", "<vICMS>",
                "<vBCFCP>", "<pFCP>", "<vFCP>", "<pFCPDif>", "<vFCPDif>", "<vFCPEfet>");
    }

    private static void assertOrdem(final String xml, final String... tags) {
        int anterior = -1;
        for (final String tag : tags) {
            final int posicao = xml.indexOf(tag);
            Assertions.assertTrue(posicao > anterior, () -> tag + " fora da ordem do XSD em: " + xml);
            anterior = posicao;
        }
    }
}
