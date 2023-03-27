package com.knoldus.stocks.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCheckResponse {

    private int statusCode;
    private String message;

}
