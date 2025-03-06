package com.example.authorization.repository;

import com.example.authorization.model.SecureResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecureResourceRepository extends JpaRepository<SecureResource, String> {

    @Query("SELECT s FROM SecureResource s WHERE s.owner=?#{authentication.name}")
    List<SecureResource> findAllForOwner();
}
