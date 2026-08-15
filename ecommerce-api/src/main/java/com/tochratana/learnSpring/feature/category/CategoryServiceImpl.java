package com.tochratana.learnSpring.feature.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        log.info("Create new category: {}", categoryRequest);

        Category category = categoryMapper.fromCategoryRequest(categoryRequest);
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getAllCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));

        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toCategoryResponse);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = findCategoryById(id);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = findCategoryById(id);

        categoryMapper.toUpdateCategoryRequest(updateCategoryRequest, category);
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse patchCategoryById(Integer id, PatchCategoryRequest patchCategoryRequest) {
        Category category = findCategoryById(id);

        categoryMapper.toPatchCategoryRequest(patchCategoryRequest, category);
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public Integer deleteCategoryById(Integer id) {
        Category category = findCategoryById(id);

        try {
            categoryRepository.delete(category);
            return id;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category is used by products");
        }
    }

    private Category findCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }
}
