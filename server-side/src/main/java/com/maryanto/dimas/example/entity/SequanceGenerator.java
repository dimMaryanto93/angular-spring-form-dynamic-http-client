package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sequence_generator")
public class SequanceGenerator {

    @Id
    @Column(name = "name_table", length = 50, nullable = false, unique = true)
    private String tableName;

    @Column(name = "current_value", nullable = false)
    private Long currentValue;
}
