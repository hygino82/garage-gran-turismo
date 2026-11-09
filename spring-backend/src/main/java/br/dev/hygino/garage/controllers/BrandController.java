package br.dev.hygino.garage.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.garage.dtos.RequestBrandDto;
import br.dev.hygino.garage.dtos.ResponseBrandDto;
import br.dev.hygino.garage.services.BrandService;
import jakarta.validation.Valid;

@RequestMapping("api/v1/brand")
@RestController
public class BrandController {
    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseBrandDto> createBrand(@RequestBody @Valid RequestBrandDto dto) {
        final var response = service.createBrand(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBrandDto> updateBrand(@PathVariable long id, @RequestBody @Valid RequestBrandDto dto) {
        final var response = service.updateBrand(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBrandDto> getBrandById(@PathVariable long id) {
        final var response = service.getBrandById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseBrandDto>> getBrands(Pageable pageable,
            @RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(service.getBrands(pageable, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable long id) {
        service.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
