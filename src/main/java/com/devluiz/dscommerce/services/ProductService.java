package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.entities.Product;
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

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product product = new Product();
        copyDtoToEntity(dto,product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            Product product = productRepository.getReferenceById(id);
            copyDtoToEntity(dto,product);
            product = productRepository.save(product);
            return new ProductDTO(product);
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

    private void copyDtoToEntity(ProductDTO dto, Product product){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}
