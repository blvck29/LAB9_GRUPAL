package com.example.lab9.model.daos;

import com.example.lab9.model.beans.DepartmentEmp;
import com.example.lab9.model.beans.DepartmentManager;
import com.example.lab9.model.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DeptManDao {
    public ArrayList<Employee> listarDeptMan () {
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
                "\tFROM dept_manager d\n" +
                "\tINNER JOIN employees e on (d.emp_no = e.emp_no)\n" +
                "    LEFT JOIN departments ddd on (d.dept_no = ddd.dept_no)";

        ArrayList<DepartmentManager> listaDeptMan = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                DepartmentManager deptMan = new DepartmentManager();

                deptMan.setEmp_no(rs.getInt(1));
                deptMan.setEmp_fullname(rs.getString(2));
                deptMan.setDept_no(rs.getString(3));
                deptMan.setDept_name(rs.getString(4));
                deptMan.setFrom_date(rs.getDate(5));
                deptMan.setTo_date(rs.getDate(6));

                listaDeptMan.add(deptMan);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listarDeptMan();
    }

}
