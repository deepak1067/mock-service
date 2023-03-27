package com.knoldus.stocks.client;

import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.util.CommonUtil;
import com.knoldus.stocks.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class KnoldusClient {

    private final RestTemplate restTemplate;

    public KnoldusClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int knoldusStockCount() {
        log.info("Knoldus Client Called!");
        ResponseEntity<StockCheckResponse> knoldusStockCountResponse;
        try {
            String restURL = Constants.KNOLDUS_STOCK_URL;
            knoldusStockCountResponse = restTemplate.getForEntity(restURL, StockCheckResponse.class);
            return knoldusStockCountResponse.getStatusCodeValue();
        } catch (Exception ex) {
            log.error("Error calling Knoldus Client: " + ex.getMessage());
            return CommonUtil.getIntStatus(ex.getMessage());
        }
    }
}
