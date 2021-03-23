package com.example.mzaraj.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuessGenderResponseList {

    private String gender;

    private List<String> nameList = new ArrayList<>();

    public void addNameToNameList(String name){
        nameList.add(name);
    }
}
