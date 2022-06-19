package br.rarants.inf.ufsm.model;

public class Coluna {
    private int id;
    private String titulo;
    private Integer ordem;
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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    @Override
    public String toString() {
        return "Coluna{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", ordem=" + ordem +
                ", quadro=" + quadro.toString() +
                '}';
    }
}
