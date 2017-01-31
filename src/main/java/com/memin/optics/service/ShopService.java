package com.memin.optics.service;

import com.memin.optics.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Shop.
 */
public interface ShopService {

    /**
     * Save a shop.
     *
     * @param shop the entity to save
     * @return the persisted entity
     */
    Shop save(Shop shop);

    /**
     *  Get all the shops.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Shop> findAll(Pageable pageable);

    /**
     *  Get the "id" shop.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Shop findOne(Long id);

    /**
     *  Delete the "id" shop.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the shop corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Shop> search(String query, Pageable pageable);
}
