package com.example.lab9.model.daos;



import com.example.lab9.model.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {
    private static final String username = "root";
    private static final String password = "root";

    public ArrayList<Employee> list(){

        ArrayList<Employee> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String sql = "select * from employees limit 100";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpNo(rs.getInt(1));
                employee.setBirthDate(rs.getString(2));
                employee.setFirstName(rs.getString(3));
                employee.setLastName(rs.getString(4));
                employee.setGender(rs.getString(4));
                employee.setHireDate(rs.getString(4));

                lista.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public static int lastId(){

        Employee lastEmployee = new Employee();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String sql = "select * from employees order by emp_no desc limit 1";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lastEmployee.setEmpNo(rs.getInt(1));
                lastEmployee.setBirthDate(rs.getString(2));
                lastEmployee.setFirstName(rs.getString(3));
                lastEmployee.setLastName(rs.getString(4));
                lastEmployee.setGender(rs.getString(4));
                lastEmployee.setHireDate(rs.getString(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lastEmployee.getEmpNo();
    }


    public static void create(String birthDate, String firstName, String lastName, String gender, String hireDate){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String sql = "insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date) values (?,?,?,?,?,?)";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            int newId = lastId() + 1;

            pstmt.setInt(1, newId);
            pstmt.setString(2,birthDate);
            pstmt.setString(3,firstName);
            pstmt.setString(4,lastName);
            pstmt.setString(5,gender);
            pstmt.setString(6,hireDate);

            pstmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Employee buscarPorId(String id){

        Employee employee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "select * from employees where emp_no = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpNo(rs.getInt(1));
                    employee.setBirthDate(rs.getString(2));
                    employee.setFirstName(rs.getString(3));
                    employee.setLastName(rs.getString(4));
                    employee.setGender(rs.getString(4));
                    employee.setHireDate(rs.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }



    public static void actualizar(Employee emp){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        try (Connection connection = DriverManager.getConnection(url,username,password);){

            String sql = "UPDATE employees SET birth_date=?, first_name=?, last_name=?, gender=?, hire_date=?" + "WHERE emp_no = ?";


            try (PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setString(1, emp.getBirthDate());
                pstmt.setString(2, emp.getFirstName());
                pstmt.setString(3, emp.getLastName());
                pstmt.setString(4, emp.getGender());
                pstmt.setString(5, emp.getHireDate());

                pstmt.setInt(6, emp.getEmpNo());

                pstmt.executeUpdate();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }



    }

    public void borrar(String employeeNo) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "delete from employees where emp_no = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,employeeNo);
            pstmt.executeUpdate();

        }
    }

    public ArrayList<Employee> searchByName(String name) {
        // TODO
        return null;
    }

    public int searchLastId() {
        // TODO
        return 0;
    }



}
