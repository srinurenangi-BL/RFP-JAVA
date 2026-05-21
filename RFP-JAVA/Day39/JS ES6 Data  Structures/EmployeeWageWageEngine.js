// Global Environment Rules Configuration [cite: 57, 58, 184]
export const WAGE_PER_HOUR = 20; 
export const MAX_HRS_IN_MONTH = 160; 
export const NUM_OF_WORKING_DAYS = 20; 

// Core Base Utility Operations [cite: 54, 56, 187, 188]
export function calcDailyWage(empHrs) {
    return empHrs * WAGE_PER_HOUR; 
}

// Emulates random shifts [cite: 64, 197]
export function getWorkingHours(empCheck) {
    switch (empCheck) {
        case 1:
            return 4; // Part-time hours
        case 2:
            return 8; // Full-time hours
        default:
            return 0; // Absent
    }
}

export function runEmployeeWageEngine() {
    let totalEmpHrs = 0; // [cite: 58, 184]
    let totalWorkingDays = 0; // [cite: 59, 185]

    let empDailyWageArr = new Array(); // [cite: 60, 186]
    let empDailyWageMap = new Map();   // [cite: 186]

    // --- UC 6 & UC 8: Generation Loop [cite: 61, 62, 193, 195] ---
    while (totalEmpHrs <= MAX_HRS_IN_MONTH && totalWorkingDays < NUM_OF_WORKING_DAYS) {
        totalWorkingDays++; // [cite: 62, 195]
        
        let empCheck = Math.floor(Math.random() * 10) % 3; // [cite: 63, 196]
        let empHrs = getWorkingHours(empCheck); // [cite: 64, 197]
        
        totalEmpHrs += empHrs; // [cite: 65, 198]
        
        let wageCalculated = calcDailyWage(empHrs);
        
        empDailyWageArr.push(wageCalculated); // [cite: 66, 199]
        empDailyWageMap.set(totalWorkingDays, wageCalculated); // [cite: 200]
    }

    let empWage = calcDailyWage(totalEmpHrs); // [cite: 69]
    console.log("UC6 Total Days: " + totalWorkingDays + " Total Hrs: " + totalEmpHrs + " Emp Wage: " + empWage); // [cite: 71, 75]
    console.log("UC8 Populated Map Content:\n", empDailyWageMap); // [cite: 201]


    // --- UC 7: ARRAY HELPER FUNCTIONS [cite: 80] ---
    
    // UC 7A: Calculate total Wage [cite: 81, 82]
    let totEmpWage = 0; // [cite: 83]
    function sum(dailyWage) {
        totEmpWage += dailyWage; // Fixed typo 'dailywage' from slide [cite: 86]
    }
    empDailyWageArr.forEach(sum); // [cite: 87]
    console.log("UC7A Total Days: " + totalWorkingDays + " Total Hrs: " + totalEmpHrs + " Emp Wage: " + totEmpWage); // [cite: 90, 91]

    function totalWages(totalWage, dailyWage) {
        return totalWage + dailyWage; // Fixed typo [cite: 92, 95]
    }
    console.log("UC7A Emp Wage with reduce: " + empDailyWageArr.reduce(totalWages, 0)); // [cite: 97, 98]

    // UC 7B: Map Day with Wage [cite: 99]
    let dailyCntr = 0; 
    function mapDayWithWage(dailyWage) {
        dailyCntr++; // [cite: 102]
        return dailyCntr + " = " + dailyWage; // [cite: 103]
    }
    let mapDayWithWageArr = empDailyWageArr.map(mapDayWithWage); // [cite: 104]
    console.log("UC7B Daily Wage Map"); // [cite: 105]
    console.log(mapDayWithWageArr); // [cite: 106]

    // UC 7C: Filter Fulltime Days [cite: 107]
    function fulltimeWage(dailyWage) {
        return dailyWage.toString().includes("160"); // Added .toString() to fix code syntax [cite: 108]
    }
    let fullDayWageArr = mapDayWithWageArr.filter(fulltimeWage); // [cite: 110]
    console.log("UC7C Daily Wage Filter When Fulltime Wage Earned"); // [cite: 111]
    console.log(fullDayWageArr);

    // UC 7D: Find first occurrence of Full Time Wage [cite: 112]
    function findFulltimeWage(dailyWage) {
        return dailyWage.toString().includes("160"); // [cite: 114]
    }
    console.log("UC 7D First time Fulltime wage was earned on Day: " + mapDayWithWageArr.find(findFulltimeWage)); // [cite: 115, 116]

    // UC 7E: Every element validation [cite: 117]
    function isAllFulltimeWage(dailyWage) {
        return dailyWage.toString().includes("160"); // [cite: 122]
    }
    console.log("UC 6E Check All Element have Full Time Wage: " + fullDayWageArr.every(isAllFulltimeWage)); // [cite: 124, 125]

    // UC 7F: Check for part time wage [cite: 126]
    function isAnyPartTimeWage(dailyWage) {
        return dailyWage.toString().includes("80"); // [cite: 128]
    }
    console.log("UC 7F Check If Any Part Time Wage: " + mapDayWithWageArr.some(isAnyPartTimeWage)); // [cite: 129, 130]

    // UC 7G: Count total days worked [cite: 131]
    function totalDaysWorked(numOfDays, dailyWage) {
        if (dailyWage > 0) return numOfDays + 1; // Fixed variable layout mismatch [cite: 132]
        return numOfDays;
    }
    console.log("UC 7G Number of Days Emp Worked: " + empDailyWageArr.reduce(totalDaysWorked, 0)); // [cite: 134, 135]


    // --- UC 8: MAP COMPILATION REDUCTION [cite: 182] ---
    console.log("UC7A Emp Wage Map totalHrs: " + Array.from(empDailyWageMap.values()).reduce(totalWages, 0)); // [cite: 205, 206, 209]
}