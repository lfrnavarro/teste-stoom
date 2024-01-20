package br.com.stoom.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDTORequest {

    @NotNull(message = "Attribute 'description' must be provided")
    @NotEmpty(message = "Attribute 'description' must be provided")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Attribute 'categoryIds' must be provided")
    @NotEmpty(message = "Attribute 'categoryIds' must be provided")
    @JsonProperty("categoryIds")
    private List<Long> categoryIds;

    @NotNull(message = "Attribute 'brandId' must be provided")
    @JsonProperty("brandId")
    private Long brandId;

    @NotNull(message = "Attribute 'price' must be provided")
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull(message = "Attribute 'active' must be provided")
    @JsonProperty("active")
    private Boolean active;

}
