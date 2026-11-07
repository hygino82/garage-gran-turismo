package br.dev.hygino.garage.dtos;

import java.time.LocalDateTime;

import br.dev.hygino.garage.entities.Car;
import br.dev.hygino.garage.enums.Drivetrain;

public record ResponseCarDto(
                Long id,
                String name,
                ResponseMinBrandDto brand,
                String color,
                Drivetrain drivetrain,
                Integer weight,
                Integer power,
                Integer rpm,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        public ResponseCarDto(
                        Car entity) {
                this(
                                entity.getId(),
                                entity.getName(),
                                new ResponseMinBrandDto(entity.getBrand().getId(), entity.getBrand().getName()),
                                entity.getColor(),
                                entity.getDrivetrain(),
                                entity.getWeight(),
                                entity.getPower(),
                                entity.getRpm(),
                                entity.getCreatedAt(),
                                entity.getUpdatedAt());
        }

}
