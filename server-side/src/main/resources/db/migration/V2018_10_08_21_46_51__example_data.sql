insert into sequence_generator (name_table, current_value)
values ('penduduk', 0),
       ('nasabah', 0);

insert into dynamic_tables (id, name_table, name_label, name_column, data_type)
VALUES (uuid(), 'penduduk', 'kode', 'id', 'INT'),
       (uuid(), 'penduduk', 'Nama Lengkap', 'namaLengkap', 'STRING'),
       (uuid(), 'penduduk', 'Nomor Induk Kependudukan', 'NIK', 'STRING'),
       (uuid(), 'penduduk', 'Tanggal Lahir', 'tglLahir', 'DATE');