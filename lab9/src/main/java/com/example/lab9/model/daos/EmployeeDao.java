package com.example.lab9.model.daos;

import com.example.lab9.model.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {
    public ArrayList<Employee> listarEmployees () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Parámetros de Conexión
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "root";

        //Conexión a la DB
        String sql = "select * from employees";

        ArrayList<Employee> listaEmployees = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setEmp_no(rs.getInt(1));
                employee.setBirth_date(rs.getDate(2));
                employee.setFirst_name(rs.getString(3));
                employee.setLast_name(rs.getString(4));
                employee.setGender(rs.getString(5));
                employee.setHire_date(rs.getDate(6));

                listaEmployees.add(employee);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listarEmployees();
    }

}