package ca.nait.dmit.domain;

import lombok.Getter;

import java.io.BufferedReader;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlbertaCovid19CaseManager {

    //This class manages collection
    //It's a List interface not the concrete class
    @Getter
    private List<AlbertaCovid19Case> albertaCovid19CaseList = new ArrayList<>();
    public AlbertaCovid19CaseManager() throws IOException {

        //open file to read
        //will use 'try'to create or open a resource
        try(var reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Data/covid-19-alberta-statistics-data.csv")))) {
            String lineText;
            // Declare a delimiter that looks for a comma inside a value
            final var DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            // We can skip the first line since its contains header columns
            reader.readLine();
            //convert date into string
            var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //create while loop to read one line at a time
            while ( (lineText = reader.readLine()) != null) {
                // Create an object for each row in the file
                AlbertaCovid19Case currentCase = new AlbertaCovid19Case();
                String[] values = lineText.split(DELIMITER, -1);
                // The -1 limit allows for any number of fields and not discard empty fields
                // Column order of fields:
                // 0 - "Id"
                // 1 - "Date reported"
                // 2 - "Alberta Health Services Zone"
                // 3 - "Gender"
                // 4 - "Age group"
                // 5 - "Case status"
                // 6 - "Case type"
                //convert String to int
                currentCase.setId(Integer.parseInt(values[0].replaceAll("\"","")));
                currentCase.setDataReported(LocalDate.parse(values[1].replaceAll("\"",""), dateTimeFormatter));
                currentCase.setAhsZone(values[2].replaceAll("\"",""));
                currentCase.setGender(values[3].replaceAll("\"",""));
                currentCase.setAgeGroup(values[4].replaceAll("\"",""));
                currentCase.setCaseStatus(values[5].replaceAll("\"",""));
                currentCase.setCaseType(values[6].replaceAll("\"",""));

                //add object to a list
                albertaCovid19CaseList.add(currentCase);
            }
        }
    }
}