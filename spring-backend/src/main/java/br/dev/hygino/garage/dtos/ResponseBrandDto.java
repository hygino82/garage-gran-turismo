package br.dev.hygino.garage.dtos;

import java.time.LocalDateTime;

import br.dev.hygino.garage.enums.Continent;
import br.dev.hygino.garage.enums.Country;

public record ResponseBrandDto(
        Long id,
        String name,
        Country country,
        Continent continent,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
