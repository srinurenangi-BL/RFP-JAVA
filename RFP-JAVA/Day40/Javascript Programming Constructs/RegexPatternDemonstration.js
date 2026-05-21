export function runRegexPatternDemonstration() {
    console.log("=== STARTING REGEX COMPONENT TESTS (PAGE 16) ===");

    // Chapter Matching Test Block
    let str = 'For more information, see Chapter 3.4.5.1';
    let re = /see (chapter \d+(\.\d)*)/i;
    let found = str.match(re);
    console.log("Chapter Matching Found:", found ? found[0] : "Not Found");

    // Word Substring Match (foo*)
    let str1 = 'table football';
    let regex = RegExp('foo*');
    let result = regex.test(str1);
    console.log(`Substring matching for "foo*": ${result}`);

    // Name Layout Regex: Starts with Capital, followed by minimum 2 lowercase
    let nameRegex = RegExp('^[A-Z]{1}[a-z]{2,}$');
    let nameCheck = nameRegex.test("BridgeLabz");
    console.log(`Name structure check for "BridgeLabz": ${nameCheck}`);

    // Sequence check logic
    let sampleRegex = RegExp('^([0-9]*[a-zA-Z]){3,}[0-9]*$');
    let sampleCheck = sampleRegex.test("12abc");
    console.log(`Sequence validation match for "12abc": ${sampleCheck}`);
    
    console.log("=== END OF REGEX COMPONENT TESTS ===\n");
}