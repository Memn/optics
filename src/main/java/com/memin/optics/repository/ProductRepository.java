package com.memin.optics.repository;

import com.memin.optics.domain.Product;

import com.memin.optics.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByShopUserLogin(String currentUserLogin, Pageable pageable);
}
