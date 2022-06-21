package br.rarants.inf.ufsm.service;

import br.rarants.inf.ufsm.dao.ColunasDAO;
import br.rarants.inf.ufsm.dao.QuadrosDAO;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;

import java.sql.SQLException;

public class ColunaService {
    public Coluna owner (Usuario usuario, Coluna coluna) throws SQLException, ClassNotFoundException {
        ColunasDAO col_dao = new ColunasDAO();
        coluna = col_dao.getColuna(coluna);
        if (coluna.getQuadro().getUsuario().getId() == usuario.getId())
            return coluna;
        return null;
    }
}
