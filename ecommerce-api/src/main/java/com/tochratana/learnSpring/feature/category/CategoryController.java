package com.tochratana.learnSpring.feature.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable @Positive Integer id) {
        log.info("Get category id: {}", id);
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        log.info("Create category: {}", categoryRequest);
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest
    ) {
        log.info("Update category id {}: {}", id, updateCategoryRequest);
        return categoryService.updateCategoryById(id, updateCategoryRequest);
    }

    @PatchMapping("/{id}")
    public CategoryResponse patchCategory(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody PatchCategoryRequest patchCategoryRequest
    ) {
        log.info("Patch category id {}: {}", id, patchCategoryRequest);
        return categoryService.patchCategoryById(id, patchCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public Integer deleteCategory(@PathVariable @Positive Integer id) {
        log.info("Delete category id: {}", id);
        return categoryService.deleteCategoryById(id);
    }
}
