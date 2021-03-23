package com.example.mzaraj.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuessGenderResponse {

    String name;

    String gender;
}
