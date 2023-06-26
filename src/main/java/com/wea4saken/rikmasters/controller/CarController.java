package com.wea4saken.rikmasters.controller;

import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.service.impl.CarService;
import com.wea4saken.rikmasters.service.impl.DetailService;
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
    private final DetailService detailService;

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
    public ResponseEntity<List<CarDto>> getAllCars(@RequestParam Integer pageNumber,
                                                   @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null || pageSize > 10) {
            return ResponseEntity.ok(carService.getAll(pageNumber, 10));
        }
        return ResponseEntity.ok(carService.getAll(pageNumber, pageSize));
    }

    @Operation(summary = "Получение автомобиля по VIN-номеру", tags = "Автомобили",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content)})
    @GetMapping("/{vin}")
    public ResponseEntity<CarDto> getCar(@PathVariable("vin") String vin) {
        return ResponseEntity.ok(carService.get(vin));
    }

    @Operation(summary = "Удалить автомобиль", tags = "Автомобили",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{vin}")
    public ResponseEntity<?> deleteCar(@PathVariable("vin") String vin) {
        carService.delete(vin);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Установить владение автомобиля водителем", tags = "Автомобили",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PostMapping("/{vin}/add-driver/{id}")
    public ResponseEntity<?> addDriver(@PathVariable("vin") String vin,
                                       @PathVariable("id") Long id) {
        carService.addDriver(vin, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Установить деталь на автомобиль", tags = "Автомобили",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PostMapping("/{vin}/add-detail/{type}")
    public ResponseEntity<?> addDetail(@PathVariable("vin") String vin,
                                       @PathVariable("type") String type) {
        detailService.addDetail(vin, type);
        return ResponseEntity.ok().build();
    }

}