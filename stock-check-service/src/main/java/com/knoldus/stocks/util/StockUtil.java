package com.knoldus.stocks.util;

import com.knoldus.stocks.entity.StockStatus;
import com.knoldus.stocks.request.StockCheckRequest;
import com.knoldus.stocks.service.StockService;
import com.knoldus.stocks.service.impl.StockServiceImpl;
import com.knoldus.stocks.task.StockCheckTask;
import com.knoldus.stocks.task.StockCountTask;
import com.knoldus.stocks.task.StockDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockUtil {

    static StockServiceImpl migrationService = new StockServiceImpl(null);

    public static List<StockCheckTask> getTaskList(StockCheckRequest stockCheckRequest, RestTemplate restTemplate) {
        List<StockCheckTask> stockCheckTasks = new ArrayList<>();
        for (String id : stockCheckRequest.getStockIds()) {
            StockCheckTask task = getTaskByType(id, stockCheckRequest, restTemplate);
            stockCheckTasks.add(task);
        }
        return stockCheckTasks;
    }

    private static StockCheckTask getTaskByType(String id, StockCheckRequest stockCheckRequest, RestTemplate restTemplate) {
        StockService stockService = new StockServiceImpl(restTemplate);
        StockDetails stockDetails = new StockDetails(id);
        return new StockCountTask(stockDetails, stockService);
    }

    public static StockStatus getStockStatusFromTask(StockDetails stockDetails) {
        StockStatus stockStatus = new StockStatus(stockDetails.getStockId(), null, null, null, null);

        log.info("Stock Status " + stockStatus);
        return stockStatus;
    }
}