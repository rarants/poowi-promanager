package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.UsuarioDAO;
import br.rarants.inf.ufsm.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("register")
public class RegisterController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String route = req.getParameter("route");
        String uri;
        if (route.equals("register")) {
            uri = "/WEB-INF/register.jsp";
        } else {
            uri = "/";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean registered = false;
        Usuario usuario = new Usuario();
        usuario.setEmail(req.getParameter("email"));
        usuario.setSenha(req.getParameter("password"));
        usuario.setNome(req.getParameter("nome"));
        RequestDispatcher rd;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            registered = dao.postUsuario(usuario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (registered) {
            rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
        } else {
            req.setAttribute("error", "Erro ao cadastrar o usuário!");
            rd = req.getRequestDispatcher("/WEB-INF/register.jsp");
        }
        rd.forward(req, resp);
    }
}
