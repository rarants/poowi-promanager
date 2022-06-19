package br.rarants.inf.ufsm.model;

public class Etiqueta {
    private int id;
    private String titulo;
    private String cor;
    private Quadro quadro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cor='" + cor + '\'' +
                ", quadro=" + quadro.toString() +
                '}';
    }
}
