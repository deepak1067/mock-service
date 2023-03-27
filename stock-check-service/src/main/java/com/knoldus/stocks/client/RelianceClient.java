package com.knoldus.stocks.client;

import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.util.CommonUtil;
import com.knoldus.stocks.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RelianceClient {

    private final RestTemplate restTemplate;

    public RelianceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int relianceStockCount() {
        log.info("Reliance Client Called!");
        ResponseEntity<StockCheckResponse> relianceStockCountResponse;
        try {
            String restURL = Constants.RELIANCE_STOCK_URL;
            relianceStockCountResponse = restTemplate.getForEntity(restURL, StockCheckResponse.class);
            return relianceStockCountResponse.getStatusCodeValue();
        } catch (Exception ex) {
            log.error("Error calling Reliance Client: " + ex.getMessage());
            return CommonUtil.getIntStatus(ex.getMessage());
        }
    }
}
