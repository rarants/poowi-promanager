package br.rarants.inf.ufsm.dao;

import br.rarants.inf.ufsm.model.Usuario;

import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario getUsuario(String email) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario();
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            }
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return user;*/
        Usuario usuario = new Usuario();
        usuario.setEmail("email@mail.com");
        usuario.setSenha("12356");
        return usuario;
    }
}