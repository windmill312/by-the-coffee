/*package com.sychev.coffeehouse.repository;

import com.sychev.coffeehouse.model.entity.CafeEntity;
import com.sychev.coffeehouse.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByUidProduct(UUID productUid);

    Page<ProductEntity> findByCafe(CafeEntity cafe, Pageable pageable);

    void deleteByUidProduct(UUID productUid);
}*/
