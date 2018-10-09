package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.TransactionRow;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRowRepository extends PagingAndSortingRepository<TransactionRow, String> {
}
