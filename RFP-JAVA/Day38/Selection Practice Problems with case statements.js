// =========================================================================
// SELECTION PROBLEMS WITH SWITCH CASE (Page 17)
// =========================================================================

// UC 1: Read a single digit number and write the number in word using Case
let caseDigit = Math.floor(Math.random() * 10);
process.stdout.write(`\nSwitch-Case UC 1 - Digit ${caseDigit}: `);
switch (caseDigit) {
    case 0: console.log("Zero"); break;
    case 1: console.log("One"); break;
    case 2: console.log("Two"); break;
    case 3: console.log("Three"); break;
    case 4: console.log("Four"); break;
    case 5: console.log("Five"); break;
    case 6: console.log("Six"); break;
    case 7: console.log("Seven"); break;
    case 8: console.log("Eight"); break;
    case 9: console.log("Nine"); break;
}

// UC 2: Read a Number and Display the week day using Case
let caseDayIndex = Math.floor(Math.random() * 7);
process.stdout.write(`Switch-Case UC 2 - Day Index ${caseDayIndex}: `);
switch (caseDayIndex) {
    case 0: console.log("Sunday"); break;
    case 1: console.log("Monday"); break;
    case 2: console.log("Tuesday"); break;
    case 3: console.log("Wednesday"); break;
    case 4: console.log("Thursday"); break;
    case 5: console.log("Friday"); break;
    case 6: console.log("Saturday"); break;
}

// UC 3: Read a Number 1, 10, 100, 1000 and display unit, ten, hundred,... using Case
let caseMagnitude = 1000;
process.stdout.write(`Switch-Case UC 3 - Magnitude ${caseMagnitude}: `);
switch (caseMagnitude) {
    case 1: console.log("Unit"); break;
    case 10: console.log("Ten"); break;
    case 100: console.log("Hundred"); break;
    case 1000: console.log("Thousand"); break;
    default: console.log("Out of tracking index limits.");
}

// UC 4: Unit Conversion of different Length units using Case Selector
let conversionChoice = Math.floor(Math.random() * 4) + 1; // Options 1 to 4
let quantitativeInput = 12; 
console.log(`Switch-Case UC 4 - Option Router Type [${conversionChoice}] for Input [${quantitativeInput}]:`);

switch (conversionChoice) {
    case 1: // Feet to Inch
        console.log(`   Feet to Inch: ${quantitativeInput} ft = ${quantitativeInput * 12} in`);
        break;
    case 2: // Feet to Meter
        console.log(`   Feet to Meter: ${quantitativeInput} ft = ${(quantitativeInput * 0.3048).toFixed(4)} m`);
        break;
    case 3: // Inch to Feet
        console.log(`   Inch to Feet: ${quantitativeInput} in = ${(quantitativeInput / 12).toFixed(4)} ft`);
        break;
    case 4: // Meter to Feet
        console.log(`   Meter to Feet: ${quantitativeInput} m = ${(quantitativeInput / 0.3048).toFixed(4)} ft`);
        break;
}