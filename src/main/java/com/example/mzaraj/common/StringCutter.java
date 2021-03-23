package com.example.mzaraj.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringCutter {
    public String[] cutAllString(String stringToCut, String separator){

        return stringToCut.split(separator);
    }

    public String[] cutStringWithWordLimit(String stringToCut, String separator,int limit){
        String [] cuttedString = stringToCut.split(separator,limit);
        String [] resut  = new String[]{cuttedString[0]};

        return resut;
    }
}