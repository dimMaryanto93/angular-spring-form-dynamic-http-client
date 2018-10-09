package com.maryanto.dimas.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maryanto.dimas.example.entity.Penduduk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukDTO {

    private String id;
    @NotNull
    private String nik;
    @NotNull
    private String namaLengkap;
    @NotNull
    private String jenisKelamin;
    private List<Map<String, Object>> extended = new ArrayList<>();

    @JsonIgnore
    public Penduduk getPenduduk() {
        return new Penduduk(this.id, this.nik, this.namaLengkap, this.jenisKelamin);
    }
}
