package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.TransactionRow;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRowRepository extends PagingAndSortingRepository<TransactionRow, String> {
    List<TransactionRow> findByTableNameAndMasterId(String tableName, String masterId);
}
