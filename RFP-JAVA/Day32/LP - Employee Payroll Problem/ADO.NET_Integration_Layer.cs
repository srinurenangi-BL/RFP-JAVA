using System;
using System.Collections.Generic;

public class EmployeePayrollData
{
    public int EmployeeID { get; set; }
    public string EmployeeName { get; set; }
    public char Gender { get; set; }
    public string Phone { get; set; }
    public string Address { get; set; }
    public DateTime StartDate { get; set; }
    public decimal BasicPay { get; set; }
    public decimal Deductions { get; set; }
    public decimal TaxablePay { get; set; }
    public decimal IncomeTax { get; set; }
    public decimal NetPay { get; set; }
    public List<string> Departments { get; set; } = new List<string>();
}