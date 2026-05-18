package Day29;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        // TC 1.3: Check extension/type
        if (!csvFilePath.endsWith(".csv")) {
            throw new CensusAnalyserException("File type is incorrect", 
                    CensusAnalyserException.ExceptionType.INCORRECT_TYPE);
        }

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVStateCensus.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            
            CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
            Iterator<CSVStateCensus> censusIterator = csvToBean.iterator();
            
            int count = 0;
            while (censusIterator.hasNext()) {
                count++;
                censusIterator.next();
            }
            return count;
        } catch (IOException e) {
            // TC 1.2: File path incorrect
            throw new CensusAnalyserException(e.getMessage(), 
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            // TC 1.4 & TC 1.5: Catches OpenCSV parsing errors due to bad delimiter or header mismatches
            throw new CensusAnalyserException("Delimiter or Header mismatch issue", 
                    CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM);
        }
    }
}
