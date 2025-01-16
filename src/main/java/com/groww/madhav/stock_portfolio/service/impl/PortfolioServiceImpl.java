package com.groww.madhav.stock_portfolio.service.impl;

import com.groww.madhav.stock_portfolio.dto.HoldingResponse;
import com.groww.madhav.stock_portfolio.dto.PortfolioResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;
import com.groww.madhav.stock_portfolio.entity.Stock;
import com.groww.madhav.stock_portfolio.repository.PortfolioRepository;
import com.groww.madhav.stock_portfolio.repository.StockRepository;
import com.groww.madhav.stock_portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private StockRepository stockRepository;
    @Override
    public PortfolioResponse getPortfolio(String userId) {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(userId);
        if(portfolioOptional.isEmpty()){
            throw new RuntimeException("Portfolio not found for this userid : " + userId);
        }
        Portfolio portfolio = portfolioOptional.get();
        List<HoldingResponse> holdings = new ArrayList<>();
        double totalCurrentValue = 0.0;
        double totalBuyPrice = 0.0;
        // Check if holdings are available
        if (portfolio.getHoldings() == null || portfolio.getHoldings().isEmpty()) {
            throw new RuntimeException("No holdings found for this userId: " + userId);
        }

        for(Map.Entry<String,Portfolio.Holding>entry : portfolio.getHoldings().entrySet()){
            Portfolio.Holding holding = entry.getValue();
            // Avoid null or invalid data (e.g., negative price)
            if (holding == null || holding.getCurrentPrice() == null || holding.getBuyPrice() == null) {
                throw new RuntimeException("Invalid price data for stock: " + entry.getKey());
            }
            double gainLoss = holding.getCurrentPrice() - holding.getBuyPrice();
            holdings.add(new HoldingResponse(
                    entry.getKey(),
                    holding.getStockName(),
                    holding.getQuantity(),
                    holding.getBuyPrice(),
                    holding.getCurrentPrice(),
                    gainLoss
            ));
            totalBuyPrice += holding.getBuyPrice() * holding.getQuantity();
            totalCurrentValue += holding.getCurrentPrice() * holding.getQuantity();
        }
        double totalPL = totalCurrentValue - totalBuyPrice;
        double totalPLPercentage = totalBuyPrice != 0 ? (totalPL / totalBuyPrice) * 100 : 0;
        return new PortfolioResponse(holdings,totalBuyPrice,totalCurrentValue,totalPL,totalPLPercentage);
    }
}
