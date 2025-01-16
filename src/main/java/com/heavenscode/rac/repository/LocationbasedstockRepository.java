package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Locationbasedstock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Locationbasedstock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationbasedstockRepository extends JpaRepository<Locationbasedstock, Long> {}
