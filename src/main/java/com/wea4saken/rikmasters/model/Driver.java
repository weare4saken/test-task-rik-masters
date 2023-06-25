package com.wea4saken.rikmasters.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String passportData;
    private String licenseCategory;
    private LocalDate birthDate;
    private Integer experience;

    @JsonSetter
    public void setBirthDate(LocalDate birthDate) {
        if (this.birthDate == null) {
            this.birthDate = birthDate;
        }
    }

}