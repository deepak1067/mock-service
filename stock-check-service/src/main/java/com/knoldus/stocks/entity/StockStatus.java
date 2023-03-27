package com.knoldus.stocks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockStatus {

    private String stockId;
    private String adityaStockStatus;
    private String knoldusStockStatus;
    private String relianceStockStatus;
    private String tataStockStatus;
}
