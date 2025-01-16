package com.groww.madhav.stock_portfolio.controller;

import com.groww.madhav.stock_portfolio.dto.PortfolioResponse;
import com.groww.madhav.stock_portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @GetMapping("/{userId}")
    public ResponseEntity<PortfolioResponse> getPortfolio(@PathVariable("userId") String userId) {
        PortfolioResponse response = portfolioService.getPortfolio(userId);
        return  ResponseEntity.ok(response);
    }
}
