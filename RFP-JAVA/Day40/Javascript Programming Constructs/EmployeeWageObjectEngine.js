// Global Configuration Constants
const WAGE_PER_HOUR = 20;
const MAX_HRS_IN_MONTH = 160;
const NUM_OF_WORKING_DAYS = 20;

export function runEmployeeWageObjectEngine() {
    console.log("=== STARTING OBJECTS & ARROW FUNCTIONS ENGINE (PAGE 5) ===");

    let totalEmpHrs = 0;
    let totalWorkingDays = 0;
    let empDailyWageObjArr = [];

    function getWorkingHours(empCheck) {
        switch (empCheck) {
            case 1: return 4;  // Part-time
            case 2: return 8;  // Full-time
            default: return 0; // Absent
        }
    }

    // Populate using JS Objects
    while (totalEmpHrs < MAX_HRS_IN_MONTH && totalWorkingDays < NUM_OF_WORKING_DAYS) {
        totalWorkingDays++;
        let empCheck = Math.floor(Math.random() * 10) % 3;
        let empHrs = getWorkingHours(empCheck);
        totalEmpHrs += empHrs;

        empDailyWageObjArr.push({
            dayNum: totalWorkingDays,
            dailyHours: empHrs,
            dailyWage: empHrs * WAGE_PER_HOUR
        });
    }

    // a. Calculate total Wage using reduce
    const totalWage = empDailyWageObjArr.reduce((total, obj) => total + obj.dailyWage, 0);
    console.log(`UC 7a - Total Monthly Wage: $${totalWage}`);

    // b. Show Day along with Daily Wage using Map helper function
    const dayWithWageMap = empDailyWageObjArr.map(obj => `Day ${obj.dayNum} = ${obj.dailyWage}`);
    console.log("UC 7b - Day with Wage Map:", dayWithWageMap);

    // c. Show Days when Full time wage of 160 was earned using filter
    const fullTimeDays = empDailyWageObjArr.filter(obj => obj.dailyWage === 160);
    console.log("UC 7c - Full-Time Days:", fullTimeDays.map(obj => `Day ${obj.dayNum}`));

    // d. Find the first occurrence when Full Time Wage was earned using find
    const firstFullTimeDay = empDailyWageObjArr.find(obj => obj.dailyWage === 160);
    console.log(`UC 7d - First Full-Time Day: ${firstFullTimeDay ? `Day ${firstFullTimeDay.dayNum}` : "None"}`);

    // e. Check if Every Element of Full Time Wage collection holds 160
    const isAllFullTime = fullTimeDays.every(obj => obj.dailyWage === 160);
    console.log(`UC 7e - Check All Elements hold 160: ${isAllFullTime}`);

    // f. Check if there is any Part Time Wage
    const hasPartTime = empDailyWageObjArr.some(obj => obj.dailyWage === 80);
    console.log(`UC 7f - Any Part-Time Day?: ${hasPartTime}`);

    // g. Find the number of days the Employee Worked
    const totalDaysWorked = empDailyWageObjArr.filter(obj => obj.dailyHours > 0).length;
    console.log(`UC 7g - Total Days Worked: ${totalDaysWorked} Days`);
    
    console.log("=== END OF OBJECT ENGINE WORKFLOW ===\n");
}