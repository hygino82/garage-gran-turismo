package br.dev.hygino.garage.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.dev.hygino.garage.dtos.RequestCarDto;
import br.dev.hygino.garage.dtos.ResponseCarDto;

public interface CarService {
    ResponseCarDto createCar(RequestCarDto dto);

    ResponseCarDto getCarById(long id);

    Page<ResponseCarDto> getCars(Pageable pageable, String name);

    ResponseCarDto updateCar(long id, RequestCarDto dto);

    void deleteCar(long id);
}
