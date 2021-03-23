package com.example.mzaraj.service;

import com.example.mzaraj.constance.GenderConstants;
import com.example.mzaraj.common.StringCutter;
import com.example.mzaraj.common.exception.ObjectNotFoundException;
import com.example.mzaraj.constance.OptionConstance;
import com.example.mzaraj.entity.GuessGenderEntity;
import com.example.mzaraj.repository.GuessGenderRepository;
import com.example.mzaraj.response.GuessGenderResponse;
import com.example.mzaraj.response.GuessGenderResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessGenderServiceImpl implements GuessGenderService {

    private static final String SEPARATOR = " ";
    private final GuessGenderRepository guessGenderRepository;
    private final StringCutter stringCutter;

    @Autowired
    public GuessGenderServiceImpl(GuessGenderRepository guessGenderRepository,
                                  StringCutter stringCutter) {
        this.guessGenderRepository = guessGenderRepository;
        this.stringCutter = stringCutter;
    }

    @Override
    public GuessGenderResponse recognizePerson(String recognizingOption, String nameOfThePersonToRecognise){
        if(!(recognizingOption.equals(OptionConstance.FIRST) || recognizingOption.equals(OptionConstance.SECOND)))
            throw new ObjectNotFoundException("Check option name, and write correct");

        GuessGenderResponse recognisedPerson = switch (recognizingOption){
            case "FIRST" -> {yield recognizePersonByFirstName(nameOfThePersonToRecognise);}
            case "SECOND" -> {yield recognisePersonByFullName(nameOfThePersonToRecognise);}
            default -> throw new IllegalArgumentException("Bad option");
        };

        return recognisedPerson;
    }

    private GuessGenderResponse recognisePersonByFullName(String nameOfThePersonToRecognise) {
         String[] cuttedNameList = stringCutter.cutAllString(nameOfThePersonToRecognise, SEPARATOR);
         String recognisedGender = recogniseGenderOfPerson(cuttedNameList);

         return new GuessGenderResponse(nameOfThePersonToRecognise, recognisedGender);

    }


    private GuessGenderResponse recognizePersonByFirstName(String nameOfThePersonToRecognise) {
        String[] cuttedNameList = stringCutter.cutStringWithWordLimit(nameOfThePersonToRecognise, SEPARATOR,2);
        String recognisedGender = recogniseGenderOfPerson(cuttedNameList);

        return new GuessGenderResponse(nameOfThePersonToRecognise, recognisedGender);

    }

    private String recogniseGenderOfPerson(String[] cuttedNameList) {
        int malePoints = 0;
        int femalePoints = 0;

        for (String name: cuttedNameList) {
            List<GuessGenderEntity> checkedGenderByName =  guessGenderRepository.findAllByName(name.toUpperCase());
            if(checkedGenderByName.size() > 1 || checkedGenderByName.size() == 0)
                return GenderConstants.INCONCLUSIVE;
            else if(checkedGenderByName.get(0).getGender().equals(GenderConstants.MALE))
                malePoints++;
            else
                femalePoints++;
        }

        if(Integer.compare(malePoints,femalePoints) == 1)
            return GenderConstants.MALE;
        else if(Integer.compare(malePoints,femalePoints) == -1)
            return GenderConstants.FEMALE;
        else
            return GenderConstants.INCONCLUSIVE;
    }

    @Override
    public GuessGenderResponseList getListWithAllMaleOrFemaleNames(String gender) {
        if(!(gender.equals(GenderConstants.MALE) || gender.equals(GenderConstants.FEMALE) || gender.equals(GenderConstants.INCONCLUSIVE)))
            throw new ObjectNotFoundException("Check gender name, and write correct");
        List<GuessGenderEntity> listAllNamesWithSpecificGender = guessGenderRepository.findAllByGender(gender);
        if(listAllNamesWithSpecificGender.isEmpty())
            throw new ObjectNotFoundException("There are no names with " + gender + " gender.");

        GuessGenderResponseList listAllNamesWithSpecificGenderResponse = new GuessGenderResponseList();
        listAllNamesWithSpecificGenderResponse.setGender(gender);
        listAllNamesWithSpecificGender.forEach(response -> listAllNamesWithSpecificGenderResponse.addNameToNameList(response.getName()));

        return listAllNamesWithSpecificGenderResponse;
    }

}
