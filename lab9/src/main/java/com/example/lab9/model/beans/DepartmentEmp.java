package com.example.lab9.model.beans;

import java.sql.Date;

public class DepartmentEmp {
    private int emp_no;
    private String emp_fullname;
    private String dept_no;
    private String dept_name;
    private Date from_date;
    private Date to_date;


    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_fullname() {
        return emp_fullname;
    }

    public void setEmp_fullname(String emp_fullname) {
        this.emp_fullname = emp_fullname;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
