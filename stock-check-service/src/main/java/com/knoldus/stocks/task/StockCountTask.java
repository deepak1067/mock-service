package com.knoldus.stocks.task;

import com.knoldus.stocks.service.StockService;
import com.knoldus.stocks.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockCountTask extends StockCheckTask {

    public StockCountTask(StockDetails stockDetails, StockService stockService) {
        super(stockDetails, stockService);
    }

    @Override
    public String call() throws Exception {
        String finalStatus = Constants.ERROR;
        log.info("Setting stock to Blocked: " + stockDetails.getStockId());
        int adityaResponseStatus = 0;
        log.info("Aditya Stock count for stockId: " + stockDetails.getStockId());
        adityaResponseStatus = stockService.adityaStockCount().getStatusCode();
        if (adityaResponseStatus == 200) {
            Thread.sleep(1000);
            int knoldusResponseStatus = 0;
            log.info("Knoldus Stock count for stockId: " + stockDetails.getStockId());
            knoldusResponseStatus = stockService.knoldusStockCount().getStatusCode();

            if (knoldusResponseStatus == 200) {
                Thread.sleep(2000);
                int relianceResponseStatus = 0;
                log.info("Reliance Stock count for stockId: " + stockDetails.getStockId());
                relianceResponseStatus = stockService.relianceStockCount().getStatusCode();

                if (relianceResponseStatus == 200) {
                    Thread.sleep(3000);
                    int tataResponseStatus = 0;
                    log.info("Tata Stock count for stockId: " + stockDetails.getStockId());
                    tataResponseStatus = stockService.tataStockCount().getStatusCode();

                    if (tataResponseStatus == 200) {
                        Thread.sleep(4000);
                        finalStatus = Constants.COUNT_STATUS.SUCCESS.toString();
                    } else {
                        log.info("Tata Stock count failed for stockId: " + stockDetails.getStockId());
                        finalStatus = Constants.TATA_STOCK_COUNT_FAILED;
                    }
                } else {
                    log.info("Reliance Stock count failed for stockId: " + stockDetails.getStockId());
                    finalStatus = Constants.RELIANCE_STOCK_COUNT_FAILED;
                }
            } else {
                log.info("Knoldus Stock count failed for stockId: " + stockDetails.getStockId());
                finalStatus = Constants.KNOLDUS_STOCK_COUNT_FAILED;
            }
        } else {
            log.info("Aditya Stock count failed for stockId: " + stockDetails.getStockId());
            finalStatus = Constants.ADITYA_STOCK_COUNT_FAILED;
        }

        log.info(String.format("Final Stock status for stockId: %s is %s", stockDetails.getStockId(),
                finalStatus));
        Thread.sleep(1000);
        return finalStatus;
    }
}
