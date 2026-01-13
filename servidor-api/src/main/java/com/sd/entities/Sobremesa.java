package com.sd.entities; // CORREÇÃO: Deve ser entities, pois está na pasta entities

/**
 * Representa uma sobremesa no cardápio do restaurante.
 * No Trabalho 3, serve como modelo de dados para a API REST.
 */
public class Sobremesa {
    // serialVersionUID removido (não usamos mais serialização RMI)

    private String nome;
    private String descricao;
    private double preco;
    private boolean temLactose;

    /**
     * Construtor padrão vazio (Obrigatório para o Gson/SparkJava converter JSON)
     */
    public Sobremesa() {}

    /**
     * Construtor completo
     */
    public Sobremesa(String nome, String descricao, double preco, boolean temLactose) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.temLactose = temLactose;
    }

    // --- GETTERS E SETTERS (Obrigatórios para a API funcionar) ---

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean isTemLactose() { return temLactose; }
    public void setTemLactose(boolean temLactose) { this.temLactose = temLactose; }

    @Override
    public String toString() {
        return nome + " - " + descricao + " (R$" + String.format("%.2f", preco) + ")" + 
               (temLactose ? " ⚠️ contém lactose" : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sobremesa sobremesa = (Sobremesa) obj;
        return nome != null && nome.equalsIgnoreCase(sobremesa.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.toLowerCase().hashCode() : 0;
    }
}