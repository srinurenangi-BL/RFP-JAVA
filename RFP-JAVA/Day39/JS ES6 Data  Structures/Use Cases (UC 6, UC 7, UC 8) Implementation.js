// =========================================================================
// Global Environmental Constants & Shared Rules (Page 6, 8, 13)
// =========================================================================
const WAGE_PER_HOUR = 20; // Fixed rate configuration [cite: 56, 188]
const MAX_HRS_IN_MONTH = 160; // Condition bound 1 [cite: 57, 184]
const NUM_OF_WORKING_DAYS = 20; // Condition bound 2 [cite: 58, 184]

// Core Base Utility Operations
function calcDailyWage(empHrs) {
    return empHrs * WAGE_PER_HOUR; // [cite: 56, 188]
}

// Emulates random conditional shifts matching your checking engine rules
function getWorkingHours(empCheck) {
    switch (empCheck) {
        case 1:
            return 4; // Part-time hours
        case 2:
            return 8; // Full-time hours
        default:
            return 0; // Absent
    }
}

// =========================================================================
// UC 6 & UC 8: Combined Array & Map Population Engine (Page 6, 13)
// =========================================================================
let totalEmpHrs = 0; // [cite: 58, 184]
let totalWorkingDays = 0; // [cite: 59, 185]

let empDailyWageArr = new Array(); // UC 6 Target [cite: 60, 186]
let empDailyWageMap = new Map();   // UC 8 Target [cite: 186]

// Continuous Loop running through monthly state caps [cite: 61, 62, 193, 195]
while (totalEmpHrs <= MAX_HRS_IN_MONTH && totalWorkingDays < NUM_OF_WORKING_DAYS) {
    totalWorkingDays++; // [cite: 62, 195]
    
    // Formula exactly as provided in code slide: Math.floor(Math.random() * 10) % 3 [cite: 63, 196]
    let empCheck = Math.floor(Math.random() * 10) % 3;
    let empHrs = getWorkingHours(empCheck); // Resolves the missing internal method call error [cite: 64, 197]
    
    totalEmpHrs += empHrs; // [cite: 65, 198]
    
    let wageCalculated = calcDailyWage(empHrs);
    
    // UC 6: Storing Daily Wage in an Array [cite: 51, 66, 199]
    empDailyWageArr.push(wageCalculated);
    
    // UC 8: Storing Daily Wage in a Map [cite: 182, 200]
    empDailyWageMap.set(totalWorkingDays, wageCalculated);
}

// Log initial state validation parameters [cite: 71, 75]
let empWage = calcDailyWage(totalEmpHrs); // [cite: 69]
console.log("=== UC 6 & UC 8 DATA INITIALIZATION ===");
console.log("UC6 Total Days: " + totalWorkingDays + " Total Hrs: " + totalEmpHrs + " Emp Wage: " + empWage);
console.log("UC8 Populated Map Content:\n", empDailyWageMap);
console.log("=================================================================\n");


// =========================================================================
// UC 7: Array Helper Functions Traversal Suite (Page 8, 9)
// =========================================================================
console.log("=== STARTING UC 7: ARRAY HELPER FUNCTIONS ===");

// --- UC 7A: Calc total Wage using Array forEach traversal or reduce method [cite: 82] ---
let totEmpWage = 0; // [cite: 83]
function sum(dailyWage) {
    totEmpWage += dailyWage; // Fixed runtime case error: converted dailywage to dailyWage [cite: 86]
}
empDailyWageArr.forEach(sum); // [cite: 87]
console.log("UC7A Total Days: " + totalWorkingDays + " Total Hrs: " + totalEmpHrs + " Emp Wage: " + totEmpWage); // [cite: 90, 91]

function totalWages(totalWage, dailyWage) {
    return totalWage + dailyWage; // Fixed runtime case error [cite: 95]
}
console.log("UC7A Emp Wage with reduce: " + empDailyWageArr.reduce(totalWages, 0)); // [cite: 97, 98]


// --- UC 7B: Show the Day along with Daily Wage using Array map helper function [cite: 99] ---
let dailyCntr = 0; // [cite: 99]
function mapDayWithWage(dailyWage) {
    dailyCntr++; // [cite: 102]
    return dailyCntr + " = " + dailyWage; // [cite: 103]
}
let mapDayWithWageArr = empDailyWageArr.map(mapDayWithWage); // [cite: 104]
console.log("UC7B Daily Wage Map"); // [cite: 105]
console.log(mapDayWithWageArr); // [cite: 106]


// --- UC 7C: Show Days when Full time wage of 160 were earned using filter function [cite: 107] ---
function fulltimeWage(dailyWage) {
    return dailyWage.toString().includes("160"); // Added explicit .toString() to prevent type crashes [cite: 108]
}
let fullDayWageArr = mapDayWithWageArr.filter(fulltimeWage); // [cite: 110]
console.log("UC7C Daily Wage Filter When Fulltime Wage Earned"); // [cite: 111]
console.log(fullDayWageArr);


// --- UC 7D: Find the first occurrence when Full Time Wage was earned using find function [cite: 112] ---
function findFulltimeWage(dailyWage) {
    return dailyWage.toString().includes("160"); // Added explicit .toString() to prevent type crashes [cite: 114]
}
console.log("UC 7D First time Fulltime wage was earned on Day: " + mapDayWithWageArr.find(findFulltimeWage)); // [cite: 115, 116]


// --- UC 7E: Check if Every Element of Full Time Wage is truly holding Full time wage [cite: 117] ---
function isAllFulltimeWage(dailyWage) {
    return dailyWage.toString().includes("160"); // Added explicit .toString() to prevent type crashes [cite: 122]
}
console.log("UC 6E Check All Element have Full Time Wage: " + fullDayWageArr.every(isAllFulltimeWage)); // [cite: 124, 125]


// --- UC 7F: Check if there is any Part Time Wage [cite: 126] ---
function isAnyPartTimeWage(dailyWage) {
    return dailyWage.toString().includes("80"); // Added explicit .toString() to prevent type crashes [cite: 128]
}
console.log("UC 7F Check If Any Part Time Wage: " + mapDayWithWageArr.some(isAnyPartTimeWage)); // [cite: 129, 130]


// --- UC 7G: Find the number of days the Employee Worked [cite: 131] ---
function totalDaysWorked(numOfDays, dailyWage) {
    if (dailyWage > 0) return numOfDays + 1; // Fixed internal slide condition name mismatch [cite: 132]
    return numOfDays;
}
console.log("UC 7G Number of Days Emp Worked: " + empDailyWageArr.reduce(totalDaysWorked, 0)); // [cite: 134, 135]
console.log("=================================================================\n");


// =========================================================================
// UC 8: Map Final Total Output Computation (Page 13)
// =========================================================================
console.log("=== STARTING UC 8: MAP COMPILATION EVALUATION ===");

// Converts the Map values into an iterable array and applies our standard tracking reduction [cite: 209]
console.log("UC7A Emp Wage Map totalHrs: " + Array.from(empDailyWageMap.values()).reduce(totalWages, 0)); // [cite: 206, 209]
console.log("=================================================================\n");