package cash_book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        BookUser login_user = (BookUser)request.getSession().getAttribute("login_user");

        List<Record> r = new ArrayList<Record>();

        String selected_content = request.getParameter("select");

        if(selected_content != null && !selected_content.equals("")) {
            r = em.createNamedQuery("getSelectedRecords", Record.class)
                    .setParameter("user", login_user)
                    .setParameter("content", selected_content)
                    .getResultList();
        } else {
            r = em.createNamedQuery("getAllRecords", Record.class)
                    .setParameter("user", login_user)
                    .getResultList();
        }

        em.close();

        Calendar viewed_cl = Calendar.getInstance();

        try {
            viewed_cl.set(Calendar.YEAR, Integer.parseInt(request.getParameter("year")));
        } catch(Exception e) {}
        try {
            viewed_cl.set(Calendar.MONTH, (Integer.parseInt(request.getParameter("month")) - 1));
        } catch(Exception e) {}

        List<Record> selected_r = new ArrayList<Record>();

        Calendar rec_cl = Calendar.getInstance();
        for(int i=0; i < r.size(); i++) {
            rec_cl.setTime(r.get(i).getDate());
            if(viewed_cl.get(Calendar.YEAR) == rec_cl.get(Calendar.YEAR) && viewed_cl.get(Calendar.MONTH) == rec_cl.get(Calendar.MONTH)) {
                selected_r.add(r.get(i));
            }
        }
        long records_count = selected_r.size();

        int total = 0;
        for(Record record : selected_r) {
            if(record.getContent().equals("収入")) {
                total += record.getAmount();
            } else {
                total -= record.getAmount();
            }
        }

        List<String> c_index = new ArrayList<String>();
        for(Record record : selected_r){
            if(record.getComment().length() > 10) {
                c_index.add(record.getComment().substring(0, 10));
            } else {
                c_index.add(record.getComment());
            }
        }

        List<Record> viewed_r = new ArrayList<Record>();
        for(int x=(page-1)*10; x<page*10; x++) {
            try {
                viewed_r.add(selected_r.get(x));
            } catch(IndexOutOfBoundsException e) {
                break;
            }
        }

        request.setAttribute("records", viewed_r);
        request.setAttribute("records_count", records_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        request.setAttribute("year", viewed_cl.get(Calendar.YEAR));
        request.setAttribute("month", viewed_cl.get(Calendar.MONTH) + 1);
        request.setAttribute("total", total);
        request.setAttribute("c_index", c_index);
        request.setAttribute("selected", selected_content);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/cash_book/index.jsp");
        rd.forward(request, response);
    }

}
