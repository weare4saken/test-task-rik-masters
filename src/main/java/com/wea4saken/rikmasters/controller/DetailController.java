package com.wea4saken.rikmasters.controller;

import com.wea4saken.rikmasters.dto.DetailDto;
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
@RequestMapping("/details")
@RequiredArgsConstructor
@Tag(name = "Детали")
public class DetailController {

    private final DetailService detailService;

    @Operation(summary = "Добавление новой детали", tags = "Детали",
                responses = {@ApiResponse(responseCode = "201", description = "CREATED",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = DetailDto.class))})})
    @PostMapping
    public ResponseEntity<DetailDto> addDetail(@RequestBody DetailDto detailDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detailService.add(detailDto));
    }

    @Operation(summary = "Редактирование сведений детали", tags = "Детали",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = DetailDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @PatchMapping("/{serialNumber}")
    public ResponseEntity<DetailDto> updateDetail(@PathVariable("serialNumber") String serialNumber,
                                                  @RequestBody DetailDto detailDto) {
        return ResponseEntity.ok(detailService.update(serialNumber, detailDto));
    }

    @Operation(summary = "Получить все детали", tags = "Детали")
    @GetMapping
    public ResponseEntity<List<DetailDto>> getAllDetails() {
        return ResponseEntity.ok(detailService.getAll());
    }

    @Operation(summary = "Получение детали по серийному номеру", tags = "Детали",
                responses = {@ApiResponse(responseCode = "200", description = "OK",
                                            content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = DetailDto.class))}),
                            @ApiResponse(responseCode = "404", description = "Not Found",
                                            content = @Content)})
    @GetMapping("/{serialNumber}")
    public ResponseEntity<DetailDto> getDetail(@PathVariable("serialNumber") String serialNumber) {
        return ResponseEntity.ok(detailService.get(serialNumber));
    }

    @Operation(summary = "Удалить деталь", tags = "Детали",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<?> deleteDetail(@PathVariable("serialNumber") String serialNumber) {
        detailService.delete(serialNumber);
        return ResponseEntity.ok().build();
    }
}