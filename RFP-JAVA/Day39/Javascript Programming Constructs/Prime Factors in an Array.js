function getPrimeFactors(n) {
    const factors = [];
    let d = 2;
    
    // Check for 2s, then odd numbers
    while (n > 1) {
        while (n % d === 0) {
            factors.push(d);
            n /= d;
        }
        d++;
        if (d * d > n && n > 1) {
            factors.push(n);
            break;
        }
    }
    return factors;
}

const targetNumber = 56; // Example number
console.log(`Prime factors of ${targetNumber}:`, getPrimeFactors(targetNumber));