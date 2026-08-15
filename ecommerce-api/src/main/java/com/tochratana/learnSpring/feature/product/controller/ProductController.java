package com.tochratana.learnSpring.feature.product.controller;

import com.tochratana.learnSpring.feature.product.dto.PatchProductRequest;
import com.tochratana.learnSpring.feature.product.dto.ProductResponse;
import com.tochratana.learnSpring.feature.product.dto.RequestProduct;
import com.tochratana.learnSpring.feature.product.dto.UpdateProductRequest;
import com.tochratana.learnSpring.feature.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ){

        return productService.getAllProduct(pageNumber, pageSize);
    }

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(
            @PathVariable String code
    ){

        log.info("Get this product code : {}", code);
        return  productService.getProductByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createNewProduct(
            @Valid @RequestBody RequestProduct requestProduct
    ){
        log.info("Product that have create : {}", requestProduct);
        return productService.createNew(requestProduct);
    }


    // must be input all
    @PutMapping("/{code}")
    public ProductResponse updateProduct(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProduct
    ){
        log.info("Update product : {}", updateProduct);
        return productService.updateProductByCode(code, updateProduct);
    }

    @PatchMapping("/{code}")
    public ProductResponse patchProduct(
            @PathVariable String code,
            @Valid @RequestBody PatchProductRequest patchProductRequest
    ){

        return productService.patchByCode(code, patchProductRequest);
    }

    @DeleteMapping("{code}")
    public String deleteProductByCode(@PathVariable String code){
        return productService.deleteProductByCode(code);
    }
}
