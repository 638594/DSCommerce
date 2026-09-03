package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.dto.CategoryDTO;
import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.dto.ProductMinDTO;
import com.devluiz.dscommerce.entities.Category;
import com.devluiz.dscommerce.entities.Product;
import com.devluiz.dscommerce.repositories.CategoryRepository;
import com.devluiz.dscommerce.repositories.ProductRepository;
import com.devluiz.dscommerce.services.exceptions.DatabaseException;
import com.devluiz.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public ProductDTO2 findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        return new ProductDTO2(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable){
        Page<Product> result = productRepository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    @Transactional
    public ProductDTO2 insert(ProductDTO2 dto){
        Product product = new Product();
        copyDtoToEntity(dto,product);
        product = productRepository.save(product);
        return new ProductDTO2(product);
    }

    @Transactional
    public ProductDTO2 update(Long id, ProductDTO2 dto){
        try{
            Product product = productRepository.getReferenceById(id);
            copyDtoToEntity(dto,product);
            product = productRepository.save(product);
            return new ProductDTO2(product);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO2 dto, Product entity){
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setImgUrl(dto.imgUrl());

        entity.getCategories().clear();
        for (CategoryDTO cat : dto.categories()){
            Category category = categoryRepository.getReferenceById(cat.id());
            entity.getCategories().add(category);
        }
    }
}
