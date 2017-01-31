package com.memin.optics.repository;

import com.memin.optics.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Shop entity.
 */
@SuppressWarnings("unused")
public interface ShopRepository extends JpaRepository<Shop,Long> {

    @Query("select shop from Shop shop where shop.user.login = ?#{principal.username}")
    Page<Shop> findByUserIsCurrentUser(Pageable pageable);

}
