package com.groww.madhav.stock_portfolio.repository;

import com.groww.madhav.stock_portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {

}
