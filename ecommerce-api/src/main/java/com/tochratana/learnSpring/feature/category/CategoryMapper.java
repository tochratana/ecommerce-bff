package com.tochratana.learnSpring.feature.category;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryRequest(CategoryRequest categoryRequest);
    CategoryResponse toCategoryResponse(Category category);

    void toUpdateCategoryRequest(UpdateCategoryRequest updateCategoryRequest, @MappingTarget Category category);
    void toPatchCategoryRequest(PatchCategoryRequest patchCategoryRequest, @MappingTarget Category category);
}
