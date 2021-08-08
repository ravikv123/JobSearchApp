Job Search Utility
============
## Job Search 
The application provides a list of suitable jobs for given worker.

## Techinical Stack
Java with Springboot is used to implement the functionality.

## Testing/Deployment process

    Checkout code
    run - mvn install
    start the springboot application
    Application url: http://localhost:8080/jobMappingDemo/worker/{WorkderId}

## Implementation
The request accepts workerId as path variable and returns first 3 job matching along with worker details

### Job Filters and validations
#### Validations
1. The workerId should be valid from the worker list
2. The worker should be active.

#### Filters
1. On Driving License requirement
2. Distance limitation
3. Required Certification Match

Sort by nearest distance


## logging
logging is not implemented for the functionality

## Test Case
[TestScenarios](docs/testCases.docx)