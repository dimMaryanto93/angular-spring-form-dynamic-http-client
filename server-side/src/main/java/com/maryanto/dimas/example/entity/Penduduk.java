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
@Table(name = "penduduk")
public class Penduduk {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid_generator")
    @GeneratedValue(generator = "uuid_generator")
    @Column(name = "id", length = 64, nullable = false, unique = true)
    private String id;

    @Column(name = "nik", length = 64, nullable = false, unique = true)
    private String nik;

    @Column(name = "nama_lengkap", nullable = false, length = 150)
    private String nama;

    @Column(name = "jenis_kelamin", length = 10)
    private String jenisKelamin;
}
