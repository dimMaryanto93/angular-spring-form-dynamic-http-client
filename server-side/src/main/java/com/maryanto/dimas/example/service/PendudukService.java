package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.dao.TableDao;
import com.maryanto.dimas.example.entity.TableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PendudukService {

    @Autowired
    private TableService service;

    @Autowired
    private TableDao tableDao;

    public List<Map<String, Object>> getFields() {
        List<TableRow> penduduk = tableDao.findColumnsByTableName("penduduk");
        return service.jsonWrapper(penduduk);
    }
}
