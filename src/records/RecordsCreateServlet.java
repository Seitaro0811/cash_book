package records;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookUser;
import models.Record;
import models.validators.RecordValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsCreateServlet
 */
@WebServlet("/records/create")
public class RecordsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Record r = new Record();

            r.setBook_user((BookUser)request.getSession().getAttribute("login_user"));

            try {
                String date_str = request.getParameter("date");
                Date date = Date.valueOf(date_str);
                r.setDate(date);
            } catch(IllegalArgumentException iae) {}

            r.setContent(request.getParameter("content"));

            try {
                r.setAmount(Integer.parseInt(request.getParameter("amount")));
            } catch(NumberFormatException nfe) {}

            r.setComment(request.getParameter("comment"));

            List<String> errors = RecordValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("record", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "記帳しました。");

                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }

}
