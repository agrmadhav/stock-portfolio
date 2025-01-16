package com.groww.madhav.stock_portfolio.service;

import com.groww.madhav.stock_portfolio.dto.PortfolioResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;


public interface PortfolioService {
    PortfolioResponse getPortfolio(String userId);
}
