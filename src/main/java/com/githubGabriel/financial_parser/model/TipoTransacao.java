package com.githubGabriel.financial_parser.model;

public enum TipoTransacao {
    DEBITO(1, "Débito", "Entrada", "+"),
    BOLETO(2, "Boleto", "Saída", "-"),
    FINANCIAMENTO(3, "Financiamento", "Saída", "-"),
    CREDITO(4, "Crédito", "Entrada", "+"),
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo", "Entrada", "+"),
    VENDAS(6, "Vendas", "Entrada", "+"),
    RECEBIMENTO_TED(7, "Recebimento TED", "Entrada", "+"),
    RECEBIMENTO_DOC(8, "Recebimento DOC", "Entrada", "+"),
    ALUGUEL(9, "Aluguel", "Saída", "-");


    private Integer codigo;
    private String descricao;
    private String natureza;
    private String sinal;

    TipoTransacao(Integer codigo, String descricao, String natureza, String sinal) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.natureza = natureza;
        this.sinal = sinal;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNatureza() {
        return natureza;
    }

    public String getSinal() {
        return sinal;
    }

    public static TipoTransacao  fromCodigo(Integer codigo){
        for (TipoTransacao tipo : TipoTransacao.values()){
            if (tipo.codigo== codigo){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de transacao incorreta."+ codigo);
    }
}
