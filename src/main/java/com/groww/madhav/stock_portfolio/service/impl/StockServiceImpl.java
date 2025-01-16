package com.groww.madhav.stock_portfolio.service.impl;

import com.groww.madhav.stock_portfolio.entity.Stock;
import com.groww.madhav.stock_portfolio.repository.StockRepository;
import com.groww.madhav.stock_portfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Override
    public Stock getStockById(String stockId) {
        Stock stock = stockRepository.findById(stockId).orElse(null);
        if(stock == null){
            throw new RuntimeException("Stock not found with id : " + stockId);
        }
        return stock;
    }

    @Override
    public void processStockData(MultipartFile file) throws Exception {
        List<Stock> stocks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            // Read each line from the file
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip the first line (header)
                    continue;  // Continue to the next line
                }

                // Process the data
                if (line != null && !line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    if (data.length < 6) {
                        throw new RuntimeException("Invalid stock data: " + line);
                    }

                    Stock stock = new Stock();
                    stock.setStockId(data[0].trim());
                    stock.setStockName(data[1].trim());
                    stock.setOpenPrice(Double.parseDouble(data[2].trim()));
                    stock.setClosePrice(Double.parseDouble(data[3].trim()));
                    stock.setHighPrice(Double.parseDouble(data[4].trim()));
                    stock.setLowPrice(Double.parseDouble(data[5].trim()));

                    stocks.add(stock);
                }
            }
        }
        stockRepository.saveAll(stocks);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}
