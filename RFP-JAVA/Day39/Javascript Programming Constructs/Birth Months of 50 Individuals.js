function generateBirthdays() {
    const months = [
        "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December"
    ];

    // Initialize Map with empty arrays for all months
    const birthMonthMap = new Map();
    months.forEach(month => birthMonthMap.set(month, []));

    // Generate data for 50 individuals
    for (let i = 1; i <= 50; i++) {
        const randomMonthIndex = Math.floor(Math.random() * 12);
        const randomMonthName = months[randomMonthIndex];
        const randomYear = Math.floor(Math.random() * 2) === 0 ? 1992 : 1993;
        
        const individualStr = `Person_${i} (${randomYear})`;
        
        // Append individual to their respective birth month
        birthMonthMap.get(randomMonthName).push(individualStr);
    }

    // Print the results clearly
    console.log("--- Individuals Born in the Same Month ---");
    for (let [month, people] of birthMonthMap.entries()) {
        console.log(`\n${month} (${people.length} people):`);
        console.log(people.length > 0 ? people.join(", ") : "None");
    }
}

generateBirthdays();