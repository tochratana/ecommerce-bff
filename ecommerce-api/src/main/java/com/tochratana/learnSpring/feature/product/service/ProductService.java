package com.tochratana.learnSpring.feature.product.service;

import com.tochratana.learnSpring.feature.product.dto.PatchProductRequest;
import com.tochratana.learnSpring.feature.product.dto.ProductResponse;
import com.tochratana.learnSpring.feature.product.dto.RequestProduct;
import com.tochratana.learnSpring.feature.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;


public interface ProductService {
    ProductResponse createNew(RequestProduct requestProduct);
    Page<ProductResponse> getAllProduct(int pageNumber, int pageSize);
    String deleteProductByCode(String code);
    ProductResponse getProductByCode(String code);
    ProductResponse updateProductByCode(String code, UpdateProductRequest updateProductRequest);
    ProductResponse patchByCode(String code, PatchProductRequest patchProductRequest);
}
