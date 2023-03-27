package com.knoldus.stocks.service.impl;

import com.knoldus.stocks.request.StockCheckRequest;
import com.knoldus.stocks.response.StockCheckResponse;
import com.knoldus.stocks.service.StockCheckService;
import com.knoldus.stocks.task.StockCheckTask;
import com.knoldus.stocks.util.StockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class StockCheckServiceImpl implements StockCheckService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;

    @Value("${threadPool.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${threadPool.keepAliveThread}")
    private long keepAliveThread;

    @Value("${threadPool.maxAwaitTermination}")
    private long maxAwaitTermination;

    private ThreadPoolExecutor setupThread() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveThread, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
    @Override
    public StockCheckResponse executeStockCheckByStockId(StockCheckRequest stockCheckRequest) {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        List<StockCheckTask> stockCheckTasks = StockUtil.getTaskList(stockCheckRequest, restTemplate);
        log.info(String.format("Processing execution for %d stocks : Stock Id %s ", stockCheckTasks.size(), stockCheckRequest.getStockIds()));
        try {
            stockCheckTasks.forEach(task -> executorService.submit(task::call));
            log.info("Executing Shutdown to wait till all task complete before shutdown.");
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            log.info("Exception while processing, can't proceed, executing shutdownNow.");
            executorService.shutdownNow();
//            Thread.currentThread().isAlive();
            Thread.currentThread().interrupt();

        }
//        try {
//            Thread.startVirtualThread(() -> stockCheckTasks.forEach(StockCheckTask::call))
//                    .onClose(() -> log.info("Executing Shutdown to wait till all task complete before shutdown."));
//        } catch (Exception e) {
//            log.info("Exception while processing, can't proceed, executing shutdownNow.");
//            Thread.currentThread().interrupt();
//        }
        return new StockCheckResponse(200, "Fetching all stock details successful");
    }
}