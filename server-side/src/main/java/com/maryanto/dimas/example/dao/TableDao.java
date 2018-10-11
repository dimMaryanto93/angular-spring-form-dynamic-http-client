package com.maryanto.dimas.example.dao;

import com.maryanto.dimas.example.entity.SequanceGenerator;
import com.maryanto.dimas.example.entity.TableRow;
import com.maryanto.dimas.example.entity.TransactionRow;
import com.maryanto.dimas.example.repository.SequanceGeneratorRepository;
import com.maryanto.dimas.example.repository.TableRowRepository;
import com.maryanto.dimas.example.repository.TransactionRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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

    public List<TransactionRow> findColumnByTableNameAndMasterId(String tableName, String masterId){
        return transactionRepository.findByTableNameAndMasterId(tableName, masterId);
    }

    public List<TransactionRow> save(String tableName, String primaryId, List<TransactionRow> rows) {
        List<TransactionRow> datas = new ArrayList<>();
        sequanceRepository.incrementByTableName(tableName);
        SequanceGenerator generator = sequanceRepository.findByTableName(tableName);

        for (TransactionRow row : rows) {
            row.setMasterId(primaryId);
            row.setTableName(generator.getTableName());
            row.setRow(generator.getCurrentValue());
            datas.add(transactionRepository.save(row));
        }

        return datas;
    }

    public List<TransactionRow> update(String tableName, String primaryId,List<TransactionRow> rows) {
        List<TransactionRow> datas = new ArrayList<>();
        SequanceGenerator generator = sequanceRepository.findByTableName(tableName);

        for (TransactionRow row : rows) {
            row.setTableName(generator.getTableName());
            row.setRow(generator.getCurrentValue());
            row.setMasterId(primaryId);
            datas.add(transactionRepository.save(row));
        }

        return datas;
    }
}
