package com.groww.madhav.stock_portfolio.repository;

import com.groww.madhav.stock_portfolio.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
