// --- Slide Demonstration: ES6 Map ---
export function runSlidePage11MapDemonstration() {
    console.log("=== STARTING PAGE 11: MAP DEMONSTRATION ===");

    // setting the values [cite: 140, 141, 143, 145, 147, 148, 149]
    let keyString = 'a string';
    let keyObj = {};
    let keyFunc = function() {};

    let myMap = new Map();
    myMap.set(keyString, "value associated with 'a string'");
    myMap.set(keyObj, 'value associated with key0bj');
    myMap.set(keyFunc, 'value associated with keyFunc');

    // getting the values [cite: 151, 152, 153]
    let size = myMap.size;
    let valStr = myMap.get(keyString);
    let isKeyExist = myMap.has('a string');

    // Demonstrating the 4 explicit loops [cite: 154, 159, 161, 165]
    for (let [key, value] of myMap) console.log("Loop1: " + key + " = " + value);
    for (let [key, value] of myMap.entries()) console.log("Loop2: " + key + '=' + value);
    for (let key of myMap.keys()) console.log("Loop3: " + key);
    for (let value of myMap.values()) console.log("Loop4: " + value);

    // Merge two maps [cite: 168, 169, 170, 173]
    let first = new Map([ [1, 'one'], [2, 'two'], [3, 'three'] ]);
    let second = new Map([ [1, 'uno'], [2, 'dos'] ]);
    let merged = new Map([...first, ...second, [1, 'eins']]);

    let haskey = merged.has(1); // [cite: 171]
    let delKey = merged.delete(1); // [cite: 172]

    if (merged.has(1)) console.log(merged.get(1)); // [cite: 174]
    console.log("Pos2: " + merged.get(2)); // [cite: 175]
    
    merged.clear(); // [cite: 176]
    console.log("Map Size After Clear: " + merged.size); // [cite: 177]
    console.log("=== END OF PAGE 11 ===\n");
}