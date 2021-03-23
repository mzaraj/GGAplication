package com.example.mzaraj.mapper;

import com.example.mzaraj.common.Mapper;
import com.example.mzaraj.entity.GuessGenderEntity;
import com.example.mzaraj.response.GuessGenderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuessGenderMapper implements Mapper<GuessGenderEntity, GuessGenderResponse> {

    private final ModelMapper modelMapper;

    @Override
    public GuessGenderEntity toEntity(GuessGenderResponse response) {
        return modelMapper.map(response, GuessGenderEntity.class);
    }

    @Override
    public GuessGenderResponse toResponse(GuessGenderEntity guessGenderEntity) {
        return modelMapper.map(guessGenderEntity, GuessGenderResponse.class);
    }
}
