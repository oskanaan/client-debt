package com.thebinaryheap.clientdebt.data.repository;

import com.thebinaryheap.clientdebt.data.resources.Settings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "settings", path = "settings")
public interface SettingsRepository extends PagingAndSortingRepository<Settings, Long> {
  Settings findByCode(@Param("code") String code);
}