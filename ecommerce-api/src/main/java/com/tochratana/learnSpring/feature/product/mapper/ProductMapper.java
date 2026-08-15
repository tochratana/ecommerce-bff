package com.tochratana.learnSpring.feature.product.mapper;

import com.tochratana.learnSpring.feature.product.Product;
import com.tochratana.learnSpring.feature.product.dto.PatchProductRequest;
import com.tochratana.learnSpring.feature.product.dto.ProductResponse;
import com.tochratana.learnSpring.feature.product.dto.UpdateProductRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    public static ProductResponse toProductResponse(Product product){
//        return ProductResponse.builder()
//                .code(product.getCode())
//                .name(product.getName())
//                .price(product.getPrice())
//                .qty(product.getQty())
//                .description(product.getDescription())
//                .isAvailable(product.getIsAvailable())
//                .categoryName(product.getCategory().getName())
//                .build();
//    }
//
//
//    public static void toUpdateProductRequest(UpdateProductRequest updateProductRequest, Product product){
//
//        product.setName(updateProductRequest.name());
//        product.setDescription(updateProductRequest.description());
//        product.setQty(updateProductRequest.qty());
//        product.setPrice(updateProductRequest.price());
//
//    }


    @Mapping(source = "category.name", target = "category")
    ProductResponse toProductResponse(Product product);
    void toUpdateProductRequest(UpdateProductRequest updateProductRequest, @MappingTarget Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toPatchProductRequest(PatchProductRequest patchProductRequest, @MappingTarget Product product);

}
