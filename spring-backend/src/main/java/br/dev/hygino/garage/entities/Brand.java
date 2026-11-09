package br.dev.hygino.garage.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.dev.hygino.garage.dtos.ResponseBrandDto;
import br.dev.hygino.garage.enums.Continent;
import br.dev.hygino.garage.enums.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "The name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The country cannot be null")
    private Country country;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The continent cannot be null")
    private Continent continent;
    
    @OneToMany(mappedBy = "brand")
    private final List<Car> carlist = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseBrandDto toResponse() {
        return new ResponseBrandDto(id, name, country, continent, createdAt, updatedAt);
    }
}
