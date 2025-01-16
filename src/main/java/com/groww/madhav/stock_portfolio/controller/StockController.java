package com.groww.madhav.stock_portfolio.controller;

import com.groww.madhav.stock_portfolio.entity.Stock;
import com.groww.madhav.stock_portfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStock(@PathVariable("stockId") String stockId) {
        Stock stock = stockService.getStockById(stockId);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadStockData(@RequestParam("file") MultipartFile file) {
        try{
            stockService.processStockData(file);
            return ResponseEntity.ok("Successfully uploaded file");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Uploading CSV file!");
        }
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

}
