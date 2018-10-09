package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.service.PendudukService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/penduduk")
@Slf4j
public class PendudukController {

    @Autowired
    private PendudukService servicePenduduk;

    @GetMapping("/fields")
    public List<Map<String, Object>> getFields() {
        return servicePenduduk.getFields();
    }

    @PostMapping("/fields")
    public ResponseEntity<?> setFields(@RequestBody List<Map<String, Object>> params) {
        try {
            List<Map<String, Object>> dataPenduduk = servicePenduduk.save(params);
            return ok().body(dataPenduduk);
        } catch (ParseException error) {
            log.warn("format tanggal salah", error);
            return badRequest().body("format tanggal salah");
        } catch (NumberFormatException error) {
            log.warn("format number salah", error);
            return badRequest().body("format number salah");
        }
    }

}
