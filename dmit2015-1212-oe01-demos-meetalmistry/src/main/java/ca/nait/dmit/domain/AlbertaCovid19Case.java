package ca.nait.dmit.domain;

import lombok.Data;

import java.time.LocalDate;

//GENERATES GETTERS AND SETTERS
@Data
public class AlbertaCovid19Case {
    private int id;
    private LocalDate dataReported;
    private String ahsZone;
    private String gender;
    private String ageGroup;
    private String caseStatus;
    private String caseType;


}
