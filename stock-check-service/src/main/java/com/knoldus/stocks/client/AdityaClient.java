package com.knoldus.stocks.client;

import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.util.CommonUtil;
import com.knoldus.stocks.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AdityaClient {

    private final RestTemplate restTemplate;

    public AdityaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int adityaStockCount() {
        log.info("Aditya Client Called!");
        ResponseEntity<StockCheckResponse> adityaStockCountResponse;
        try {
            String restURL = Constants.ADITYA_STOCK_URL;
            adityaStockCountResponse = restTemplate.getForEntity(restURL, StockCheckResponse.class);
            return adityaStockCountResponse.getStatusCodeValue();
        } catch (Exception ex) {
            log.error("Error calling Aditya Client: " + ex.getMessage());
            return CommonUtil.getIntStatus(ex.getMessage());
        }
    }
}
