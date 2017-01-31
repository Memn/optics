package com.memin.optics.service.impl;

import com.memin.optics.service.ShopService;
import com.memin.optics.domain.Shop;
import com.memin.optics.repository.ShopRepository;
import com.memin.optics.repository.search.ShopSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Shop.
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService{

    private final Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);
    
    @Inject
    private ShopRepository shopRepository;

    @Inject
    private ShopSearchRepository shopSearchRepository;

    /**
     * Save a shop.
     *
     * @param shop the entity to save
     * @return the persisted entity
     */
    public Shop save(Shop shop) {
        log.debug("Request to save Shop : {}", shop);
        Shop result = shopRepository.save(shop);
        shopSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the shops.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Shop> findAll(Pageable pageable) {
        log.debug("Request to get all Shops");
        Page<Shop> result = shopRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one shop by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Shop findOne(Long id) {
        log.debug("Request to get Shop : {}", id);
        Shop shop = shopRepository.findOne(id);
        return shop;
    }

    /**
     *  Delete the  shop by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Shop : {}", id);
        shopRepository.delete(id);
        shopSearchRepository.delete(id);
    }

    /**
     * Search for the shop corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Shop> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Shops for query {}", query);
        Page<Shop> result = shopSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
