package com.example.mzaraj.repository;

import com.example.mzaraj.entity.GuessGenderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuessGenderRepository extends CrudRepository<GuessGenderEntity, Long> {

    List<GuessGenderEntity> findAllByGender(String gender);

    List<GuessGenderEntity> findAllByName(String name);
}
