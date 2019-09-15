package com.thebinaryheap.clientdebt.data.repository;

import com.thebinaryheap.clientdebt.data.resources.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "transactions", path = "transactions")
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>, CrudRepository<Transaction, Long>, QueryByExampleExecutor<Transaction> {

  List<Transaction> findByClientId(@Param("clientId") Long clientId);
  List<Transaction> findByClientIdAndDueMonthAndDueYear(@Param("clientId") Long clientId, @Param("dueMonth") String dueMonth, @Param("dueYear") String dueYear);

}