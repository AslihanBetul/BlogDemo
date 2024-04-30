package com.abm.bloggerdemo.repository;

import com.abm.bloggerdemo.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
   Optional<Categories>  findById(Long id);
   List<Categories> findAllCategoriesByNameContainingIgnoreCase(String containing);
}
