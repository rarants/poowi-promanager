package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.CartoesDAO;
import br.rarants.inf.ufsm.model.Cartao;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;
import br.rarants.inf.ufsm.service.QuadroService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("cartao")
public class CartaoController extends HttpServlet {    
    private void getQuadro(HttpServletRequest req, Integer id) {
    Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
    Quadro quadro = new Quadro();
    quadro.setId(id);
    quadro.setUsuario(usuario);
    try {
        quadro = new QuadroService().owner(usuario, quadro);
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    HttpSession session = req.getSession();
    session.setAttribute("quadro", quadro);
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("atualizar") != null) {
            doPut(req, resp);
        } else if(req.getParameter("excluir") != null) {
            doDelete(req, resp);
        } else {
            Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
            Coluna coluna = (Coluna) req.getSession().getAttribute("coluna");
            String uri;
            RequestDispatcher rd;

            Cartao novo_cartao = new Cartao();
            novo_cartao.setTitulo(req.getParameter("titulo"));

            if (req.getParameter("status") == null || !req.getParameter("status").equals("on"))
                novo_cartao.setStatus(false);
            else
                novo_cartao.setStatus(true);
            Date dataInicio = req.getParameter("dataInicio") != null ? Date.valueOf(req.getParameter("dataInicio")) : null;
            Date dataTermino = req.getParameter("dataTermino") != null ? Date.valueOf(req.getParameter("dataTermino")) : null;
            novo_cartao.setDataInicio(dataInicio);
            novo_cartao.setDataTermino(dataTermino);
            novo_cartao.setColuna(coluna);
            novo_cartao.setDescricao(req.getParameter("descricao"));
            CartoesDAO crt_dao = new CartoesDAO();
            try {
                novo_cartao = crt_dao.postCartao(novo_cartao);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (novo_cartao != null) {
                getQuadro(req, quadro.getId());
                uri = "/WEB-INF/quadro.jsp";
            } else {
                req.setAttribute("error", "Erro ao cadastrar o cartão!");
                uri = "/WEB-INF/novo_cartao.jsp";
            }
            System.out.println(uri);
            rd = req.getRequestDispatcher(uri);
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        Coluna coluna = (Coluna) req.getSession().getAttribute("coluna");
        Cartao cartao = (Cartao) req.getSession().getAttribute("cartao");

        String uri = "";

        Cartao cartao_atualizado = new Cartao();
        cartao_atualizado.setTitulo(req.getParameter("titulo").equals("") ? cartao.getTitulo() : req.getParameter("titulo"));

        if (req.getParameter("status") == null || !req.getParameter("status").equals("on"))
            cartao_atualizado.setStatus(false);
        else
            cartao_atualizado.setStatus(true);
        cartao_atualizado.setDescricao(req.getParameter("descricao"));
        Date dataInicio = null, dataTermino = null;
        if (req.getParameter("dataInicio") != null && !req.getParameter("dataInicio").equals("")) {
            dataInicio = Date.valueOf(req.getParameter("dataInicio"));
        }
        if (req.getParameter("dataTermino") != null && !req.getParameter("dataTermino").equals("")) {
            dataTermino = Date.valueOf(req.getParameter("dataTermino"));
        }
        cartao_atualizado.setDataInicio(dataInicio);
        cartao_atualizado.setDataTermino(dataTermino);
        cartao_atualizado.setId(cartao.getId());
        cartao_atualizado.setColuna(coluna);
        CartoesDAO col_dao = new CartoesDAO();

        try {
            cartao_atualizado = col_dao.putCartao(cartao_atualizado);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (cartao_atualizado != null) {
            getQuadro(req, quadro.getId());
            uri = "/WEB-INF/quadro.jsp";
        } else {
            req.setAttribute("error", "Erro ao atualizar o cartão!");
            uri = "/WEB-INF/editar_cartao.jsp";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        Cartao cartao = (Cartao) req.getSession().getAttribute("cartao");

        CartoesDAO crt_dao = new CartoesDAO();
        Boolean deleted = false;
        String uri = "";
        try {
            deleted = crt_dao.deleteCartao(cartao.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deleted == false) {
            req.setAttribute("error", "Erro ao remover o cartão!");
            uri = "/WEB-INF/editar_cartao.jsp";
        } else {
            getQuadro(req, quadro.getId());
            uri = "/WEB-INF/quadro.jsp";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }

}
