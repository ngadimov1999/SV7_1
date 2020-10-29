package controller;

import dbService.DBException;
import dbService.DBService;
import dbService.data.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class EntryController extends HttpServlet {

    private DBService dbService = new DBService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile userProfile = SessionController.getInstance().getUserBySessionId(req.getSession().getId());
        if (userProfile != null) {
            resp.sendRedirect("/SV7_1_war/explorer");
            return;
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");

        clearErrors(req);


        boolean errorStatus = false;
        try {
            errorStatus = checkErrors(req, login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }

        if (errorStatus) {
            req.setAttribute("login", login);
            req.setAttribute("pass", password);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
        UserProfile userProfile = dbService.getUser(login);
        if (userProfile == null || !userProfile.getPassword().equals(password)) {
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            return;
        }
        SessionController.getInstance().addSession(req.getSession().getId(), userProfile);
        resp.sendRedirect("/SV7_1_war/explorer");
        }
    }

    private boolean checkErrors(HttpServletRequest req, String login, String password) throws DBException {

        if (login == null || login.equals("")) {
            req.setAttribute("loginErr", "Поле не заполнено");
        } else if (password == null || password.equals("")) {
            req.setAttribute("passErr", "Поле не заполнено");
        }
        else if (dbService.getUser(login) == null) {
            req.setAttribute("loginErr", "Аккаунта с таким логином не существует");
        }
        else if (!dbService.getUser(login).getPassword().equals(password)) {
            req.setAttribute("passErr", "Неверный пароль");
        } else return false;
        return true;
    }

    private void clearErrors(HttpServletRequest req) {
        req.setAttribute("loginErr", "");
        req.setAttribute("passErr", "");
    }
}