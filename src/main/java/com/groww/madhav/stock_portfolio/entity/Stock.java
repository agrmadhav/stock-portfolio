package com.groww.madhav.stock_portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    private String stockId;
    private String stockName;
    private Double openPrice;
    private Double closePrice;
    private Double highPrice;
    private Double lowPrice;
    private Double settlementPrice;
    // Getters, Setters, Constructors
    public Stock() {

    };

    public Stock(String stockId, String stockName, Double openPrice, Double closePrice, Double highPrice, Double lowPrice, Double settlementPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.settlementPrice = settlementPrice;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(Double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }
}

