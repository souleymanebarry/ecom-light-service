package com.barry;

import com.barry.dao.CategoryRepository;
import com.barry.dao.ProductRepository;
import com.barry.entities.Category;
import com.barry.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcomLightApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomLightApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository,
                            RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class,Category.class);
        return args -> {
            categoryRepository.save(new Category(null,"Computer", null,null,null));
            categoryRepository.save(new Category(null,"Printers", null,null,null));
            categoryRepository.save(new Category(null,"Smart Phone", null,null,null));

            Random rnd= new Random();
            for(int i=0; i<10; i++){
                categoryRepository.findAll().forEach(c->{
                    Product p= new Product();
                    p.setName(RandomString.make(18));
                    p.setCurrentPrice(100+rnd.nextInt(1000));
                    p.setAvailable(rnd.nextBoolean());
                    p.setPromotion(rnd.nextBoolean());
                    p.setSelected(rnd.nextBoolean());
                    p.setCategory(c);
                    p.setPictureName("unknown.png");
                    productRepository.save(p);
                });

            }

        };
    }

}
