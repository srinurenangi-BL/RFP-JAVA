// --- Slide Demonstration: Basic Arrays ---
function runSlidePage4ArrayDemonstration() {
    console.log("=== STARTING PAGE 4: ARRAY DEMONSTRATION ===");

    // Array Initialization [cite: 13, 14, 15]
    let origDogs = ["Bulldog", "Beagle", "Labrador"];
    let cats = new Array("Americal Curl", "Bengal");
    let birds = new Array("Falcons", "Ducks", "Flamingoes");

    // Array Copy Elements [cite: 16, 17, 18, 19]
    let slicedDogs = origDogs.slice(1, 2);
    let copyDogs = [...origDogs];
    let dogs = origDogs.slice(0);

    // Stack Functions (LIFO) Push and Pop [cite: 20, 23, 24, 25]
    dogs.push("Golden Retriever");
    dogs.pop();
    dogs[dogs.length] = "Poodle";

    // Add and Remove from First [cite: 26, 27, 28]
    let addFirst = dogs.unshift("Golden Retriever");
    let shiftDog = dogs.shift();

    // Atomic add and remove elements (where, how many to remove, element list) [cite: 29, 30]
    dogs.splice(2, 1, "Pug", "Boxer");

    // Array Functions [cite: 31, 32, 34, 35, 36]
    let animals = dogs.concat(cats, birds);
    birds.toString();
    dogs.slice(0).sort();
    let newAnimal = [...dogs, ...cats, ...birds]; // Restored the missing spread target [cite: 36]

    // Array Scanning Traversal [cite: 39, 40, 41, 42, 43, 44]
    function scanArray([first, second]) { 
        console.log("Scan: " + first + " " + second); 
    }
    scanArray(animals);

    let joinAnimals = animals.join(" ");
    let allAnimals = "";
    for (let animal of animals) {
        allAnimals += animal + " ";
    }
    console.log("Animals : " + allAnimals);
    console.log("=== END OF PAGE 4 ===\n");
}