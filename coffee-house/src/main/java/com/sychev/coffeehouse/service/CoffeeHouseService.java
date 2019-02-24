package com.sychev.coffeehouse.service;

import com.sychev.coffeehouse.model.entity.CafeEntity;
import com.sychev.coffeehouse.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CoffeeHouseService {

    Page<CafeEntity> getAllCafes(Pageable pageable);

    Page<ProductEntity> getProductsByCafe(CafeEntity cafe, Pageable pageable);

    UUID addCafe(CafeEntity entity);

    UUID addProduct(ProductEntity entity);

    void removeCafe(UUID cafeUid);

    void removeProduct(UUID productUid);

    ProductEntity getProductByUid(UUID productUid);

}