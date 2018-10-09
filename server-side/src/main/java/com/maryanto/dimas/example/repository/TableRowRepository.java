package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.TableRow;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TableRowRepository extends PagingAndSortingRepository<TableRow, String> {

    List<TableRow> findByTableName(String tableName);
}
