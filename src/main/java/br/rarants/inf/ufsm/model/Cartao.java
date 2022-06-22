package br.rarants.inf.ufsm.model;

import java.sql.Date;
import java.util.List;

public class Cartao {
    private int id;
    private String titulo;
    private String descricao;
    private Boolean status;
    private Integer ordem;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataUpdate;
    private Coluna coluna;
    private List<Etiqueta> etiquetaList;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public void setColuna(Coluna coluna) {
        this.coluna = coluna;
    }

    public List<Etiqueta> getEtiquetaList() {
        return etiquetaList;
    }

    public void setEtiquetaList(List<Etiqueta> etiquetaList) {
        this.etiquetaList = etiquetaList;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", ordem=" + ordem +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", dataUpdate=" + dataUpdate +
                ", etiquetaList=" + etiquetaList +
                '}';
    }
}
