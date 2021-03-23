package com.example.mzaraj.service;

import com.example.mzaraj.response.GuessGenderResponse;
import com.example.mzaraj.response.GuessGenderResponseList;

public interface GuessGenderService {
    GuessGenderResponse recognizePerson(String recognizingOption, String nameOfThePersonToRecognise) ;

    GuessGenderResponseList getListWithAllMaleOrFemaleNames(String gender);

}
