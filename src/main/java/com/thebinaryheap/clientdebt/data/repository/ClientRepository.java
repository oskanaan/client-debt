package com.thebinaryheap.clientdebt.data.repository;

import com.thebinaryheap.clientdebt.data.resources.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, QueryByExampleExecutor<Client> {

  @Query("select c from Client c where (c.name is null or c.name like ?1%) and c.active = ?2")
  Page<Client> searchClients(String name, Boolean active, Pageable pageable);

  List<Client> findByReferenceNumber(@Param("referenceNumber") String referenceNumber);

  List<Client> findByActiveTrue();
}