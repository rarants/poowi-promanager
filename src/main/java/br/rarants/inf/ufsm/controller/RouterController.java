package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.ColunasDAO;
import br.rarants.inf.ufsm.dao.QuadrosDAO;
import br.rarants.inf.ufsm.model.Cartao;
import br.rarants.inf.ufsm.model.Coluna;
import br.rarants.inf.ufsm.model.Quadro;
import br.rarants.inf.ufsm.model.Usuario;
import br.rarants.inf.ufsm.service.CartaoService;
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

@WebServlet("router")
public class RouterController extends HttpServlet {
    private void getCartão(HttpServletRequest req, Integer id) throws SQLException, ClassNotFoundException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        Coluna coluna = (Coluna) req.getSession().getAttribute("coluna");

        Cartao cartao = new Cartao();
        cartao.setId(id);
        cartao.setColuna(coluna);
        try {
            cartao = new CartaoService().owner(usuario, cartao);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("cartao", cartao);
    }

    private void getColuna(HttpServletRequest req, Integer id) throws SQLException, ClassNotFoundException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        Coluna coluna = new Coluna();
        coluna.setId(id);
        coluna.setQuadro(quadro);
        try {
            coluna = new ColunaService().owner(usuario, coluna);
            quadro = new QuadroService().owner(usuario, quadro);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("coluna", coluna);
    }

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
        System.out.println(quadro.toString());
    }

    private void getQuadros(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        String uri = "/WEB-INF/dashboard.jsp";
        QuadrosDAO qdr_dao = new QuadrosDAO();
        ArrayList<Quadro> quadros = null;
        try {
            quadros = qdr_dao.getQuadros(usuario);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("quadros", quadros);
        req.getRequestDispatcher(uri).forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        String acao = req.getParameter("acao");
        String id_quadro = req.getParameter("id-quadro");
        String id_coluna = req.getParameter("id-coluna");
        String id_cartao = req.getParameter("id-cartao");
        String uri = "/";
        System.out.println(id_cartao);
        System.out.println(acao);

        if (req.getSession().getAttribute("usuario_logado") != null) {
            if(acao.equals("nova-coluna")) {
                // redireciona para cadastro de coluna
                uri = "/WEB-INF/nova_coluna.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("editar-coluna")) {
                // redireciona para edição de coluna
                try {
                    getColuna(req, Integer.parseInt(id_coluna));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                uri = "/WEB-INF/editar_coluna.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("excluir-coluna")) {
                // exclui coluna e redireciona
                doDelete(req, resp);
            } else if(acao.equals("quadro")) {
                getQuadro(req, Integer.parseInt(id_quadro));
                uri = "/WEB-INF/quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("novo-quadro")) {
                // redireciona para cadastro de quadros
                uri = "/WEB-INF/novo_quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("editar-quadro")) {
                // redireciona para edição de quadros
                getQuadro(req, Integer.parseInt(id_quadro));
                uri = "/WEB-INF/editar_quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("excluir-quadro")) {
                // exclui quadro e redireciona
                Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
                QuadrosDAO qdr_dao = new QuadrosDAO();
                Boolean deleted = false;
                try {
                    deleted = qdr_dao.deleteQuadro(Integer.parseInt(id_quadro), usuario.getId());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (deleted == false) {
                    req.setAttribute("error", "Erro ao remover o quadro!");
                }
                req.setAttribute("route", "quadros");
                getQuadros(req, resp);
                req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
            } else if(acao.equals("novo-cartao")) {
                // redireciona para cadastro de cartões
                try {
                    getColuna(req, Integer.parseInt(id_coluna));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                uri = "/WEB-INF/novo_cartao.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("editar-cartao")) {
                // redireciona para edição de cartão
                try {
                    getColuna(req, Integer.parseInt(id_coluna));
                    getCartão(req, Integer.parseInt(id_cartao));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                uri = "/WEB-INF/editar_cartao.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            }  else {
                getQuadros(req, resp);
                req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
