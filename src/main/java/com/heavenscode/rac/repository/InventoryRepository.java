package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Inventory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Inventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
