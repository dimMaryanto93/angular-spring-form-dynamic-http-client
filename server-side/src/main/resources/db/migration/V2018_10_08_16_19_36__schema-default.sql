create table sequence_generator (
  name_table    varchar(50) not null primary key,
  current_value bigint      not null
) engine = InnoDB;

create table dynamic_tables (
  id          varchar(64)  not null primary key,
  name_table  varchar(50)  not null,
  name_label  varchar(100) not null,
  name_column varchar(25)  not null,
  data_type   varchar(10)  not null
)  engine = InnoDB;

alter table dynamic_tables
  add constraint fk_dynamic_table_name foreign key (name_table)
references sequence_generator (name_table)
  on update cascade
  on delete restrict;

alter table dynamic_tables
  add constraint uq_dynamic_data unique (name_table, name_column);

create table transaction_tables (
  id                  varchar(64) not null primary key,
  name_table          varchar(50) not null,
  name_column         varchar(25) not null,
  row_number          bigint      not null,
  data_type           varchar(10) not null,
  master_id           varchar(64),
  data_value_int      integer,
  data_value_money    decimal,
  data_value_boolean  boolean,
  data_value_string   varchar(255),
  data_value_text     longtext,
  data_value_date     date,
  data_value_datetime datetime
) engine = InnoDB;

alter table transaction_tables
  add constraint fk_transaction_table_name foreign key (name_table)
references sequence_generator (name_table)
  on update cascade
  on delete restrict;

create table penduduk (
  id            varchar(64)  not null primary key,
  nik           varchar(64)  not null unique,
  nama_lengkap  varchar(150) not null,
  jenis_kelamin varchar(10)
) engine = InnoDB;