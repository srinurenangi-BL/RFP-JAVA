// =========================================================================
// REPETITION PROBLEMS WITH WHILE LOOP (Page 21)
// =========================================================================

// UC 1: Print table of powers of 2 until the exponent parameter is reached or 256 is passed
let maxExponentTarget = 10;
let loopExponentIndex = 0;
let computedPowerValue = 0;
console.log("\nWhile-Loop UC 1 - Powers of 2 Table capped at 256 threshold boundaries:");
while (loopExponentIndex <= maxExponentTarget && computedPowerValue < 256) {
    computedPowerValue = Math.pow(2, loopExponentIndex);
    console.log(`   2 ^ ${loopExponentIndex} = ${computedPowerValue}`);
    loopExponentIndex++;
}

// UC 2: Magic Number interactive tracking simulator implementation
let searchLowerBound = 1;
let searchUpperBound = 100;
console.log(`While-Loop UC 2 - Initiating Magic Number binary logic search range pipeline...`);
// Simulating an imagined target internal configuration constant state selection
let hiddenSecretMagicNumber = 67; 

while (searchLowerBound !== searchUpperBound) {
    let currentGuessMidpoint = Math.floor((searchLowerBound + searchUpperBound) / 2);
    if (hiddenSecretMagicNumber <= currentGuessMidpoint) {
        searchUpperBound = currentGuessMidpoint; // Select lower subfield segment
    } else {
        searchLowerBound = currentGuessMidpoint + 1; // Select upper subfield segment
    }
}
console.log(`   Isolated Target Magic Number: ${searchLowerBound}`);

// UC 3: Extend Flip Coin problem until either Heads or Tails wins 11 times
let aggregateHeadsScore = 0;
let aggregateTailsScore = 0;
while (aggregateHeadsScore < 11 && aggregateTailsScore < 11) {
    let binaryFlipResult = Math.floor(Math.random() * 2);
    if (binaryFlipResult === 0) aggregateHeadsScore++;
    else aggregateTailsScore++;
}
console.log(`While-Loop UC 3 - Series final scores matrix -> Heads: ${aggregateHeadsScore} | Tails: ${aggregateTailsScore}`);
console.log(`   Champion Winner: ` + (aggregateHeadsScore === 11 ? "Heads wins the match!" : "Tails wins the match!"));

// UC 4: Gambler Simulation engine tracking loops
let cashPotBalance = 100;
let milestoneGoalCap = 200;
let cumulativeBetsPlaced = 0;
let totalWinsTracked = 0;

while (cashPotBalance > 0 && cashPotBalance < milestoneGoalCap) {
    cumulativeBetsPlaced++;
    let randomBetOutcomeRoll = Math.floor(Math.random() * 2); // 0 means loss, 1 means win
    if (randomBetOutcomeRoll === 1) {
        cashPotBalance++;
        totalWinsTracked++;
    } else {
        cashPotBalance--;
    }
}
console.log(`While-Loop UC 4 - Gambler Run Summary Stats:`);
console.log(`   Total Bets Placed: ${cumulativeBetsPlaced} | Wins Record Count: ${totalWinsTracked}`);
console.log(`   Final Wallet Ending Balance: Rs ${cashPotBalance}`);