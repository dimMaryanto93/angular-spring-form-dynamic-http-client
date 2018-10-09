package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.PendudukDTO;
import com.maryanto.dimas.example.entity.Penduduk;
import com.maryanto.dimas.example.service.PendudukService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/penduduk")
@Slf4j
public class PendudukController {

    @Autowired
    private PendudukService servicePenduduk;

    @GetMapping("/list")
    public List<PendudukDTO> pendudukList() {
        return servicePenduduk.list();
    }

    @GetMapping("/{id}/findById")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            return ok(servicePenduduk.getFields(id));
        } catch (NoSuchElementException emptyData) {
            return notFound().build();
        }
    }

    @GetMapping("/new")
    public PendudukDTO registerNew() {
        return servicePenduduk.getFields();
    }

    @PostMapping("/save")
    public ResponseEntity<?> setFields(@Valid @RequestBody PendudukDTO dto) {
        try {
            PendudukDTO penduduk = servicePenduduk.save(dto);
            return ok().body(penduduk);
        } catch (ParseException error) {
            log.warn("format tanggal salah", error);
            return badRequest().body("format tanggal salah");
        } catch (NumberFormatException error) {
            log.warn("format number salah", error);
            return badRequest().body("format number salah");
        }
    }


}
