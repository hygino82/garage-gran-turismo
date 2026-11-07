package br.dev.hygino.garage.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.dev.hygino.garage.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("""
        SELECT obj
        FROM Car obj
        WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    Page<Car> findByCars(String name, Pageable pageable);
    
}
