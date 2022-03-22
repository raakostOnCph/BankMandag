import DomæneObjekter.Konto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LogIn", value = "/LogIn")
public class LogInServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String navn = request.getParameter("navn");
        String kode = request.getParameter("koden");

        Map<String, Konto> kontoMap = (Map<String, Konto>) getServletContext().getAttribute("kontis");

        Konto konto = kontoMap.getOrDefault(navn, null);

        String fejlBesked;

        if (konto == null ) {

            fejlBesked = "kontoen fandtes ikke";
            request.setAttribute("fejl", fejlBesked);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        if (!konto.getKode().equals(kode)) {
            fejlBesked = "dit login er forkert, prøv igen";
            request.setAttribute("fejl", fejlBesked);
            request.getRequestDispatcher("index.jsp").forward(request,response);

        }

        HttpSession session = request.getSession();

        session.setAttribute("navn", navn);
        session.setAttribute("konto", konto);



        request.getRequestDispatcher("WEB-INF/BrugerSide.jsp").forward(request, response);
        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
