package br.rarants.inf.ufsm.controller;

import br.rarants.inf.ufsm.dao.QuadrosDAO;
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

@WebServlet("dashboard")
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_logado");
        QuadrosDAO qdr_dao = new QuadrosDAO();
        ArrayList<Quadro> quadros = null;
        try {
            quadros = qdr_dao.getQuadros(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("quadros", quadros);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/dashboard.jsp");
        rd.forward(req, resp);
    }
}
