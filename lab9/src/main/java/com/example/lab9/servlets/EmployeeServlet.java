package com.example.lab9.servlets;

import com.example.lab9.model.beans.Employee;
import com.example.lab9.model.daos.EmployeeDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EmployeeServlet", value = "/listar_employees")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao EmpDao = new EmployeeDao();

        //Listar:
        ArrayList<Employee> listaEmployees = EmpDao.listarEmployees();
        request.setAttribute("listaEmployees",listaEmployees);
        request.getRequestDispatcher("#").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

