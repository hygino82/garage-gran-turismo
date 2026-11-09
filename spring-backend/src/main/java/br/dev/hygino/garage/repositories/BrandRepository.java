package br.dev.hygino.garage.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.dev.hygino.garage.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("""
        SELECT obj
        FROM Brand obj
        WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    Page<Brand> findByName(String name, Pageable pageable);
    
}
