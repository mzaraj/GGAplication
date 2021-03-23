package com.example.mzaraj.controller;

import com.example.mzaraj.common.exception.ObjectNotFoundException;
import com.example.mzaraj.response.GuessGenderResponse;
import com.example.mzaraj.response.GuessGenderResponseList;
import com.example.mzaraj.service.GuessGenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/GuessGender/info")
@AllArgsConstructor
public class GuessGenderController {

    GuessGenderService guessGenderService;

    /**
     * Method return list of male or female names
     * @param gender possible values: MALE, FEMALE, INCONCLUSIVE
     * @return GuessGenderResponseList
     */
    @GetMapping("/all/tokens")
    public ResponseEntity<GuessGenderResponseList> getAllMaleAndFemaleNames(@RequestParam("gender") String gender){
        GuessGenderResponseList listOfNameOfAllMaleAndFemalePerson= guessGenderService.getListWithAllMaleOrFemaleNames(gender);
        log.info("List of " + gender + " names was getted");
        if(listOfNameOfAllMaleAndFemalePerson.getNameList().isEmpty())
            throw new ObjectNotFoundException("There are not names with gender " + gender );

        return ResponseEntity.ok(listOfNameOfAllMaleAndFemalePerson);
    }

    /**
     * Mwthod check gender of incoming person's names
     * @param recognizingOption - option to check nemes, First option check only first incoming name in string
     *                          secound option check all names in string
     * @param nameOfThePersonToRecognise incoming string with person names.
     * @return incoming person name and his/her gender counld be MEN, FEMALE and INCONCLUSIVE
     */
    @GetMapping("/check/gender")
    public ResponseEntity<GuessGenderResponse> checkIfPersonIsMaleOrFemale(
                        @RequestParam("rOption") String recognizingOption,
                        @RequestParam("name") String nameOfThePersonToRecognise){

        GuessGenderResponse checkedGenderPerson = guessGenderService.recognizePerson(recognizingOption,nameOfThePersonToRecognise);

        return ResponseEntity.ok().body(checkedGenderPerson);

    }

}
