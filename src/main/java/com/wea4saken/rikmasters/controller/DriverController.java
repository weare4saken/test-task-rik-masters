package com.wea4saken.rikmasters.controller;

import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.dto.DriverDto;
import com.wea4saken.rikmasters.service.impl.DriverService;
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
@RequestMapping("/driver")
@RequiredArgsConstructor
@Tag(name = "Водители")
public class DriverController {

    private final DriverService driverService;

    @Operation(summary = "Добавление нового водителя", tags = "Водители",
                responses = {@ApiResponse(responseCode = "201", description = "CREATED",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = DriverDto.class))})})
    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverDto driverDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.add(driverDto));
    }

    @Operation(summary = "Редактирование сведений о водителе", tags = "Водители",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = DriverDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @PatchMapping("/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable("id") Long id,
                                            @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok(driverService.update(id, driverDto));
    }

    @Operation(summary = "Получить всех водителей", tags = "Водители")
    @GetMapping
    public ResponseEntity<List<DriverDto>> getAllDrivers(@RequestParam Integer pageNumber,
                                                         @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null || pageSize > 10) {
            return ResponseEntity.ok(driverService.getAll(pageNumber, 10));
        }
        return ResponseEntity.ok(driverService.getAll(pageNumber, pageSize));
    }

    @Operation(summary = "Получение водителя по ID", tags = "Водители",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = CarDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable("id") Long id) {
        return ResponseEntity.ok(driverService.get(id));
    }

    @Operation(summary = "Удалить водителя", tags = "Водители",
                responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetail(@PathVariable("id") Long id) {
        driverService.delete(id);
        return ResponseEntity.ok().build();
    }
}
