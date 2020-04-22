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

import models.Record;
import models.validators.RecordValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsUpdateServlet
 */
@WebServlet("/records/update")
public class RecordsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Record r = em.find(Record.class, (Integer)(request.getSession().getAttribute("record_id")));

            try {
                r.setDate(Date.valueOf(request.getParameter("date")));
            } catch(IllegalArgumentException iae) {
                r.setDate(null);
            }

            r.setContent(request.getParameter("content"));

            try {
                r.setAmount(Integer.parseInt(request.getParameter("amount")));
            } catch(NumberFormatException nfe) {
                r.setAmount(null);
            }

            r.setComment(request.getParameter("comment"));

            List<String> errors = RecordValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("record", r);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "変更が完了しました。");

                request.getSession().removeAttribute("record_id");

                response.sendRedirect(request.getContextPath() + "/");
                }
        }
    }

}
