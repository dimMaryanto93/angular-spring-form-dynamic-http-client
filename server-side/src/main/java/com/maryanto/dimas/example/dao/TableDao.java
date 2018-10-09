package com.maryanto.dimas.example.dao;

import com.maryanto.dimas.example.entity.SequanceGenerator;
import com.maryanto.dimas.example.entity.TableRow;
import com.maryanto.dimas.example.entity.TransactionRow;
import com.maryanto.dimas.example.repository.SequanceGeneratorRepository;
import com.maryanto.dimas.example.repository.TableRowRepository;
import com.maryanto.dimas.example.repository.TransactionRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TableDao {

    @Autowired
    private TableRowRepository rowRepository;

    @Autowired
    private SequanceGeneratorRepository sequanceRepository;

    @Autowired
    private TransactionRowRepository transactionRepository;

    public List<TableRow> findColumnsByTableName(String tableName) {
        return rowRepository.findByTableName(tableName);
    }

    @Transactional
    public List<TransactionRow> save(String tableName, List<TransactionRow> rows) {
        List<TransactionRow> datas = new ArrayList<>();
        sequanceRepository.incrementByTableName(tableName);
        SequanceGenerator generator = sequanceRepository.findByTableName(tableName);

        for (TransactionRow row : rows) {
            row.setTableName(generator.getTableName());
            row.setRow(generator.getCurrentValue());
            datas.add(transactionRepository.save(row));
        }

        return datas;
    }
}
