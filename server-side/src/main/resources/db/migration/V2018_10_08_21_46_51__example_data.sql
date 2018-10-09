insert into sequence_generator (name_table, current_value)
values ('penduduk', 0),
       ('nasabah', 0);

insert into dynamic_tables (id, name_table, name_label, name_column, data_type)
VALUES (uuid(), 'penduduk', 'Tanggal Registrasi', 'tglRegistration', 'DATE_TIME'),
       (uuid(), 'penduduk', 'Tanggal Lahir', 'tanggalLahir', 'DATE'),
       (uuid(), 'penduduk', 'Nama Ayah', 'namaAyah', 'STRING'),
       (uuid(), 'penduduk', 'Nama IBU', 'namaIbu', 'STRING'),
       (uuid(), 'penduduk', 'Alamat Domisili', 'alamatDomisili', 'TEXT'),
       (uuid(), 'penduduk', 'Alamat Lahir', 'alamatLahir', 'TEXT');