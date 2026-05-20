// =========================================================================
// FUNCTIONS PRACTICE PROBLEMS (Page 24)
// =========================================================================

// UC 1: Temperature Unit Conversions with Case constraints and validation limits
function calculateTemperatureConversion(scalarValue, scaleUnitToken) {
    console.log(`\nFunctions UC 1 - Converting temperature: ${scalarValue}°${scaleUnitToken.toUpperCase()}`);
    switch (scaleUnitToken.toUpperCase()) {
        case 'C':
            // Check boundary limits for Freezing Point (0°C) and Boiling Point (100°C)
            if (scalarValue >= 0 && scalarValue <= 100) {
                let convertedFahrenheit = (scalarValue * 9 / 5) + 32;
                console.log(`   Result: ${scalarValue}°C = ${convertedFahrenheit.toFixed(2)}°F`);
                return convertedFahrenheit;
            } else {
                console.log("   Execution Terminated: Values out of water fluid state temperature validation bounds (0°C - 100°C).");
                return null;
            }
        case 'F':
            // Check boundary limits for Freezing Point (32°F) and Boiling Point (212°F)
            if (scalarValue >= 32 && scalarValue <= 212) {
                let convertedCelsius = (scalarValue - 32) * 5 / 9;
                console.log(`   Result: ${scalarValue}°F = ${convertedCelsius.toFixed(2)}°C`);
                return convertedCelsius;
            } else {
                console.log("   Execution Terminated: Values out of water fluid state temperature validation bounds (32°F - 212°F).");
                return null;
            }
        default:
            console.log("   Error Flag: Unknown target scaling indicator.");
            return null;
    }
}
calculateTemperatureConversion(100, 'C'); // Test boiling limits conversion
calculateTemperatureConversion(50, 'F');  // Test intermediate conversion

// UC 2: Function to check if two values are distinct Palindromes
function evaluatePalindromeEquality(firstNumericInput, secondNumericInput) {
    let rawString = firstNumericInput.toString();
    let reversedValueString = rawString.split('').reverse().join('');
    return (rawString === reversedValueString) && (firstNumericInput === secondNumericInput);
}
console.log(`Functions UC 2 - Evaluation test matches for [121, 121]: ${evaluatePalindromeEquality(121, 121)}`);

// UC 3: Prime-Palindrome Composite Verification
// a. Function to check if a number is prime
function evaluatePrimeStatus(number) {
    if (number <= 1) return false;
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i === 0) return false;
    }
    return true;
}

// b. Function to get the Palindrome representation inversion value
function generateInvertedPalindromeInteger(targetValue) {
    let invertedText = targetValue.toString().split('').reverse().join('');
    return parseInt(invertedText, 10);
}

// c. Main validation routine execution path workflow orchestrator
let targetedUserNumberInput = 13; 
console.log(`Functions UC 3 - Starting Composite Pipeline analysis for target (${targetedUserNumberInput}):`);

if (evaluatePrimeStatus(targetedUserNumberInput)) {
    console.log(`   a) Primary confirmation: Base digit (${targetedUserNumberInput}) is Prime.`);
    let calculatedPalindromeInversion = generateInvertedPalindromeInteger(targetedUserNumberInput);
    console.log(`   b) Inversion reflection calculated value: ${calculatedPalindromeInversion}`);
    
    // c. Check if the Palindrome number is also prime
    let isPalindromePrime = evaluatePrimeStatus(calculatedPalindromeInversion);
    console.log(`   c) Final Verification Status: Is inverted number prime? -> ${isPalindromePrime}`);
} else {
    console.log(`   a) Baseline failure: Target selection input (${targetedUserNumberInput}) isn't prime. Pipeline aborted.`);
}