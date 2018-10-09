package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.service.PendudukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/penduduk")
public class PendudukController {

    @Autowired
    private PendudukService servicePenduduk;

    @GetMapping("/fields")
    public List<Map<String, Object>> getFields() {
        return servicePenduduk.getFields();
    }

}
