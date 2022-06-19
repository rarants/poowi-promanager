package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.model.Usuario;
import br.rarants.inf.ufsm.service.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        RequestDispatcher rd;
        Usuario usuario = null;
        try {
            usuario = new UsuarioService().autenticado(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario_logado", usuario);
            rd = req.getRequestDispatcher("/WEB-INF/dashboard.jsp");
        } else {
            req.setAttribute("error", "Usuário e/ou senha incorretos!");
            rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
        }
        rd.forward(req, resp);
    }
}
