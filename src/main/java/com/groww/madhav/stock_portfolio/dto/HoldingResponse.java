package com.groww.madhav.stock_portfolio.dto;

public class HoldingResponse {
    private String stockId;
    private String stockName;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double gainLoss;

    public HoldingResponse(String stockId, String stockName, int quantity, double buyPrice, double currentPrice, double gainLoss) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.gainLoss = gainLoss;
    }

    // Getters and Setters
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getGainLoss() {
        return gainLoss;
    }

    public void setGainLoss(double gainLoss) {
        this.gainLoss = gainLoss;
    }
}
