package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Inventory;
import com.heavenscode.rac.repository.InventoryRepository;
import com.heavenscode.rac.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.heavenscode.rac.domain.Inventory}.
 */
@RestController
@RequestMapping("/api/inventories")
@Transactional
public class InventoryResource {

    private final Logger log = LoggerFactory.getLogger(InventoryResource.class);

    private static final String ENTITY_NAME = "inventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventoryRepository inventoryRepository;

    public InventoryResource(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * {@code POST  /inventories} : Create a new inventory.
     *
     * @param inventory the inventory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventory, or with status {@code 400 (Bad Request)} if the inventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) throws URISyntaxException {
        log.debug("REST request to save Inventory : {}", inventory);
        if (inventory.getId() != null) {
            throw new BadRequestAlertException("A new inventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inventory = inventoryRepository.save(inventory);
        return ResponseEntity.created(new URI("/api/inventories/" + inventory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, inventory.getId().toString()))
            .body(inventory);
    }

    /**
     * {@code PUT  /inventories/:id} : Updates an existing inventory.
     *
     * @param id the id of the inventory to save.
     * @param inventory the inventory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventory,
     * or with status {@code 400 (Bad Request)} if the inventory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Inventory inventory
    ) throws URISyntaxException {
        log.debug("REST request to update Inventory : {}, {}", id, inventory);
        if (inventory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        inventory = inventoryRepository.save(inventory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inventory.getId().toString()))
            .body(inventory);
    }

    /**
     * {@code PATCH  /inventories/:id} : Partial updates given fields of an existing inventory, field will ignore if it is null
     *
     * @param id the id of the inventory to save.
     * @param inventory the inventory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventory,
     * or with status {@code 400 (Bad Request)} if the inventory is not valid,
     * or with status {@code 404 (Not Found)} if the inventory is not found,
     * or with status {@code 500 (Internal Server Error)} if the inventory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Inventory> partialUpdateInventory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Inventory inventory
    ) throws URISyntaxException {
        log.debug("REST request to partial update Inventory partially : {}, {}", id, inventory);
        if (inventory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Inventory> result = inventoryRepository
            .findById(inventory.getId())
            .map(existingInventory -> {
                if (inventory.getCode() != null) {
                    existingInventory.setCode(inventory.getCode());
                }
                if (inventory.getPartnumber() != null) {
                    existingInventory.setPartnumber(inventory.getPartnumber());
                }
                if (inventory.getName() != null) {
                    existingInventory.setName(inventory.getName());
                }
                if (inventory.getDescription() != null) {
                    existingInventory.setDescription(inventory.getDescription());
                }
                if (inventory.getType() != null) {
                    existingInventory.setType(inventory.getType());
                }
                if (inventory.getClassification1() != null) {
                    existingInventory.setClassification1(inventory.getClassification1());
                }
                if (inventory.getClassification2() != null) {
                    existingInventory.setClassification2(inventory.getClassification2());
                }
                if (inventory.getClassification3() != null) {
                    existingInventory.setClassification3(inventory.getClassification3());
                }
                if (inventory.getClassification4() != null) {
                    existingInventory.setClassification4(inventory.getClassification4());
                }
                if (inventory.getClassification5() != null) {
                    existingInventory.setClassification5(inventory.getClassification5());
                }
                if (inventory.getUnitofmeasurement() != null) {
                    existingInventory.setUnitofmeasurement(inventory.getUnitofmeasurement());
                }
                if (inventory.getDecimalplaces() != null) {
                    existingInventory.setDecimalplaces(inventory.getDecimalplaces());
                }
                if (inventory.getIsassemblyunit() != null) {
                    existingInventory.setIsassemblyunit(inventory.getIsassemblyunit());
                }
                if (inventory.getAssemblyunitof() != null) {
                    existingInventory.setAssemblyunitof(inventory.getAssemblyunitof());
                }
                if (inventory.getReorderlevel() != null) {
                    existingInventory.setReorderlevel(inventory.getReorderlevel());
                }
                if (inventory.getLastcost() != null) {
                    existingInventory.setLastcost(inventory.getLastcost());
                }
                if (inventory.getLastsellingprice() != null) {
                    existingInventory.setLastsellingprice(inventory.getLastsellingprice());
                }
                if (inventory.getLmu() != null) {
                    existingInventory.setLmu(inventory.getLmu());
                }
                if (inventory.getLmd() != null) {
                    existingInventory.setLmd(inventory.getLmd());
                }
                if (inventory.getAvailablequantity() != null) {
                    existingInventory.setAvailablequantity(inventory.getAvailablequantity());
                }
                if (inventory.getHasbatches() != null) {
                    existingInventory.setHasbatches(inventory.getHasbatches());
                }
                if (inventory.getItemspecfilepath() != null) {
                    existingInventory.setItemspecfilepath(inventory.getItemspecfilepath());
                }
                if (inventory.getItemimagepath() != null) {
                    existingInventory.setItemimagepath(inventory.getItemimagepath());
                }
                if (inventory.getReturnprice() != null) {
                    existingInventory.setReturnprice(inventory.getReturnprice());
                }
                if (inventory.getActiveitem() != null) {
                    existingInventory.setActiveitem(inventory.getActiveitem());
                }
                if (inventory.getMinstock() != null) {
                    existingInventory.setMinstock(inventory.getMinstock());
                }
                if (inventory.getMaxstock() != null) {
                    existingInventory.setMaxstock(inventory.getMaxstock());
                }
                if (inventory.getDailyaverage() != null) {
                    existingInventory.setDailyaverage(inventory.getDailyaverage());
                }
                if (inventory.getBufferlevel() != null) {
                    existingInventory.setBufferlevel(inventory.getBufferlevel());
                }
                if (inventory.getLeadtime() != null) {
                    existingInventory.setLeadtime(inventory.getLeadtime());
                }
                if (inventory.getBuffertime() != null) {
                    existingInventory.setBuffertime(inventory.getBuffertime());
                }
                if (inventory.getSaftydays() != null) {
                    existingInventory.setSaftydays(inventory.getSaftydays());
                }
                if (inventory.getAccountcode() != null) {
                    existingInventory.setAccountcode(inventory.getAccountcode());
                }
                if (inventory.getAccountid() != null) {
                    existingInventory.setAccountid(inventory.getAccountid());
                }
                if (inventory.getCasepackqty() != null) {
                    existingInventory.setCasepackqty(inventory.getCasepackqty());
                }
                if (inventory.getIsregistered() != null) {
                    existingInventory.setIsregistered(inventory.getIsregistered());
                }
                if (inventory.getDefaultstocklocationid() != null) {
                    existingInventory.setDefaultstocklocationid(inventory.getDefaultstocklocationid());
                }
                if (inventory.getRackno() != null) {
                    existingInventory.setRackno(inventory.getRackno());
                }
                if (inventory.getBarcodeimage() != null) {
                    existingInventory.setBarcodeimage(inventory.getBarcodeimage());
                }
                // if (inventory.getBarcodeimageContentType() != null) {
                //     existingInventory.setBarcodeimageContentType(inventory.getBarcodeimageContentType());
                // }
                if (inventory.getCommissionempid() != null) {
                    existingInventory.setCommissionempid(inventory.getCommissionempid());
                }
                if (inventory.getChecktypeid() != null) {
                    existingInventory.setChecktypeid(inventory.getChecktypeid());
                }
                if (inventory.getChecktype() != null) {
                    existingInventory.setChecktype(inventory.getChecktype());
                }
                if (inventory.getReorderqty() != null) {
                    existingInventory.setReorderqty(inventory.getReorderqty());
                }
                if (inventory.getNotininvoice() != null) {
                    existingInventory.setNotininvoice(inventory.getNotininvoice());
                }

                return existingInventory;
            })
            .map(inventoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inventory.getId().toString())
        );
    }

    /**
     * {@code GET  /inventories} : get all the inventories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Inventory>> getAllInventories(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Inventories");
        Page<Inventory> page = inventoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inventories/:id} : get the "id" inventory.
     *
     * @param id the id of the inventory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable("id") Long id) {
        log.debug("REST request to get Inventory : {}", id);
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inventory);
    }

    /**
     * {@code DELETE  /inventories/:id} : delete the "id" inventory.
     *
     * @param id the id of the inventory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") Long id) {
        log.debug("REST request to delete Inventory : {}", id);
        inventoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
