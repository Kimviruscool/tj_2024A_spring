package example.day06.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/day06/test") //http://localhost:8080/day06/test?data=10
public class ServletTestController extends HttpServlet {
 int data = 10;
//GET

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("ServletTestController.doGet");
        //요청데이터
        System.out.println("request DATA : " + req.getParameter("value"));
        //응답데이터
        resp.getWriter().print(data + 2);
    }

//POST

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        //요청데이터
        System.out.println("request DATA : " + req.getParameter("value"));
        //응답데이터
        resp.getWriter().print(data * 2);
    }


//PUT

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
        //요청데이터
        System.out.println("request DATA : " + req.getParameter("value"));
        //응답데이터
        resp.getWriter().print(data / 2);
    }


//DELETE


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
        //요청데이터
        System.out.println("request DATA : " + req.getParameter("value"));
        //응답데이터
        resp.getWriter().print(data % 2);
    }
}
