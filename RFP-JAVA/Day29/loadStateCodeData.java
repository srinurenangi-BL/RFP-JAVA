package Day29;
public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
    if (!csvFilePath.endsWith(".csv")) {
        throw new CensusAnalyserException("File type is incorrect", 
                CensusAnalyserException.ExceptionType.INCORRECT_TYPE);
    }

    try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
        CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder<CSVStates>(reader)
                .withType(CSVStates.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        
        Iterator<CSVStates> stateCodeIterator = csvToBean.iterator();
        int count = 0;
        while (stateCodeIterator.hasNext()) {
            count++;
            stateCodeIterator.next();
        }
        return count;
    } catch (IOException e) {
        throw new CensusAnalyserException(e.getMessage(), 
                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
    } catch (RuntimeException e) {
        throw new CensusAnalyserException("Delimiter or Header mismatch issue", 
                CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM);
    }
}