package org.jspiders.springdatasecurityproductapp.repository;

import org.jspiders.springdatasecurityproductapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.productName=:name OR p.productCategory=:name")
    List<Product> searchProduct(@Param("name") String name);
}
