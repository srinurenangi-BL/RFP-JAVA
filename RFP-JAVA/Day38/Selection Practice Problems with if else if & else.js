// =========================================================================
// SELECTION PROBLEMS WITH IF-ELSE-IF (Page 15)
// =========================================================================

// UC 1: Read a single digit number and write the number in words
let targetDigit = Math.floor(Math.random() * 10);
let digitInWord = "";
if (targetDigit === 0) digitInWord = "Zero";
else if (targetDigit === 1) digitInWord = "One";
else if (targetDigit === 2) digitInWord = "Two";
else if (targetDigit === 3) digitInWord = "Three";
else if (targetDigit === 4) digitInWord = "Four";
else if (targetDigit === 5) digitInWord = "Five";
else if (targetDigit === 6) digitInWord = "Six";
else if (targetDigit === 7) digitInWord = "Seven";
else if (targetDigit === 8) digitInWord = "Eight";
else if (targetDigit === 9) digitInWord = "Nine";
console.log(`\nIf-Else-If UC 1 - Digit: ${targetDigit} -> Words: ${digitInWord}`);

// UC 2: Read a Number and Display the week day (Sunday, Monday,...)
let dayIndex = Math.floor(Math.random() * 7); // Range: 0 to 6
let weekDayName = "";
if (dayIndex === 0) weekDayName = "Sunday";
else if (dayIndex === 1) weekDayName = "Monday";
else if (dayIndex === 2) weekDayName = "Tuesday";
else if (dayIndex === 3) weekDayName = "Wednesday";
else if (dayIndex === 4) weekDayName = "Thursday";
else if (dayIndex === 5) weekDayName = "Friday";
else if (dayIndex === 6) weekDayName = "Saturday";
console.log(`If-Else-If UC 2 - Number Index: ${dayIndex} -> Week Day: ${weekDayName}`);

// UC 3: Read a Number 1, 10, 100, 1000, etc. and display unit, ten, hundred,...
let magnitudeValues = [1, 10, 100, 1000, 10000];
let randomMagnitude = magnitudeValues[Math.floor(Math.random() * magnitudeValues.length)];
let placeValueDescription = "";
if (randomMagnitude === 1) placeValueDescription = "Unit";
else if (randomMagnitude === 10) placeValueDescription = "Ten";
else if (randomMagnitude === 100) placeValueDescription = "Hundred";
else if (randomMagnitude === 1000) placeValueDescription = "Thousand";
else if (randomMagnitude === 10000) placeValueDescription = "Ten Thousand";
console.log(`If-Else-If UC 3 - Magnitude: ${randomMagnitude} -> Description Place Value: ${placeValueDescription}`);

// UC 4: Enter 3 Numbers, perform arithmetic operations, and extract Max and Min values
let valA = 10, valB = 4, valC = 6;
let op1 = valA + valB * valC;
let op2 = valA % valB + valC;
let op3 = valC + valA / valB;
let op4 = valA * valB + valC;

let resultsArray = [op1, op2, op3, op4];
console.log(`If-Else-If UC 4 - Evaluated Equation Results Matrix: [${resultsArray.join(", ")}]`);

let evaluatedMin = resultsArray[0], evaluatedMax = resultsArray[0];
for (let i = 1; i < resultsArray.length; i++) {
    if (resultsArray[i] < evaluatedMin) evaluatedMin = resultsArray[i];
    if (resultsArray[i] > evaluatedMax) evaluatedMax = resultsArray[i];
}
console.log(`   Calculated Arithmetic Minimum: ${evaluatedMin} | Calculated Arithmetic Maximum: ${evaluatedMax}`);