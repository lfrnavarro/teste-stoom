package br.com.stoom.store.dto.response;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDTOResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("categories")
    private List<Category> categories;

    @JsonProperty("brand")
    private Brand brand;

    @JsonProperty("price")
    private BigDecimal price;

}
