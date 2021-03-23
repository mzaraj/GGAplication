package com.example.mzaraj.common;

public interface Mapper<ENTITY, RESPONSE> {

    ENTITY toEntity(RESPONSE response);

    RESPONSE toResponse(ENTITY entity);
}
