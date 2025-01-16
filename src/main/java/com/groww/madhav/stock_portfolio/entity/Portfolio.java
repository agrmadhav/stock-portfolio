package com.groww.madhav.stock_portfolio.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Portfolio {
    @Id
    private String userId;
    @ElementCollection
    private Map<String, Holding> holdings = new HashMap<>();
    private Double totalBuyPrice = 0.0;
    private Double totalCurrentValue = 0.0;

    public Portfolio() {}

    // Parameterized Constructor
    public Portfolio(String userId, Double totalBuyPrice, Double totalCurrentValue) {
        this.userId = userId;
        this.totalBuyPrice = totalBuyPrice;
        this.totalCurrentValue = totalCurrentValue;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Holding> holdings) {
        this.holdings = holdings;
    }

    public Double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(Double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public Double getTotalCurrentValue() {
        return totalCurrentValue;
    }

    public void setTotalCurrentValue(Double totalCurrentValue) {
        this.totalCurrentValue = totalCurrentValue;
    }

    @Embeddable
    public static class Holding {
        private String stockName;
        private Integer quantity;
        private Double buyPrice;
        private Double currentPrice;

        // No-Args Constructor
        public Holding() {}

        // Parameterized Constructor
        public Holding(String stockName, Integer quantity, Double buyPrice, Double currentPrice) {
            this.stockName = stockName;
            this.quantity = quantity;
            this.buyPrice = buyPrice;
            this.currentPrice = currentPrice;
        }

        // Getters and Setters
        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(Double buyPrice) {
            this.buyPrice = buyPrice;
        }

        public Double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Double currentPrice) {
            this.currentPrice = currentPrice;
        }
    }
}

