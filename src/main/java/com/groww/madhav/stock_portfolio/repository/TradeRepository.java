package com.groww.madhav.stock_portfolio.repository;

import com.groww.madhav.stock_portfolio.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    
}
