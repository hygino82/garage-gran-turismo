package br.dev.hygino.garage.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.garage.dtos.RequestBrandDto;
import br.dev.hygino.garage.dtos.ResponseBrandDto;
import br.dev.hygino.garage.entities.Brand;
import br.dev.hygino.garage.repositories.BrandRepository;
import br.dev.hygino.garage.services.exceptions.DatabaseException;
import br.dev.hygino.garage.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repository;

    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ResponseBrandDto createBrand(RequestBrandDto dto) {
        var brand = new Brand();
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        dtoToEntity(dto, brand);
        brand = repository.save(brand);

        return brand.toResponse();
    }

    private void dtoToEntity(RequestBrandDto dto, Brand brand) {
        brand.setName(dto.name());
        brand.setCountry(dto.country());
        brand.setContinent(dto.continent());
    }

    @Override
    public ResponseBrandDto getBrandById(long id) {
        return repository.findById(id)
                .map(Brand::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseBrandDto> getBrands(Pageable pageable, String name) {
        return repository.findByName(name, pageable).map(Brand::toResponse);
    }

    @Override
    @Transactional
    public ResponseBrandDto updateBrand(long id, RequestBrandDto dto) {
        try {
            var brand = repository.getReferenceById(id);
            brand.setUpdatedAt(LocalDateTime.now());
            dtoToEntity(dto, brand);
            brand = repository.save(brand);

            return brand.toResponse();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Brand not found!");
        }
    }

    @Override
    public void deleteBrand(long id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new DatabaseException("Brand not found!");
        }
    }
}
