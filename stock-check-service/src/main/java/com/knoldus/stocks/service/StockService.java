package com.knoldus.stocks.service;

import com.knoldus.stocks.response.StockCheckResponse;

public interface StockService {

    StockCheckResponse adityaStockCount();

    StockCheckResponse knoldusStockCount();

    StockCheckResponse relianceStockCount();

    StockCheckResponse tataStockCount();
}
