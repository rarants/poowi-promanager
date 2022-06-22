package br.rarants.inf.ufsm.dao;

import br.rarants.inf.ufsm.connection.Conexao;
import br.rarants.inf.ufsm.model.Cartao;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoesDAO {
    public Cartao getCartao(Cartao cartao) throws SQLException, ClassNotFoundException {
        Coluna coluna = cartao.getColuna();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("SELECT * FROM cartoes WHERE id = ?");
            stmt.setInt(1, cartao.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                cartao = new Cartao();
                cartao.setId(rs.getInt("id"));
                cartao.setTitulo(rs.getString("titulo"));
                cartao.setStatus(rs.getBoolean("status"));
                cartao.setDescricao(rs.getString("descricao"));
                cartao.setDataInicio(rs.getDate("data_inicio"));
                cartao.setDataTermino(rs.getDate("data_termino"));
                cartao.setDataUpdate(rs.getDate("data_atualizacao"));
                cartao.setColuna(coluna);
            }
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return cartao;
    }

    public Cartao postCartao(Cartao cartao) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Coluna coluna = cartao.getColuna();
        System.out.println("POST -->" + cartao.toString());
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("INSERT INTO cartoes VALUES (DEFAULT, ?, ?, DEFAULT, ?, ?, DEFAULT, ?, ?)");
            stmt.setString(1, cartao.getTitulo());
            stmt.setBoolean(2, cartao.getStatus());
            stmt.setDate(3, cartao.getDataInicio());
            stmt.setDate(4, cartao.getDataTermino());
            stmt.setInt(5, coluna.getId());
            stmt.setString(6, cartao.getDescricao());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                cartao.setId(rs.getInt("id"));
            }
            return cartao;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

    public Cartao putCartao(Cartao cartao) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE cartoes SET " +
                "titulo = ?, " +
                "status = ?, " +
                "descricao = ?, " +
                "data_inicio = ?, " +
                "data_termino = ? " +
                "WHERE id = ?";
        System.out.println("PUT -->" + cartao.toString());
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cartao.getTitulo());
            stmt.setBoolean(2, cartao.getStatus());
            stmt.setString(3, cartao.getDescricao());
            stmt.setDate(4, cartao.getDataInicio());
            stmt.setDate(5, cartao.getDataTermino());
            stmt.setInt(6, cartao.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                cartao.setId(rs.getInt("id"));
            }
            return cartao;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

    public Boolean deleteCartao(Integer id) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("DELETE FROM cartoes WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
}
