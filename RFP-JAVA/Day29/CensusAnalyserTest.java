package Day29;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CensusAnalyserTest {

    private static final String REQ_CENSUS_CSV = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_TXT_FILE = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String BAD_DELIMITER_CSV = "./src/test/resources/IndiaStateCensusDataBadDelimiter.csv";
    private static final String BAD_HEADER_CSV = "./src/test/resources/IndiaStateCensusDataBadHeader.csv";

    @Test // TC 1.1: Happy Test Case
    public void givenIndianCensusCSVFile_ReturnsCorrectRecordCount() throws CensusAnalyserException {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        int count = analyser.loadIndiaCensusData(REQ_CENSUS_CSV);
        Assertions.assertEquals(29, count);
    }

    @Test // TC 1.2: Sad Test Case - Path is wrong
    public void givenWrongCensusCSVFile_ThrowsCustomException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadIndiaCensusData(WRONG_CSV_PATH);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test // TC 1.3: Sad Test Case - Type/Extension incorrect
    public void givenWrongCensusFileType_ThrowsCustomException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadIndiaCensusData(WRONG_TXT_FILE);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_TYPE, e.type);
        }
    }

    @Test // TC 1.4: Sad Test Case - Delimiter incorrect
    public void givenCensusFile_WithWrongDelimiter_ThrowsCustomException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadIndiaCensusData(BAD_DELIMITER_CSV);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test // TC 1.5: Sad Test Case - Header incorrect
    public void givenCensusFile_WithWrongHeader_ThrowsCustomException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadIndiaCensusData(BAD_HEADER_CSV);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }
}
