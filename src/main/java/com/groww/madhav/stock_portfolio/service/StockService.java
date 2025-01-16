package com.groww.madhav.stock_portfolio.service;

import com.groww.madhav.stock_portfolio.entity.Stock;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StockService {
    Stock getStockById(String stockId);
    void processStockData(MultipartFile file) throws Exception;
    List<Stock> getAllStocks();
}
