package com.kamkacz.currency_average.controller;

import com.kamkacz.currency_average.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping("currency/{cur}")
    public ResponseEntity<Double> getCurrency(@PathVariable String cur, @RequestParam(defaultValue = "1") int days) {
        return ResponseEntity.ok(currencyService.getCurrency(cur, days));
    }


}
