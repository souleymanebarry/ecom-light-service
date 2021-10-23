package com.barry.web;

import com.barry.dao.ProductRepository;
import com.barry.entities.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {

    private final ProductRepository productRepository;

    @Value("${dir.images}")
    private String imagesDirectory;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "pictureProducts/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getPicture(@PathVariable(name = "id") Long id) throws IOException {
        Product p = productRepository.findById(id).get();
       return Files.readAllBytes(Paths.get(imagesDirectory+p.getPictureName()));
    }
}
