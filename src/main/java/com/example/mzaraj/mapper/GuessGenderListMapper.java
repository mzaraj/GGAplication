package com.example.mzaraj.mapper;

import com.example.mzaraj.common.Mapper;
import com.example.mzaraj.entity.GuessGenderEntity;
import com.example.mzaraj.response.GuessGenderResponseList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GuessGenderListMapper implements Mapper<GuessGenderEntity, GuessGenderResponseList>{

    private final ModelMapper modelMapper;

    @Override
    public GuessGenderEntity toEntity(GuessGenderResponseList response) {
        return modelMapper.map(response, GuessGenderEntity.class);
    }

    @Override
    public GuessGenderResponseList toResponse(GuessGenderEntity guessGenderEntity) {
        return modelMapper.map(guessGenderEntity, GuessGenderResponseList.class);
    }
}