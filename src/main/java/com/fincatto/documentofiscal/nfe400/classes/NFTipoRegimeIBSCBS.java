package com.fincatto.documentofiscal.nfe400.classes;

/**
 * C21a - Tipo de Regime de Apuracao do IBS e da CBS (tpRegimeIBSCBS - NT 2026.005).<br>
 * Informar somente nas situacoes abaixo; nas demais a tag nao deve ser informada.
 */
public enum NFTipoRegimeIBSCBS {

    SIMPLES_NACIONAL_COM_REGIME_REGULAR("1", "Contribuinte do Simples Nacional (CRT=1 ou 2) com opção pelo regime regular do IBS e da CBS"),
    NORMAL_PENDENTE_SIMPLES_NACIONAL_SEM_REGIME_REGULAR("2", "Contribuinte do Regime Normal (CRT=3) com opção pendente pelo CRT=1-Simples Nacional sem regime regular do IBS e da CBS"),
    NORMAL_PENDENTE_SIMPLES_NACIONAL_EXCESSO_SUBLIMITE_SEM_REGIME_REGULAR("3", "Contribuinte do Regime Normal (CRT=3) com opção pendente pelo CRT=2-Simples Nacional, excesso sublimite de receita bruta sem regime regular do IBS e da CBS"),
    NORMAL_PENDENTE_SIMPLES_NACIONAL_COM_REGIME_REGULAR("4", "Contribuinte do Regime Normal (CRT=3) com opção pendente pelo Simples Nacional (CRT=1 ou 2), com regime regular do IBS e da CBS");

    private final String codigo;
    private final String descricao;

    NFTipoRegimeIBSCBS(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static NFTipoRegimeIBSCBS valueOfCodigo(final String codigo) {
        for (final NFTipoRegimeIBSCBS tipoRegime : NFTipoRegimeIBSCBS.values()) {
            if (tipoRegime.getCodigo().equals(codigo)) {
                return tipoRegime;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
