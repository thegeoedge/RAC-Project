package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.EmpSectiontbl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmpSectiontbl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpSectiontblRepository extends JpaRepository<EmpSectiontbl, Long> {}
