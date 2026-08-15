package com.tochratana.learnSpring.feature.category;

import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Page<CategoryResponse> getAllCategories(int pageNumber, int pageSize);

    CategoryResponse getCategoryById(Integer id);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest);

    CategoryResponse patchCategoryById(Integer id, PatchCategoryRequest patchCategoryRequest);

    Integer deleteCategoryById(Integer id);
}
