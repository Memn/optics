package com.memin.optics.service.impl;

import com.memin.optics.security.AuthoritiesConstants;
import com.memin.optics.security.SecurityUtils;
import com.memin.optics.service.SaleService;
import com.memin.optics.domain.Sale;
import com.memin.optics.repository.SaleRepository;
import com.memin.optics.repository.search.SaleSearchRepository;
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
 * Service Implementation for managing Sale.
 */
@Service
@Transactional
public class SaleServiceImpl implements SaleService{

    private final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Inject
    private SaleRepository saleRepository;

    @Inject
    private SaleSearchRepository saleSearchRepository;

    /**
     * Save a sale.
     *
     * @param sale the entity to save
     * @return the persisted entity
     */
    public Sale save(Sale sale) {
        log.debug("Request to save Sale : {}", sale);
        Sale result = saleRepository.save(sale);
        saleSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the sales.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Sale> findAll(Pageable pageable) {
        log.debug("Request to get all Sales");
        Page<Sale> result;
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            result = saleRepository.findAll(pageable);
        } else {
            result = saleRepository.findByShopUserLogin(SecurityUtils.getCurrentUserLogin(),pageable);
        }
        return result;
    }

    /**
     *  Get one sale by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Sale findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        Sale sale = saleRepository.findOneWithEagerRelationships(id);
        return sale;
    }

    /**
     *  Delete the  sale by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sale : {}", id);
        saleRepository.delete(id);
        saleSearchRepository.delete(id);
    }

    /**
     * Search for the sale corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Sale> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sales for query {}", query);
        Page<Sale> result = saleSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
