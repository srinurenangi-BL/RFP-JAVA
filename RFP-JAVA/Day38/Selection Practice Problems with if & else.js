// =========================================================================
// SELECTION PROBLEMS WITH IF-ELSE (Page 14)
// =========================================================================

// UC 1: Read 5 Random 3-Digit values and output the minimum and maximum values
let valuesArray = [];
for (let i = 0; i < 5; i++) {
    valuesArray.push(Math.floor(Math.random() * 900) + 100); // Range: 100 to 999
}
console.log("\nIf-Else UC 1 - Generated 3-Digit Values:", valuesArray);

let currentMin = valuesArray[0];
let currentMax = valuesArray[0];
for (let i = 1; i < valuesArray.length; i++) {
    if (valuesArray[i] < currentMin) currentMin = valuesArray[i];
    if (valuesArray[i] > currentMax) currentMax = valuesArray[i];
}
console.log(`   Extracted Minimum: ${currentMin} | Extracted Maximum: ${currentMax}`);

// UC 2: Day and Month command-line range validator (Returns true between March 20 and June 20)
// To run via CLI pass inputs: node AddressBookMain.js 25 4
let testDay = parseInt(process.argv[2]) || 20;
let testMonth = parseInt(process.argv[3]) || 3; 
let isWithinRange = false;

if ((testMonth === 3 && testDay >= 20 && testDay <= 31) ||
    (testMonth === 4 && testDay >= 1 && testDay <= 30) ||
    (testMonth === 5 && testDay >= 1 && testDay <= 31) ||
    (testMonth === 6 && testDay >= 1 && testDay <= 20)) {
    isWithinRange = true;
}
console.log(`If-Else UC 2 - Checked Date [Day: ${testDay}, Month: ${testMonth}] -> Result: ${isWithinRange}`);

// UC 3: Leap Year Verification Check (4-Digit Number criteria check)
let testYear = parseInt(process.argv[4]) || 2024;
if (testYear >= 1000 && testYear <= 9999) {
    if ((testYear % 4 === 0 && testYear % 100 !== 0) || (testYear % 400 === 0)) {
        console.log(`If-Else UC 3 - Year ${testYear} is a Leap Year.`);
    } else {
        console.log(`If-Else UC 3 - Year ${testYear} is NOT a Leap Year.`);
    }
} else {
    console.log("If-Else UC 3 - System Error: Please input a valid 4-digit integer year.");
}

// UC 4: Simulate a coin flip and print out "Heads" or "Tails" accordingly
let randomFlipValue = Math.floor(Math.random() * 2);
let coinSideOutput = (randomFlipValue === 0) ? "Heads" : "Tails";
console.log("If-Else UC 4 - Coin Flip Result: " + coinSideOutput);  