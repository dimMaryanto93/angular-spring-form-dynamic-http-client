package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dynamic_tables")
public class TableRow {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id", nullable = false, length = 64)
    private String id;
    @Column(name = "name_table", length = 50, nullable = false)
    private String tableName;
    @Column(name = "name_label", nullable = false, length = 100)
    private String labelName;
    @Column(name = "name_column", length = 25, nullable = false)
    private String columnName;
    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", nullable = false)
    private DataType dataType;
}
