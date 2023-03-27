package com.knoldus.stocks.client;

import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.util.CommonUtil;
import com.knoldus.stocks.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class TataClient {

    private final RestTemplate restTemplate;

    public TataClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int tataStockCount() {
        log.info("Tata Client Called!");
        ResponseEntity<StockCheckResponse> tataStockCountResponse;
        try {
            String restURL = Constants.TATA_STOCK_URL;
            tataStockCountResponse = restTemplate.getForEntity(restURL, StockCheckResponse.class);
            return tataStockCountResponse.getStatusCodeValue();
        } catch (Exception ex) {
            log.error("Error calling Tata Client: " + ex.getMessage());
            return CommonUtil.getIntStatus(ex.getMessage());
        }
    }
}
