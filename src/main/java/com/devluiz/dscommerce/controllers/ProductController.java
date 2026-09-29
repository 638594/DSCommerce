package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.dto.ProductMinDTO;
import com.devluiz.dscommerce.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/products")
@Tag(name = "Product", description = "Endpoints para gerenciamento do catálogo de produtos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Busca um produto por ID", description = "Retorna um produto específico baseado no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO2> findById(@PathVariable("id") Long id){
        ProductDTO2 dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista paginada de produtos. Pode ser filtrada pelo nome.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name,@ParameterObject Pageable pageable){
        Page<ProductMinDTO> dto = productService.findAll(name,pageable);
        return ResponseEntity.ok(dto);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastra um novo produto", description = "Cria um novo produto no catálogo. Requer privilégios de Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (Usuário não é Admin)")
    })
    @PostMapping
    public ResponseEntity<ProductDTO2> insert(@Valid @RequestBody ProductDTO2 dto){
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualiza um produto", description = "Atualiza os dados de um produto existente baseado no seu ID. Requer privilégios de Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (Usuário não é Admin)"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO2> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO2 dto){
        dto = productService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deleta um produto", description = "Remove um produto do catálogo baseado no seu ID. Requer privilégios de Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Violação de integridade (Ex: O produto já possui pedidos atrelados a ele)"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (Usuário não é Admin)"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
