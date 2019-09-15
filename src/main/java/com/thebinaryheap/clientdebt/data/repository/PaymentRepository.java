package com.thebinaryheap.clientdebt.data.repository;

import com.thebinaryheap.clientdebt.data.resources.Payment;
import com.thebinaryheap.clientdebt.data.resources.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "payments", path = "payments")
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>, CrudRepository<Payment, Long>, QueryByExampleExecutor<Payment> {

  List<Payment> findByClientIdAndDueMonthAndDueYear(@Param("clientId") Long clientId, @Param("dueMonth") String dueMonth, @Param("dueYear") String dueYear);

}