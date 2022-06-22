package br.rarants.inf.ufsm.service;

import br.rarants.inf.ufsm.dao.CartoesDAO;
import br.rarants.inf.ufsm.dao.ColunasDAO;
import br.rarants.inf.ufsm.model.Cartao;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Usuario;

import java.sql.SQLException;

public class CartaoService {
    public Cartao owner (Usuario usuario, Cartao cartao) throws SQLException, ClassNotFoundException {
        CartoesDAO crt_dao = new CartoesDAO();
        cartao = crt_dao.getCartao(cartao);
        if (crt_dao.getCartao(cartao).getColuna().getQuadro().getUsuario().getId() == usuario.getId())
            return cartao;
        return null;
    }
}
