package com.knoldus.stocks.task;

import com.knoldus.stocks.service.StockService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public abstract class StockCheckTask implements Callable<String> {

    StockService stockService;

    StockDetails stockDetails;

    public StockCheckTask(StockDetails stockDetails, StockService stockService) {
        this.stockDetails = stockDetails;
        this.stockService = stockService;
    }
}
