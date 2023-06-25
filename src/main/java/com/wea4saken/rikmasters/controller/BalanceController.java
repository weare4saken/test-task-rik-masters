package com.wea4saken.rikmasters.controller;

import com.wea4saken.rikmasters.enums.Currency;
import com.wea4saken.rikmasters.model.Balance;
import com.wea4saken.rikmasters.service.impl.AboutDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class BalanceController {

    private final AboutDriverService aboutDriverService;

    @Operation(summary = "Внесение денег на счет", tags = "Водители",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Balance.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content)})
    @PostMapping("/{id}/deposit")
    public ResponseEntity<?> depositOnBalance(@PathVariable("id") Long id,
                                              @RequestBody Balance balance) {
        aboutDriverService.deposit(balance, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Снятие денег со счета", tags = "Водители",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Balance.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content)})
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withdrawFromBalance(@PathVariable("id") Long id,
                                                 @RequestBody Balance balance) {
        aboutDriverService.withdraw(balance, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Узнать баланс", tags = "Водители",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Balance.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content)})
    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable("id") Long id,
                                             @RequestParam Currency currency) {
        Double balance = aboutDriverService.getBalance(currency, id);
        return ResponseEntity.ok(balance);
    }

}