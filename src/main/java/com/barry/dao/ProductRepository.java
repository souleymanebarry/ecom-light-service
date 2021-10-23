package com.barry.dao;

import com.barry.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {

    @RestResource(path = "/selectedProducts")
    List<Product> findBySelectedIsTrue();


    @RestResource(path = "/productsByKeyword")
    List<Product> findByNameContains(@Param("mc") String mc);
}
