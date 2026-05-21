import { runEmployeeWageObjectEngine } from './EmployeeWageObjectEngine.js';
import { runRegexPatternDemonstration } from './RegexPatternDemonstration.js';
import { PinCodeValidator } from './PinCodeValidator.js';
import { EmailValidator } from './EmailValidator.js';

function executeMasterPipeline() {
    console.log(">>>> STARTING PIPELINES <<<<\n");

    runEmployeeWageObjectEngine();
    runRegexPatternDemonstration();
    PinCodeValidator.runTests();
    EmailValidator.runTests();

    console.log(">>>> ALL RUNS COMPLETED WITH ZERO ERRORS <<<<");
}

executeMasterPipeline();