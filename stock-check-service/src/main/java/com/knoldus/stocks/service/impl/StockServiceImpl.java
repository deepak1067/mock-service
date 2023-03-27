package com.knoldus.stocks.service.impl;

import com.knoldus.stocks.client.AdityaClient;
import com.knoldus.stocks.client.KnoldusClient;
import com.knoldus.stocks.client.RelianceClient;
import com.knoldus.stocks.client.TataClient;
import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.service.StockService;
import com.knoldus.stocks.util.Constants;
import org.springframework.web.client.RestTemplate;

public class StockServiceImpl implements StockService {

    AdityaClient adityaClient;

    KnoldusClient knoldusClient;

    RelianceClient relianceClient;

    TataClient tataClient;

    public StockServiceImpl(RestTemplate restTemplate) {
        this.adityaClient = new AdityaClient(restTemplate);
        this.knoldusClient = new KnoldusClient(restTemplate);
        this.relianceClient = new RelianceClient(restTemplate);
        this.tataClient = new TataClient(restTemplate);
    }

    @Override
    public StockCheckResponse adityaStockCount() {
        int status = adityaClient.adityaStockCount();
        return getResponse(status);
    }

    @Override
    public StockCheckResponse knoldusStockCount() {
        int status = knoldusClient.knoldusStockCount();
        return getResponse(status);
    }

    @Override
    public StockCheckResponse relianceStockCount() {
        int status = relianceClient.relianceStockCount();
        return getResponse(status);
    }

    @Override
    public StockCheckResponse tataStockCount() {
        int status = tataClient.tataStockCount();
        return getResponse(status);
    }

    private StockCheckResponse getResponse(int status) {
        if (status >= 200 && status < 300) {
            return new StockCheckResponse(status, Constants.COUNT_STATUS.SUCCESS.toString());
        } else if (status >= 400 && status < 500) {
            return new StockCheckResponse(status, Constants.COUNT_STATUS.FAILED.toString());
        } else {
            return new StockCheckResponse(status, Constants.ERROR);
        }
    }
}
