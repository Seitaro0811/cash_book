package book_users;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookUser;
import models.validators.BookUserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class BookUsersUpdateServlet
 */
@WebServlet("/book_users/update")
public class BookUsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUsersUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            Integer login_user_id = ((BookUser)request.getSession().getAttribute("login_user")).getId();
            BookUser u = em.find(BookUser.class, login_user_id);

            Boolean code_duplicate_check = true;
            if(u.getCode().equals(request.getParameter("code"))) {
                code_duplicate_check = false;
            } else {
                u.setCode(request.getParameter("code"));
            }

            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                u.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }

            u.setName(request.getParameter("name"));

            List<String> errors = BookUserValidator.validate(u, code_duplicate_check, password_check_flag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("book_user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/book_users/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().setAttribute("login_user", u);

                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }

}
