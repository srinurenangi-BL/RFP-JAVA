function simulateDieRolls() {
    const dieRollMap = new Map([
        [1, 0], [2, 0], [3, 0], [4, 0], [5, 0], [6, 0]
    ]);

    let targetReached = false;

    while (!targetReached) {
        const roll = Math.floor(Math.random() * 6) + 1;
        const currentCount = dieRollMap.get(roll);
        const newCount = currentCount + 1;
        
        dieRollMap.set(roll, newCount);

        if (newCount === 10) {
            targetReached = true;
        }
    }

    console.log("Final Die Roll Map Counts:", Object.fromEntries(dieRollMap));

    // Finding max and min occurrences
    let maxRoll = null, minRoll = null;
    let maxCount = -Infinity, minCount = Infinity;

    for (let [roll, count] of dieRollMap.entries()) {
        if (count > maxCount) {
            maxCount = count;
            maxRoll = roll;
        }
        if (count < minCount) {
            minCount = count;
            minRoll = roll;
        }
    }

    console.log(`\nNumber that reached maximum times (10): Die Face [${maxRoll}]`);
    console.log(`Number that was rolled minimum times (${minCount} times): Die Face [${minRoll}]`);
}

simulateDieRolls();