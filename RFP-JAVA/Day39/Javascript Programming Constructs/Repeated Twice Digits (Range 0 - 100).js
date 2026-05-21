const repeatedDigits = [];

for (let i = 1; i <= 100; i++) {
    // Only 2-digit numbers can have repeated identical digits in this range
    if (i > 9 && i < 100) {
        if (i % 11 === 0) { // Multiples of 11 (11, 22, 33...) have identical digits
            repeatedDigits.push(i);
        }
    }
}

console.log("Numbers with repeated digits between 0-100:", repeatedDigits);