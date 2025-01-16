package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Inventorybatches;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Inventorybatches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventorybatchesRepository extends JpaRepository<Inventorybatches, Long> {}
