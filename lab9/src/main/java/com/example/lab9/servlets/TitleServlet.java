package com.example.lab9.servlets;

import com.example.lab9.model.beans.Employee;
import com.example.lab9.model.beans.Title;
import com.example.lab9.model.daos.EmployeeDao;
import com.example.lab9.model.daos.TitleDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TitleServlet", value = "/TitleServlet")
public class TitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") ==null? "lista" : request.getParameter("action");
        TitleDao titleDao = new TitleDao();
        switch (action){
            case "lista":
                ArrayList<Title> list = titleDao.list();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("title/lista.jsp");
                rd.forward(request,response);

                break;


            case "edit":
                String emp_no = request.getParameter("id");
                Title title = titleDao.buscarPorEmp_no(emp_no);

                if(title != null){
                    request.setAttribute("title", title);
                    request.getRequestDispatcher("title/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/TitleServlet");
                }
                break;


            case "del":
                String emp_noo = request.getParameter("id");
                Title title1 = titleDao.buscarPorEmp_no(emp_noo);

                if(title1 != null){
                    try {
                        titleDao.borrar(emp_noo);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/TitleServlet");

                break;



        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        TitleDao titleDao = new TitleDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear":
                // TODO
                break;
            case "e":
                String titleEmpNo2 = request.getParameter("empNo");
                String titleTitle2 = request.getParameter("title");
                String titleFdate2 = request.getParameter("fromDate");
                String titleTdate2 = request.getParameter("toDate");

                boolean isAllValid2 = true;

                if(titleTitle2.length() > 35){
                    isAllValid2 = false;
                }

                if(titleEmpNo2.length() > 10){
                    isAllValid2 = false;
                }
                if(isAllValid2){
                    Title title = new Title();
                    title.setTitle(titleEmpNo2);
                    title.setJobTitle(titleTitle2);
                    title.setMinSalary(Integer.parseInt(minSalary2));
                    title.setMaxSalary(Integer.parseInt(maxSalary2));

                    jobDao.actualizar(job);
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }else{
                    request.setAttribute("job",jobDao.buscarPorId(jobId2));
                    request.getRequestDispatcher("job/form_edit.jsp").forward(request,response);
                }

                break;

        }






    }
}
