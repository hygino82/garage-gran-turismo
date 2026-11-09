package br.dev.hygino.garage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.garage.dtos.RequestCarDto;
import br.dev.hygino.garage.dtos.ResponseCarDto;
import br.dev.hygino.garage.services.CarService;
import jakarta.validation.Valid;

@RequestMapping("api/v1/car")
@RestController
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseCarDto> createCar(@RequestBody @Valid RequestCarDto dto) {
        final var response = service.createCar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
