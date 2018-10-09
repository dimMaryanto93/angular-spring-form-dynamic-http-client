package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.SequanceGenerator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SequanceGeneratorRepository extends CrudRepository<SequanceGenerator, String> {

    SequanceGenerator findByTableName(String tableName);

    @Modifying
    @Query("update SequanceGenerator set currentValue = (currentValue + 1) where tableName = ?1")
    Integer incrementByTableName(String tableName);
}
