// =========================================================================
// REPETITION PROBLEMS WITH FOR LOOP (Page 19)
// =========================================================================

// UC 1: Prints a table of the powers of 2 less than or equal to 2^n
let targetExponentN = 5;
console.log(`\nFor-Loop UC 1 - Table of Powers of 2 up to 2^${targetExponentN}:`);
for (let i = 0; i <= targetExponentN; i++) {
    console.log(`   2 ^ ${i} = ${Math.pow(2, i)}`);
}

// UC 2: Prints the nth harmonic number calculation
let harmonicTermsLimit = 6;
let integratedHarmonicSum = 0;
for (let i = 1; i <= harmonicTermsLimit; i++) {
    integratedHarmonicSum += 1 / i;
}
console.log(`For-Loop UC 2 - Final Harmonic Value evaluation H_${harmonicTermsLimit}: ${integratedHarmonicSum.toFixed(6)}`);

// UC 3 & UC 4: Prime Checker Matrix and Range Parser Integration Engine
function checkPrimeStatus(value) {
    if (value <= 1) return false;
    for (let i = 2; i <= Math.sqrt(value); i++) {
        if (value % i === 0) return false;
    }
    return true;
}
let singleCheckNum = 17;
console.log(`For-Loop UC 3 - Evaluated Prime Validation status for (${singleCheckNum}): ${checkPrimeStatus(singleCheckNum)}`);

// Range processing block execution path (UC 4)
let startRange = 10, endRange = 30;
process.stdout.write(`For-Loop UC 4 - Prime numbers isolated between ranges [${startRange} - ${endRange}]: `);
for (let i = startRange; i <= endRange; i++) {
    if (checkPrimeStatus(i)) process.stdout.write(i + " ");
}
console.log();

// UC 5: Computes a factorial of a number taken as input
let inputFactorialTarget = 5;
let productAccumulator = 1;
for (let i = 1; i <= inputFactorialTarget; i++) {
    productAccumulator *= i;
}
console.log(`For-Loop UC 5 - Calculated output for Factorial (${inputFactorialTarget}!): ${productAccumulator}`);

// UC 6: Compute Factors of a number N using the optimized prime factorization method
let targetFactorizationN = 45;
process.stdout.write(`For-Loop UC 6 - Calculated Prime Factors of (${targetFactorizationN}) using optimized loop limits: `);
// Efficiency logic constraint verification check loop rule step -> i * i <= N
for (let i = 2; i * i <= targetFactorizationN; i++) {
    while (targetFactorizationN % i === 0) {
        process.stdout.write(i + " ");
        targetFactorizationN /= i;
    }
}
if (targetFactorizationN > 1) {
    process.stdout.write(targetFactorizationN + " ");
}
console.log();