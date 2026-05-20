// =========================================================================
// SEQUENCE PROBLEMS (Page 12)
// =========================================================================

// UC 1: Random Function Math.floor(Math.random() * 10) to get Single Digit.
let singleDigit = Math.floor(Math.random() * 10);
console.log("UC 1 - Random Single Digit [0-9]: " + singleDigit);

// UC 2: Use Random to get Dice Number between 1 to 6
let diceNumber = Math.floor(Math.random() * 6) + 1;
console.log("UC 2 - Random Dice Roll [1-6]: " + diceNumber);

// UC 3: Add two Random Dice Number and Print the Result
let dice1 = Math.floor(Math.random() * 6) + 1;
let dice2 = Math.floor(Math.random() * 6) + 1;
console.log(`UC 3 - Dice 1: ${dice1}, Dice 2: ${dice2} | Sum: ${dice1 + dice2}`);

// UC 4: Write a program that reads 5 Random 2 Digit values, then find their sum and average
let totalSum = 0;
console.log("UC 4 - Generated 5 Random 2-Digit Numbers:");
for (let i = 1; i <= 5; i++) {
    let twoDigitValue = Math.floor(Math.random() * 90) + 10; // Range: 10 to 99
    console.log(`   Value ${i}: ${twoDigitValue}`);
    totalSum += twoDigitValue;
}
console.log(`   Aggregated Sum: ${totalSum} | Calculated Average: ${totalSum / 5}`);

// UC 5: Unit Conversion Engine
console.log("UC 5 - Unit Conversion Outputs:");
// a. 1ft = 12 in, then 42 in = ? ft
let inputInches = 42;
let convertedFeet = inputInches / 12;
console.log(`   a) ${inputInches} inches = ${convertedFeet} feet`);

// b. Rectangular Plot of 60 feet x 40 feet in meters (Note: 1 foot = 0.3048 meters)
let lengthFeet = 60, widthFeet = 40;
let lengthMeters = lengthFeet * 0.3048;
let widthMeters = widthFeet * 0.3048;
let areaSinglePlotMeters = lengthMeters * widthMeters;
console.log(`   b) Plot Area: ${lengthFeet}ft x ${widthFeet}ft = ${areaSinglePlotMeters.toFixed(2)} square meters`);

// c. Calculate area of 25 such plots in acres (Note: 1 acre = 4046.86 square meters)
let totalArea25PlotsMeters = areaSinglePlotMeters * 25;
let totalAreaInAcres = totalArea25PlotsMeters / 4046.86;
console.log(`   c) Total Area of 25 plots = ${totalArea25PlotsMeters.toFixed(2)} sq meters (${totalAreaInAcres.toFixed(4)} Acres)`);