package com.knoldus.stocks.controller;

import com.knoldus.stocks.request.StockCheckRequest;
import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.service.StockCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/execute")
public class StockCheckServiceController {

    StockCheckService stockCheckService;

    public StockCheckServiceController(StockCheckService stockCheckService) {
        super();
        this.stockCheckService = stockCheckService;
    }
    @PostMapping("/stockCount")
    public ResponseEntity<String> executeStockCheckById(@RequestBody @Valid StockCheckRequest stockCheckRequest) throws ExecutionException, InterruptedException {
        stockCheckService.executeStockCheckByStockId(stockCheckRequest);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
