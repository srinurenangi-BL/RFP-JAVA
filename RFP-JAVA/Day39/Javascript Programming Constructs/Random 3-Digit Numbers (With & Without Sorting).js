// Helper to generate a random 3-digit number (100 to 999)
const generate3DigitNumber = () => Math.floor(Math.random() * 900) + 100;

// a & b. Generate 10 random 3-digit numbers and store them in an array
const numbers = Array.from({ length: 10 }, generate3DigitNumber);
console.log("Original Array:", numbers);

// --- Problem 1: Without Sorting ---
function find2ndMinMaxWithoutSorting(arr) {
    let max = -Infinity, secondMax = -Infinity;
    let min = Infinity, secondMin = Infinity;

    for (let num of arr) {
        // Track maximums
        if (num > max) {
            secondMax = max;
            max = num;
        } else if (num > secondMax && num !== max) {
            secondMax = num;
        }

        // Track minimums
        if (num < min) {
            secondMin = min;
            min = num;
        } else if (num < secondMin && num !== min) {
            secondMin = num;
        }
    }
    return { secondMin, secondMax };
}

const resultWithoutSorting = find2ndMinMaxWithoutSorting(numbers);
console.log("\n[Problem 1] Without Sorting:");
console.log("2nd Smallest:", resultWithoutSorting.secondMin);
console.log("2nd Largest :", resultWithoutSorting.secondMax);


// --- Problem 2: With Sorting ---
// Note: We slice() first to avoid mutating the original array. 
// JavaScript's sort() treats items as strings by default, so we need a numeric comparator (a - a).
const sortedNumbers = numbers.slice().sort((a, b) => a - b);

console.log("\n[Problem 2] Sorted Array:", sortedNumbers);
console.log("2nd Smallest (Index 1):", sortedNumbers[1]);
console.log("2nd Largest (Index length-2):", sortedNumbers[sortedNumbers.length - 2]);