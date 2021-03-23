package com.example.mzaraj;

import com.example.mzaraj.constance.GenderConstants;
import com.example.mzaraj.constance.OptionConstance;
import com.example.mzaraj.response.GuessGenderResponse;
import com.example.mzaraj.response.GuessGenderResponseList;
import com.example.mzaraj.service.GuessGenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTests {

    @Autowired
    GuessGenderService guessGenderService;

    @Test
    public void shouldCheckMenGenderByNameUsingFirstOptionHaveOneName() {

        String name = "marcin";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.FIRST,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }


    @Test
    public void shouldCheckFemaleGenderByNameUsingFirstOptionHaveOneName() {

        String name = "anna";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.FEMALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.FIRST,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }


    @Test
    public void shouldCheckMaleGenderByNameUsingFirstOptionHaveMoreThenOneName() {
        String name = "marcin krzysztof";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.FIRST,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldCheckFemaleGenderByNameUsingFirstOptionHaveMoreThenOneName() {
        String name = "anna katarzyna";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.FEMALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.FIRST,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldCheckMaleGenderByNameUsingFirstOptionHaveMoreFemaleNames() {
        String name = "marcin anna anna anna";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.FIRST,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }
//--------------------------------------------------  end of test first option to recognize person
//--------------------------------------------------  start testing second option to recognize person
    @Test
    public void shouldCheckMenGenderByNameUsingSecondOptionHaveOneName() {
        String name = "marcin";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.SECOND,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldCheckMenGenderByNameUsingSecondOptionHaveMOreThenOneName() {
        String name = "marcin krzysztof";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.SECOND,name);

        assertEquals(response, shouldResponse);
    }

    @Test
    public void shouldCheckMenGenderByNameUsingSecondOptionHaveMultiNames() {
        String name = "marcin katarzyna marcin";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.MALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.SECOND,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldCheckFemaleGenderByNameUsingSecondOptionHaveMultiNames() {
        String name = "anna marcin anna";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.FEMALE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.SECOND,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldCheckInconclusiveGenderByNameUsingFirstOptionWithNamesUnderDatabase() {
        String name = "anna hania";
        GuessGenderResponse shouldResponse = new GuessGenderResponse(name, GenderConstants.INCONCLUSIVE);

        GuessGenderResponse response = guessGenderService.recognizePerson(OptionConstance.SECOND,name);

        assertEquals(response.getGender(), shouldResponse.getGender());
    }

    @Test
    public void shouldTakeListOfMaleNames() {
        ArrayList checkedList = new ArrayList();

        checkedList.add("JAN");
        checkedList.add("KAMIL");
        checkedList.add("MAREK");
        checkedList.add("MARCIN");
        checkedList.add("KRZYSZTOF");

        GuessGenderResponseList shouldGuessGenderResponseList = new GuessGenderResponseList(GenderConstants.MALE,checkedList);

        GuessGenderResponseList response = guessGenderService.getListWithAllMaleOrFemaleNames(GenderConstants.MALE);

        assertEquals(shouldGuessGenderResponseList, response);
    }



}
