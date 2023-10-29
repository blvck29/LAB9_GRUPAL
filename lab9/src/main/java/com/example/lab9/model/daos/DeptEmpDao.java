package com.example.lab9.model.daos;

import com.example.lab9.model.beans.DepartmentEmp;
import com.example.lab9.model.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DeptEmpDao {
    public ArrayList<Employee> listarDeptEmp () {
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
        String sql = "SELECT e.emp_no, concat(e.first_name, \" \", e.last_name) as `full_name`, d.dept_no, ddd.dept_name, d.from_date, d.to_date\n" +
                        "\tFROM dept_emp d\n" +
                        "\tINNER JOIN employees e on (d.emp_no = e.emp_no)\n" +
                        "    LEFT JOIN departments ddd on (d.dept_no = ddd.dept_no)";

        ArrayList<DepartmentEmp> listaDeptEmp = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                DepartmentEmp deptEmp = new DepartmentEmp();

                deptEmp.setEmp_no(rs.getInt(1));
                deptEmp.setEmp_fullname(rs.getString(2));
                deptEmp.setDept_no(rs.getString(3));
                deptEmp.setDept_name(rs.getString(4));
                deptEmp.setFrom_date(rs.getDate(5));
                deptEmp.setTo_date(rs.getDate(6));

                listaDeptEmp.add(deptEmp);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listarDeptEmp();
    }
}
