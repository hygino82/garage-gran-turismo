package br.dev.hygino.garage.dtos;

import br.dev.hygino.garage.enums.Continent;
import br.dev.hygino.garage.enums.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestBrandDto(
        @NotBlank(message = "The name cannot be null or empty") 
        @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters") 
        String name,

        @NotNull(message = "The country cannot be null")
        Country country,

        @NotNull(message = "The continent cannot be null") Continent continent) {

}
