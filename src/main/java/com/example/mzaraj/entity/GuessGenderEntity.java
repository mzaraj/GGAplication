package com.example.mzaraj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GuessGenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    String gender;

    public static GuessGenderEntity of (String name, String gender) {
        return new GuessGenderEntity(null, name, gender);
    }
}
