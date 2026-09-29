package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.dto.OrderDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.entities.Order;
import com.devluiz.dscommerce.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Order", description = "Endpoints para processamento e gerenciamento de pedidos")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Busca um pedido por ID", description = "Retorna os detalhes de um pedido. Clientes só têm permissão para acessar os seus próprios pedidos. Administradores podem acessar qualquer pedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido retornado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (Tentativa de acessar o pedido de outro cliente)"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id){
        OrderDTO dto = orderService.findById(id);
        return ResponseEntity.ok(dto);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registra um novo pedido", description = "Cria um novo pedido associado ao cliente autenticado e processa os itens do carrinho.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação (Ex: Produto inexistente ou falha na requisição)"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos ou mal formatados no corpo da requisição")
    })
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto){
        dto = orderService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


}
