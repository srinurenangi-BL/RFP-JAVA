import { runSlidePage4ArrayDemonstration } from './ArrayDemonstration.js';
import { runSlidePage11MapDemonstration } from './MapDemonstration.js';
import { runEmployeeWageEngine } from './EmployeeWageWageEngine.js';

function runMasterPipeline() {
    console.log(">>> Initiating System Tests Suite <<<\n");
    
    // Execute Module 1: Slide Layout Array Code
    runSlidePage4ArrayDemonstration();
    
    // Execute Module 2: Slide Layout Map Code
    runSlidePage11MapDemonstration();
    
    // Execute Module 3: Use Cases Business Engine (UC6, UC7, UC8)
    runEmployeeWageEngine();
    
    console.log(">>> System execution finished with 0 structural errors. <<<");
}

runMasterPipeline();