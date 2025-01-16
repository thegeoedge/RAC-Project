package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autojobempallocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autojobempallocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutojobempallocationRepository extends JpaRepository<Autojobempallocation, Long> {}
