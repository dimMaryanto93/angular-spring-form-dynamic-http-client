package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.dao.TableDao;
import com.maryanto.dimas.example.dto.PendudukDTO;
import com.maryanto.dimas.example.entity.Penduduk;
import com.maryanto.dimas.example.entity.TableRow;
import com.maryanto.dimas.example.entity.TransactionRow;
import com.maryanto.dimas.example.repository.PendudukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PendudukService {

    @Autowired
    private TableService service;

    @Autowired
    private TableDao tableDao;

    @Autowired
    private PendudukRepository pendudukRepository;

    public PendudukDTO getFields() {
        List<TableRow> extendedFields = tableDao.findColumnsByTableName("penduduk");
        List<Map<String, Object>> fields = service.rowJsonWrapper(extendedFields);
        return new PendudukDTO(null, null, null, null, fields);
    }

    public PendudukDTO getFields(String masterId) throws NoSuchElementException {
        List<TransactionRow> extendedFields = tableDao.findColumnByTableNameAndMasterId("penduduk", masterId);
        Optional<Penduduk> data = pendudukRepository.findById(masterId);
        List<Map<String, Object>> fields = service.jsonWrapper(extendedFields);
        Penduduk penduduk = data.get();
        return new PendudukDTO(penduduk.getId(), penduduk.getNik(), penduduk.getNama(), penduduk.getJenisKelamin(), fields);
    }

    public List<PendudukDTO> list() {
        List<Penduduk> list = pendudukRepository.findAll();
        return list.stream().map(data -> new PendudukDTO(
                data.getId(),
                data.getNik(),
                data.getNama(),
                data.getJenisKelamin(),
                new ArrayList<>()
        )).collect(Collectors.toList());
    }

    @Transactional
    public PendudukDTO save(PendudukDTO params)
            throws ParseException, NumberFormatException {
        // validate fields
        List<TransactionRow> data = service.jsonUnwrapper(params.getExtended());
        Penduduk penduduk = pendudukRepository.save(params.getPenduduk());
        data = tableDao.save("penduduk", penduduk.getId(), data);
        List<Map<String, Object>> extendData = service.jsonWrapper(data);
        return new PendudukDTO(
                penduduk.getId(),
                penduduk.getNik(),
                penduduk.getNama(),
                penduduk.getJenisKelamin(),
                extendData);
    }
}
