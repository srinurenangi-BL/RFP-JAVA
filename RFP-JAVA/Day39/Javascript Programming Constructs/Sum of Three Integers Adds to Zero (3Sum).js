function findTripletsWithZeroSum(arr) {
    const triplets = [];
    // Sort helps us use the two-pointer approach efficiently
    arr.sort((a, b) => a - b);

    for (let i = 0; i < arr.length - 2; i++) {
        // Skip duplicate values for the first element
        if (i > 0 && arr[i] === arr[i - 1]) continue;

        let left = i + 1;
        let right = arr.length - 1;

        while (left < right) {
            const sum = arr[i] + arr[left] + arr[right];

            if (sum === 0) {
                triplets.push([arr[i], arr[left], arr[right]]);
                
                // Skip duplicates for left and right pointers
                while (left < right && arr[left] === arr[left + 1]) left++;
                while (left < right && arr[right] === arr[right - 1]) right--;
                
                left++;
                right--;
            } else if (sum < 0) {
                left++; // Sum is too low, move left pointer up
            } else {
                right--; // Sum is too high, move right pointer down
            }
        }
    }
    return triplets;
}

const sampleArray = [-1, 0, 1, 2, -1, -4, -2, 3];
console.log("Triplets that sum to zero:", findTripletsWithZeroSum(sampleArray)); 