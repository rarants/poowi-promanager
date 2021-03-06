package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.ColunasDAO;
import br.rarants.inf.ufsm.dao.QuadrosDAO;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;
import br.rarants.inf.ufsm.service.ColunaService;
import br.rarants.inf.ufsm.service.QuadroService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("coluna")
public class ColunaController extends HttpServlet {
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
            Quadro   quadro = (Quadro) req.getSession().getAttribute("quadro");
            String uri = "";
            RequestDispatcher rd;

            Coluna nova_coluna = new Coluna();
            nova_coluna.setTitulo(req.getParameter("titulo"));
            nova_coluna.setQuadro(quadro);
            ColunasDAO col_dao = new ColunasDAO();
            try {
                nova_coluna = col_dao.postColuna(nova_coluna);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (nova_coluna != null) {
                getQuadro(req, quadro.getId());
                uri = "/WEB-INF/quadro.jsp";
            } else {
                req.setAttribute("error", "Erro ao cadastrar a coluna!");
                uri = "/WEB-INF/nova_coluna.jsp";
            }
            rd = req.getRequestDispatcher(uri);
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        Coluna coluna = (Coluna) req.getSession().getAttribute("coluna");

        String uri = "";

        Coluna coluna_atualizada = new Coluna();
        coluna_atualizada.setTitulo(req.getParameter("titulo").equals("") ? coluna.getTitulo() : req.getParameter("titulo"));
        coluna_atualizada.setQuadro(quadro);
        coluna_atualizada.setId(coluna.getId());
        ColunasDAO col_dao = new ColunasDAO();

        try {
            coluna_atualizada = col_dao.putColuna(coluna_atualizada);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (coluna_atualizada != null) {
            getQuadro(req, quadro.getId());
            uri = "/WEB-INF/quadro.jsp";
        } else {
            req.setAttribute("error", "Erro ao atualizar a coluna!");
            uri = "/WEB-INF/editar_coluna.jsp";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        Coluna coluna = (Coluna) req.getSession().getAttribute("coluna");

        ColunasDAO col_dao = new ColunasDAO();
        Boolean deleted = false;
        String uri = "";
        try {
            deleted = col_dao.deleteColuna(coluna.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deleted == false) {
            req.setAttribute("error", "Erro ao remover a coluna!");
            uri = "/WEB-INF/editar_coluna.jsp";
        } else {
            getQuadro(req, quadro.getId());
            uri = "/WEB-INF/quadro.jsp";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }
}
