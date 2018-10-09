package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * create table transaction_tables (
 * id                  varchar(64) not null primary key,
 * name_table          varchar(50) not null,
 * name_column         varchar(25) not null,
 * row_number          long      not null,
 * data_type           varchar(10) not null,
 * data_value_int      integer,
 * data_value_money    decimal,
 * data_value_boolean  boolean,
 * data_value_string   varchar(255),
 * data_value_text     text,
 * data_value_date     date,
 * data_value_datetime datetime
 * ) engine = InnoDB;
 */
@Entity
@Table(name = "transaction_tables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRow {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false, unique = true, length = 64)
    private String id;
    @Column(name = "name_table", nullable = false, length = 50)
    private String tableName;
    @Column(name = "name_column", nullable = false, length = 25)
    private String columnName;
    @Column(name = "row_number", nullable = false)
    private Long row;
    @Column(name = "data_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DataType dataType;
    @Column(name = "master_id", length = 64)
    private String masterId;
    @Column(name = "data_value_int")
    private Integer intValue;
    @Column(name = "data_value_boolean")
    private boolean booleanValue;
    @Column(name = "data_value_money")
    private BigDecimal moneyValue;
    @Column(name = "data_value_string")
    private String stringValue;
    @Lob
    @Column(name = "data_value_text")
    private String textValue;
    @Column(name = "data_value_date")
    private Date dateValue;
    @Column(name = "data_value_datetime")
    private Timestamp datetimeValue;

}
