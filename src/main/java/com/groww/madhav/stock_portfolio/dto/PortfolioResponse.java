package com.groww.madhav.stock_portfolio.dto;

import java.util.List;

public class PortfolioResponse {
    private List<HoldingResponse> holdings;
    private double totalCurrentValue;
    private double totalBuyPrice;
    private double totalPL;
    private double totalPLPercent;

    public PortfolioResponse(){

    }

    public PortfolioResponse(List<HoldingResponse> holdings, double totalCurrentValue, double totalBuyPrice, double totalPL, double totalPLPercent) {
        this.holdings = holdings;
        this.totalCurrentValue = totalCurrentValue;
        this.totalBuyPrice = totalBuyPrice;
        this.totalPL = totalPL;
        this.totalPLPercent = totalPLPercent;
    }

    // Getters and Setters
    public List<HoldingResponse> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<HoldingResponse> holdings) {
        this.holdings = holdings;
    }

    public double getTotalCurrentValue() {
        return totalCurrentValue;
    }

    public void setTotalCurrentValue(double totalCurrentValue) {
        this.totalCurrentValue = totalCurrentValue;
    }

    public double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public double getTotalPL() {
        return totalPL;
    }

    public void setTotalPL(double totalPL) {
        this.totalPL = totalPL;
    }

    public double getTotalPLPercent() {
        return totalPLPercent;
    }

    public void setTotalPLPercent(double totalPLPercent) {
        this.totalPLPercent = totalPLPercent;
    }
}
