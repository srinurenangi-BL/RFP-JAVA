// --- ARRAY INITIALIZATION ---
let origDogs = ["Bulldog", "Beagle", "Labrador"]; [cite: 13]
let cats = new Array("Americal Curl", "Bengal"); [cite: 14]
let birds = new Array("Falcons", "Ducks", "Flamingoes"); [cite: 15]

// --- ARRAY COPY ELEMENTS ---
let slicedDogs = origDogs.slice(1, 2); [cite: 17]
let copyDogs = [...origDogs]; [cite: 18]
let dogs = origDogs.slice(0); [cite: 19]

// --- STACK FUNCTIONS (LIFO) PUSH AND POP ---
dogs.push("Golden Retriever"); [cite: 23]
dogs.pop(); [cite: 24]
dogs[dogs.length] = "Poodle"; [cite: 25]

// --- ADD AND REMOVE FROM FIRST (QUEUE/DEQUE) ---
let addFirst = dogs.unshift("Golden Retriever"); [cite: 27]
let shiftDog = dogs.shift(); [cite: 28]

// --- ATOMIC ADD AND REMOVE ELEMENTS (SPLICE) ---
// Syntax: (where, how many to remove, element list)
dogs.splice(2, 1, "Pug", "Boxer"); [cite: 29, 30]

// --- ARRAY FUNCTIONS & OPERATORS ---
let animals = dogs.concat(cats, birds); [cite: 32]
birds.toString(); [cite: 34]
dogs.slice(0).sort(); [cite: 35]
let newAnimal = [...dogs, ...cats, ...birds]; // Complete spread pattern [cite: 36]

// --- SCANNING / TRAVERSING ARRAYS ---
function scanArray([first, second]) { 
    console.log("Scan: " + first + " " + second); 
} [cite: 39]
scanArray(animals); [cite: 40]

let joinAnimals = animals.join(" "); [cite: 41]
let allAnimals = "";
for (let animal of animals) {
    allAnimals += animal + " ";
} [cite: 43]
console.log("Animals : " + allAnimals); [cite: 44]