package com.microservice.productservice.resource;

import com.microservice.productservice.dto.CategoryDto;
import com.microservice.productservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<CategoryDto>> findAll(){
        log.info("*** CategoryDto List, controller; fetch all categories *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.categoryService.findAll()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findById(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String categoryId
    ){
        log.info("*** CategoryDto, resource; fetch category by id *");
        return ResponseEntity.ok(this.categoryService.findById(Integer.parseInt(categoryId)));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid final CategoryDto categoryDto
    ){
        log.info("*** CategoryDto, resource; save category *");
        return ResponseEntity.ok(this.categoryService.save(categoryDto));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CategoryDto categoryDto) {
        log.info("*** CategoryDto, resource; update category *");
        return ResponseEntity.ok(this.categoryService.update(categoryDto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String categoryId,
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CategoryDto categoryDto) {
        log.info("*** CategoryDto, resource; update category with categoryId *");
        return ResponseEntity.ok(this.categoryService.update(Integer.parseInt(categoryId), categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId) {
        log.info("*** Boolean, resource; delete category by id *");
        this.categoryService.deleteById(Integer.parseInt(categoryId));
        return ResponseEntity.ok(true);
    }

}
