package br.dev.hygino.garage.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.dev.hygino.garage.enums.Continent;
import br.dev.hygino.garage.enums.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Country country;
    private Continent continent;
    
    private final List<Car> carlist = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
