package br.dev.hygino.garage.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.dev.hygino.garage.dtos.RequestBrandDto;
import br.dev.hygino.garage.dtos.ResponseBrandDto;

public interface BrandService {
    ResponseBrandDto createBrand(RequestBrandDto dto);

    ResponseBrandDto getBrandById(long id);

    Page<ResponseBrandDto> getBrands(Pageable pageable, String name);

    ResponseBrandDto updateBrand(long id, RequestBrandDto dto);

    void deleteBrand(long id);
}
