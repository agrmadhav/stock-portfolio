package com.groww.madhav.stock_portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;
    private String userAccountId;
    private String tradeType; // "buy" or "sell"
    private String stockId;
    private Integer quantity;
    private LocalDateTime timestamp;
    // Getters, Setters, Constructors
    public Trade() {

    }

    public Trade(Long tradeId, String userAccountId, String tradeType, String stockId, Integer quantity, LocalDateTime timestamp) {
        this.tradeId = tradeId;
        this.userAccountId = userAccountId;
        this.tradeType = tradeType;
        this.stockId = stockId;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
