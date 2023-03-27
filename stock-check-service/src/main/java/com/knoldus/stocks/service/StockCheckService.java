package com.knoldus.stocks.service;

import com.knoldus.stocks.request.StockCheckRequest;
import com.knoldus.stocks.response.StockCheckResponse;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

public interface StockCheckService {

    public StockCheckResponse executeStockCheckByStockId(StockCheckRequest stockCheckRequest) throws InterruptedException, ExecutionException;
}
