package br.rarants.inf.ufsm.dao;

import br.rarants.inf.ufsm.connection.Conexao;
import br.rarants.inf.ufsm.model.Cartao;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class QuadrosDAO {
    private Boolean findColunaInList(Coluna coluna, ArrayList<Coluna> colunas) {
        for(Coluna cl : colunas) {
            if(cl.getId() == coluna.getId()) {
                return true;
            }
        }
        return false;
    }
    private Boolean findCartaoInList(Cartao cartao, ArrayList<Cartao> cartoes) {
        for(Cartao ct : cartoes) {
            if(ct.getId() == cartao.getId()) {
                return true;
            }
        }
        return false;
    }
    private ArrayList<Coluna> setColunaAndCartao(ResultSet rs, Quadro quadro, ArrayList<Coluna> colunas) throws SQLException {
        Coluna coluna = new Coluna();
        coluna.setId(rs.getInt("id_coluna"));
        coluna.setTitulo(rs.getString("coluna_titulo"));
        coluna.setOrdem(0);
        coluna.setQuadro(quadro);
        coluna.setCartaoArrayList(new ArrayList<Cartao>());

        Cartao cartao = new Cartao();
        cartao.setId(rs.getInt("id_cartao"));
        cartao.setTitulo(rs.getString("cartao_titulo"));
        cartao.setStatus(rs.getBoolean("status"));
        cartao.setOrdem(0);
        cartao.setDataInicio(rs.getDate("data_inicio"));
        cartao.setDataTermino(rs.getDate("data_termino"));
        cartao.setDataUpdate(rs.getDate("data_atualizacao"));
        cartao.setColuna(coluna);

        if (findColunaInList(coluna, colunas) == true) {
            if (findCartaoInList(cartao, coluna.getCartaoArrayList()) != true && cartao.getId()  != 0) {
                ArrayList<Cartao> cartoes = coluna.getCartaoArrayList();
                cartoes.add(cartao);
                coluna.setCartaoArrayList(cartoes);
            }
        } else if (!coluna.getTitulo().equals("")){
            colunas.add(coluna);
            if (findCartaoInList(cartao, coluna.getCartaoArrayList()) != true && cartao.getId()  != 0) {
                ArrayList<Cartao> cartoes = coluna.getCartaoArrayList();
                cartoes.add(cartao);
                coluna.setCartaoArrayList(cartoes);
            }
        }
        return colunas;
    }

    public Quadro getQuadro(Quadro quadro) throws SQLException, ClassNotFoundException {
        Usuario usuario = quadro.getUsuario();
        String sql = "SELECT q.descricao quadro_descricao, q.titulo quadro_titulo, " +
                "cl.id id_coluna, cl.titulo coluna_titulo, " +
                "ct.id id_cartao, ct.titulo cartao_titulo, status, " +
                "data_inicio, data_termino, data_atualizacao " +
                "FROM quadros q " +
                "LEFT JOIN colunas cl " +
                "LEFT JOIN cartoes ct " +
                "ON cl.id = ct.id_coluna " +
                "ON q.id = cl.id_quadro " +
                "WHERE q.id = ? AND q.id_usuario = ? " +
                "ORDER BY cl.id,  ct.id;";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, quadro.getId());
            stmt.setInt(2, usuario.getId());
            rs = stmt.executeQuery();
            ArrayList<Coluna> colunas = new ArrayList<Coluna>();
            while (rs.next()) {
                quadro.setTitulo(rs.getString("quadro_titulo"));
                quadro.setDescricao(rs.getString("quadro_descricao"));
                colunas = setColunaAndCartao(rs, quadro, colunas);
            }
            quadro.setColunaArrayList(colunas);
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

    public Boolean deleteQuadro(Integer id, Integer id_usuario) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("DELETE FROM quadros WHERE id = ? AND id_usuario = ?");
            stmt.setInt(1, id);
            stmt.setInt(2, id_usuario);
            stmt.executeUpdate();
            return true;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
}
