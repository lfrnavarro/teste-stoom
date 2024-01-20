package br.com.stoom.store.mappers;
import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTOResponse mapToProductDTOResponse(Product product);

    List<ProductDTOResponse> mapListToProductDTOResponseList(List<Product> productList);

    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idToCategory")
    Product mapToProduct(ProductDTORequest productDTORequest);

    @Named("idToCategory")
    default List<Category> idToCategory(List<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toList());
    }

}