package br.dev.hygino.garage.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.garage.dtos.RequestCarDto;
import br.dev.hygino.garage.dtos.ResponseCarDto;
import br.dev.hygino.garage.entities.Brand;
import br.dev.hygino.garage.entities.Car;
import br.dev.hygino.garage.repositories.BrandRepository;
import br.dev.hygino.garage.repositories.CarRepository;
import br.dev.hygino.garage.services.exceptions.ResourceNotFoundException;

@Service
public class CarServiceImpl implements CarService {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    public CarServiceImpl(BrandRepository brandRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public ResponseCarDto createCar(RequestCarDto dto) {
        final var brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        var car = new Car();
        dtoToEntity(dto, car, brand);
        car.setCreatedAt(LocalDateTime.now());
        car.setUpdatedAt(LocalDateTime.now());

        car = carRepository.save(car);
        return new ResponseCarDto(car);
    }

    private void dtoToEntity(RequestCarDto dto, Car car, Brand brand) {
        car.setName(dto.name());
        car.setBrand(brand);
        car.setColor(dto.color());
        car.setDrivetrain(dto.drivetrain());
        car.setWeight(dto.weight());
        car.setPower(dto.power());
        car.setRpm(dto.rpm());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCarDto getCarById(long id) {
        return carRepository.findById(id)
                .map(ResponseCarDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseCarDto> getCars(Pageable pageable, String name) {
        return carRepository.findByCars(name, pageable)
                .map(ResponseCarDto::new);
    }

    @Override
    @Transactional
    public ResponseCarDto updateCar(long id, RequestCarDto dto) {
        final var car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found!"));
        try {

            final var brand = brandRepository.getReferenceById(dto.brandId());
            dtoToEntity(dto, car, brand);
            car.setUpdatedAt(LocalDateTime.now());

            return new ResponseCarDto(carRepository.save(car));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
