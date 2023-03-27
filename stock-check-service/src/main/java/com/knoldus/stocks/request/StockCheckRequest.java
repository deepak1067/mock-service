package com.knoldus.stocks.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCheckRequest {

    @NotEmpty(message = "stockIds List is a mandatory field and cannot be null")
    private List<String> stockIds;
}
