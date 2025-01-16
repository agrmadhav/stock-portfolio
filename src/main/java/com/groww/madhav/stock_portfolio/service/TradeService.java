package com.groww.madhav.stock_portfolio.service;

import com.groww.madhav.stock_portfolio.dto.TradeRequest;
import com.groww.madhav.stock_portfolio.dto.TradeResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;
import org.springframework.stereotype.Service;

@Service
public interface TradeService {
    TradeResponse executeTrade(TradeRequest tradeRequest);
    void updatePortfolioTotals(Portfolio portfolio);
}
