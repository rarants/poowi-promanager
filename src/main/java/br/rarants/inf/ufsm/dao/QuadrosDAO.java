package br.rarants.inf.ufsm.dao;

import br.rarants.inf.ufsm.connection.Conexao;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuadrosDAO {
    public Quadro getQuadro(Quadro quadro) throws SQLException, ClassNotFoundException {
        Usuario usuario = quadro.getUsuario();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("SELECT * FROM quadros WHERE id = ?");
            stmt.setInt(1, quadro.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                quadro = new Quadro();
                quadro.setId(rs.getInt("id"));
                quadro.setTitulo(rs.getString("titulo"));
                quadro.setDescricao(rs.getString("descricao"));
                quadro.setPublico(rs.getBoolean("publico"));
                quadro.setUsuario(usuario);
            }
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return quadro;
    }

    public ArrayList<Quadro> getQuadros(Usuario usuario) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Quadro> quadros = new ArrayList<Quadro>();
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("SELECT * FROM quadros WHERE id_usuario = ?");
            stmt.setInt(1, usuario.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Quadro quadro = new Quadro();
                quadro.setId(rs.getInt("id"));
                quadro.setTitulo(rs.getString("titulo"));
                quadro.setDescricao(rs.getString("descricao"));
                quadro.setPublico(rs.getBoolean("publico"));
                quadro.setUsuario(usuario);
                quadros.add(quadro);
            }
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return quadros;
    }

    public Quadro postQuadro(Quadro quadro) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("INSERT INTO quadros VALUES (DEFAULT, ?, ?, ?, ?)");
            stmt.setString(1, quadro.getTitulo());
            stmt.setString(2, quadro.getDescricao());
            stmt.setBoolean(3, quadro.getPublico());
            stmt.setInt(4, quadro.getUsuario().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                quadro.setId(rs.getInt("id"));
            }
            return quadro;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

    public Quadro putQuadro(Quadro quadro) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("UPDATE quadros SET titulo = ?, descricao = ?, publico = ?" +
                    "WHERE id = ? AND id_usuario = ?");
            stmt.setString(1, quadro.getTitulo());
            stmt.setString(2, quadro.getDescricao());
            stmt.setBoolean(3, quadro.getPublico());
            stmt.setInt(4, quadro.getId());
            stmt.setInt(5, quadro.getUsuario().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                quadro.setId(rs.getInt("id"));
            }
            return quadro;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

}
