package br.rarants.inf.ufsm.service;

import br.rarants.inf.ufsm.dao.UsuarioDAO;
import br.rarants.inf.ufsm.model.Usuario;

import java.sql.SQLException;

public class UsuarioService {
    public Usuario autenticado (String email, String senha) throws SQLException, ClassNotFoundException {
        UsuarioDAO usr_dao = new UsuarioDAO();
        Usuario usuario = usr_dao.getUsuario(email);
        if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        else return null;
    }
}
