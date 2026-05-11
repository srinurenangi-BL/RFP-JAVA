package Day10.Employee_Wage_Computation_Problem;

public interface IComputeEmpWage {
    void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth);
    void computeEmpWage();
    int getTotalWage(String company);
}