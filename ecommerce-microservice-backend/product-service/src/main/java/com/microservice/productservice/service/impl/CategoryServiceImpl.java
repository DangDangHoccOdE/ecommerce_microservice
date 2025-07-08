package com.microservice.productservice.service.impl;

import com.microservice.productservice.dto.CategoryDto;
import com.microservice.productservice.exception.wrapper.CategoryNotFoundException;
import com.microservice.productservice.helper.CategoryMappingHelper;
import com.microservice.productservice.repository.CategoryRepository;
import com.microservice.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        log.info("*** CategoryDto List, service; fetch all category *");
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CategoryDto findById(Integer categoryId) {
        log.info("*** CategoryDto, service; fetch category by id *");
        return this.categoryRepository.findById(categoryId)
                .map(CategoryMappingHelper::map)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; save category *");
        return CategoryMappingHelper.map(this.categoryRepository.save(CategoryMappingHelper.map(categoryDto)));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; update category *");
        return CategoryMappingHelper.map(this.categoryRepository.save(CategoryMappingHelper.map(categoryDto)));
    }

    @Override
    public CategoryDto update(Integer categoryId, CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; update category with categoryId *");
        return CategoryMappingHelper.map(this.categoryRepository.save(CategoryMappingHelper.map(this.findById(categoryId))));
    }

    @Override
    public void deleteById(Integer categoryId) {
        log.info("*** Void, service; delete category by id *");
        this.categoryRepository.deleteById(categoryId);
    }
}
