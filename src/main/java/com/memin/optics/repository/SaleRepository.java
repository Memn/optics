package com.memin.optics.repository;

import com.memin.optics.domain.Sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Sale entity.
 */
@SuppressWarnings("unused")
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query("select distinct sale from Sale sale left join fetch sale.products")
    List<Sale> findAllWithEagerRelationships();

    @Query("select sale from Sale sale left join fetch sale.products where sale.id =:id")
    Sale findOneWithEagerRelationships(@Param("id") Long id);

    Page<Sale> findByShopUserLogin(String currentUserLogin, Pageable pageable);
}
