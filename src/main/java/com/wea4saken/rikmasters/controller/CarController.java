package com.wea4saken.rikmasters.controller;

import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.service.impl.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Tag(name = "Автомобили")
public class CarController {

    private final CarService carService;

    @Operation(summary = "Добавление нового автомобиля", tags = "Автомобили",
                responses = {@ApiResponse(responseCode = "201", description = "CREATED",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = CarDto.class))})})
    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.add(carDto));
    }

    @Operation(summary = "Редактирование сведений об автомобиле", tags = "Автомобили",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = CarDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @PatchMapping("/{vin}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("vin") String vin,
                                                  @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.update(vin, carDto));
    }

    @Operation(summary = "Получить все автомобили", tags = "Автомобили")
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAll());
    }

    @Operation(summary = "Получение автомобиля по VIN-номеру", tags = "Автомобили",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = CarDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @GetMapping("/{vin}")
    public ResponseEntity<CarDto> getDetail(@PathVariable("vin") String vin) {
        return ResponseEntity.ok(carService.get(vin));
    }

    @Operation(summary = "Удалить деталь", tags = "Детали",
                responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{vin}")
    public ResponseEntity<?> deleteDetail(@PathVariable("vin") String vin) {
        carService.delete(vin);
        return ResponseEntity.ok().build();
    }
}