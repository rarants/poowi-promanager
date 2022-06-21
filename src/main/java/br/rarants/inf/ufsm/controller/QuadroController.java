package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.QuadrosDAO;
import br.rarants.inf.ufsm.dao.UsuarioDAO;
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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("quadro")
public class QuadroController extends HttpServlet {
/*  @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        String id = req.getParameter("id");
        String uri = "/";
        System.out.println(acao + " " + req.getSession().getAttribute("usuario_logado"));
        if (req.getSession().getAttribute("usuario_logado") != null) {
            if (acao.equals("sair")) {
                req.getSession().invalidate();
                System.out.println("depois " + req.getSession().getAttribute("usuario_logado"));
                uri = "/WEB-INF/login.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if (acao.equals("quadros")) {
                // busca quadros e redireciona
                doGet(req, resp);
            } else if(acao.equals("novo")) {
                // redireciona para cadastro de quadros
                uri = "/WEB-INF/novo_quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("editar")) {
                // redireciona para edição de quadros
                getQuadro(req, Integer.parseInt(id));
                uri = "/WEB-INF/editar_quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            } else if(acao.equals("cadastrar")) {
                // cadastra quadro e redireciona
                doPost(req, resp);
            } else if(acao.equals("atualizar")) {
                // atualiza quadro e redireciona
                doPut(req, resp);
            } else if(acao.equals("excluir")) {
                // exclui quadro e redireciona
                doDelete(req, resp);
            } else {
                // busca quadro e redireciona
                getQuadro(req, Integer.parseInt(id));
                uri = "/WEB-INF/quadro.jsp";
                req.getRequestDispatcher(uri).forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
*/
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        String id = req.getParameter("id");

        if (acao.equals("quadros")) {
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
        } else {
            getQuadro(req, Integer.parseInt(id));
            req.getRequestDispatcher("/WEB-INF/quadro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        String uri = "";
        RequestDispatcher rd;

        Quadro novo_quadro = new Quadro();
        novo_quadro.setTitulo(req.getParameter("titulo"));
        novo_quadro.setDescricao(req.getParameter("descricao"));
        novo_quadro.setUsuario(usuario);
        if (req.getParameter("publico") == null || !req.getParameter("publico").equals("on"))
            novo_quadro.setPublico(false);
        else
            novo_quadro.setPublico(true);

        QuadrosDAO qdr_dao = new QuadrosDAO();
        try {
            novo_quadro = qdr_dao.postQuadro(novo_quadro);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (novo_quadro != null) {
            HttpSession session = req.getSession();
            session.setAttribute("quadro", novo_quadro);
            uri = "/WEB-INF/quadro.jsp";
        } else {
            req.setAttribute("error", "Erro ao cadastrar o quadro!");
            uri = "/WEB-INF/novo_quadro.jsp";
        }
        rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        Quadro quadro = (Quadro) req.getSession().getAttribute("quadro");
        String uri = "";
        RequestDispatcher rd;

        Quadro quadro_atualizado = new Quadro();
        quadro_atualizado.setTitulo(req.getParameter("titulo").equals("") ? quadro.getTitulo() : req.getParameter("titulo"));
        quadro_atualizado.setDescricao(req.getParameter("descricao").equals("") ? quadro.getDescricao() : req.getParameter("descricao"));
        quadro_atualizado.setUsuario(usuario);
        quadro_atualizado.setId(quadro.getId());
        if (req.getParameter("publico") == null || !req.getParameter("publico").equals("on"))
            quadro_atualizado.setPublico(false);
        else
            quadro_atualizado.setPublico(true);

        QuadrosDAO qdr_dao = new QuadrosDAO();

        try {
            quadro_atualizado = qdr_dao.putQuadro(quadro_atualizado);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (quadro_atualizado != null) {
            HttpSession session = req.getSession();
            session.setAttribute("quadro", quadro_atualizado);
            uri = "/WEB-INF/quadro.jsp";
        } else {
            req.setAttribute("error", "Erro ao atualizar o quadro!");
            uri = "/WEB-INF/editar_quadro.jsp";
        }
        rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        String id = req.getParameter("id");
        QuadrosDAO qdr_dao = new QuadrosDAO();
        Boolean deleted = false;
        try {
            deleted = qdr_dao.deleteQuadro(Integer.parseInt(id), usuario.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deleted == false) {
            req.setAttribute("error", "Erro ao remover o quadro!");
        }
        req.setAttribute("route", "quadros");
        doGet(req, resp);
    }
}
