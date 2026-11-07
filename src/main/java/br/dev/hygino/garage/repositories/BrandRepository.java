package br.dev.hygino.garage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.garage.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
