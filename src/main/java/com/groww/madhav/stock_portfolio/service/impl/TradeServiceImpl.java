package com.groww.madhav.stock_portfolio.service.impl;

import com.groww.madhav.stock_portfolio.dto.TradeRequest;
import com.groww.madhav.stock_portfolio.dto.TradeResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;
import com.groww.madhav.stock_portfolio.repository.PortfolioRepository;
import com.groww.madhav.stock_portfolio.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public TradeResponse executeTrade(TradeRequest tradeRequest) {
        // Fetch user's portfolio
        Portfolio portfolio = portfolioRepository.findById(tradeRequest.getUserAccountId())
                .orElse(new Portfolio(tradeRequest.getUserAccountId(), 0.0, 0.0));

        Portfolio.Holding holding = portfolio.getHoldings()
                .getOrDefault(tradeRequest.getStockId(), new Portfolio.Holding(
                        tradeRequest.getStockName(), 0, 0.0, 0.0));

        // Perform trade logic
        if (tradeRequest.getTradeType().equalsIgnoreCase("BUY")) {
            holding.setQuantity(holding.getQuantity() + tradeRequest.getQuantity());
            holding.setBuyPrice((holding.getBuyPrice() * (holding.getQuantity() - tradeRequest.getQuantity())
                    + tradeRequest.getPrice() * tradeRequest.getQuantity()) / holding.getQuantity());
        }
        else if (tradeRequest.getTradeType().equalsIgnoreCase("SELL")) {
            if (holding.getQuantity() < tradeRequest.getQuantity()) {
                return new TradeResponse("Failure", "Insufficient quantity to sell.");
            }
            holding.setQuantity(holding.getQuantity() - tradeRequest.getQuantity());
        }
        else {
            return new TradeResponse("Failure", "Invalid trade type. Use 'BUY' or 'SELL'.");
        }

        // Update holding or remove if quantity is zero
        if (holding.getQuantity() > 0) {
            portfolio.getHoldings().put(tradeRequest.getStockId(), holding);
        }
        else {
            portfolio.getHoldings().remove(tradeRequest.getStockId());
        }

        // Update portfolio totals
        updatePortfolioTotals(portfolio);

        // Save portfolio
        portfolioRepository.save(portfolio);

        return new TradeResponse("Success", "Trade executed successfully.");
    }

    public void updatePortfolioTotals(Portfolio portfolio) {
        double totalBuyPrice = 0.0;
        double totalCurrentValue = 0.0;

        for (Portfolio.Holding holding : portfolio.getHoldings().values()) {
            totalBuyPrice += holding.getBuyPrice() * holding.getQuantity();
            totalCurrentValue += holding.getCurrentPrice() * holding.getQuantity();
        }

        portfolio.setTotalBuyPrice(totalBuyPrice);
        portfolio.setTotalCurrentValue(totalCurrentValue);
    }
}
