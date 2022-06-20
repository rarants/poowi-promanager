package br.rarants.inf.ufsm.service;

import br.rarants.inf.ufsm.dao.QuadrosDAO;
import br.rarants.inf.ufsm.dao.UsuarioDAO;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuadroService {
    public Quadro owner (Usuario usuario, Quadro quadro) throws SQLException, ClassNotFoundException {
        QuadrosDAO qdr_dao = new QuadrosDAO();
        quadro = qdr_dao.getQuadro(quadro);
        if (quadro.getUsuario().getId() == usuario.getId())
            return quadro;
        return null;
    }
}
