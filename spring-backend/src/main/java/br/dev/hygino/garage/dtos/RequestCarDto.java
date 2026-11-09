package br.dev.hygino.garage.dtos;

import br.dev.hygino.garage.enums.Drivetrain;

public record RequestCarDto(
        String name,
        long brandId,
        String color,
        Drivetrain drivetrain,
        Integer weight,
        Integer power,
        Integer rpm

) {

}
