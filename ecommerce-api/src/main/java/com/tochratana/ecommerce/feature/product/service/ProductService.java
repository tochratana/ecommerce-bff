package com.tochratana.ecommerce.feature.product.service;

import com.tochratana.ecommerce.feature.product.dto.PatchProductRequest;
import com.tochratana.ecommerce.feature.product.dto.ProductResponse;
import com.tochratana.ecommerce.feature.product.dto.RequestProduct;
import com.tochratana.ecommerce.feature.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;


public interface ProductService {
    ProductResponse createNew(RequestProduct requestProduct);
    Page<ProductResponse> getAllProduct(int pageNumber, int pageSize);
    String deleteProductByCode(String code);
    ProductResponse getProductByCode(String code);
    ProductResponse updateProductByCode(String code, UpdateProductRequest updateProductRequest);
    ProductResponse patchByCode(String code, PatchProductRequest patchProductRequest);
}
