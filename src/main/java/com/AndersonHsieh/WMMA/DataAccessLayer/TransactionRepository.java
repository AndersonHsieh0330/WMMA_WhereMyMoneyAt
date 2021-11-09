package com.AndersonHsieh.WMMA.DataAccessLayer;

import com.AndersonHsieh.WMMA.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT * FROM transaction WHERE time BETWEEN ?1 AND ?2 ;", nativeQuery = true)
    List<Transaction> findTransactionByDateInterval(String from, String to);

    @Query(value = "SELECT * FROM transaction WHERE id = ?1 ;", nativeQuery = true)
    Optional<Transaction> findTransactionByEmail(Long id);

}
