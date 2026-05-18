package Day29;
import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private int densityPerSqKm;

    // Getters and Setters
}