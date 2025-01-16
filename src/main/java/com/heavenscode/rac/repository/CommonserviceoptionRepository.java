package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Commonserviceoption;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Commonserviceoption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonserviceoptionRepository extends JpaRepository<Commonserviceoption, Long> {}
