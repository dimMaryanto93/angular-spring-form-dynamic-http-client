package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.Penduduk;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PendudukRepository extends PagingAndSortingRepository<Penduduk, String> {
}
