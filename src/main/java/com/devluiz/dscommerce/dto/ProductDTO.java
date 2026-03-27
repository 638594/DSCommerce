package com.devluiz.dscommerce.dto;


import com.devluiz.dscommerce.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonPropertyOrder({"id", "name","description","price","imgUrl"})
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
    private String name;
    @NotBlank(message = "Campo requerido")
    @Size(min = 10, message = "Descricao precisa ter no minimo 10 caracteres")
    private String description;
    @Positive(message = "Valor do preço deve ser positivo")
    private Double price;
    private String imgUrl;

    public ProductDTO() {
    }

    public ProductDTO(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
