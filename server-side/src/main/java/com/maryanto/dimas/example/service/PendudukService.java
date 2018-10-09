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
import java.util.List;
import java.util.Map;

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
        List<TableRow> penduduk = tableDao.findColumnsByTableName("penduduk");
        List<Map<String, Object>> extended = service.rowJsonWrapper(penduduk);
        return new PendudukDTO(null, null, null, null, extended);
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
