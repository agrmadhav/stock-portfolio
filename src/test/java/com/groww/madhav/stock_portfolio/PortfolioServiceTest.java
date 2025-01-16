package com.groww.madhav.stock_portfolio;

import com.groww.madhav.stock_portfolio.dto.PortfolioResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;
import com.groww.madhav.stock_portfolio.entity.Stock;
import com.groww.madhav.stock_portfolio.repository.PortfolioRepository;
import com.groww.madhav.stock_portfolio.repository.StockRepository;
import com.groww.madhav.stock_portfolio.service.PortfolioService;
import com.groww.madhav.stock_portfolio.service.impl.PortfolioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private PortfolioService portfolioService = new PortfolioServiceImpl();

    private Portfolio portfolio;
    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the stock data
        stock = new Stock();
        stock.setStockId("STK123");
        stock.setStockName("Apple");
        stock.setOpenPrice(150.0);
        stock.setClosePrice(155.0);
        stock.setHighPrice(157.0);
        stock.setLowPrice(148.0);

        // Mock the portfolio data
        portfolio = new Portfolio("user123", 0.0, 0.0);
        Map<String, Portfolio.Holding> holdings = new HashMap<>();

        Portfolio.Holding holding = new Portfolio.Holding("Apple", 10, 150.0, 155.0);
        holdings.put("STK123", holding);
        portfolio.setHoldings(holdings);
        portfolio.setTotalBuyPrice(1500.0);  // 10 * 150.0
        portfolio.setTotalCurrentValue(1550.0); // 10 * 155.0
    }

//    @Test
//    void testGetPortfolio_Success() {
//        // Create mock data
//        Portfolio portfolio = new Portfolio();
//        portfolio.setHoldings(new HashMap<>());
//        portfolio.getHoldings().put("AAPL", new Portfolio.Holding("Apple", 10, 50.0, 60.0)); // Example holding
//        // Mock the repository
//        when(portfolioRepository.findById(anyString())).thenReturn(Optional.of(portfolio));
//
//        // Call the method
//        PortfolioResponse response = portfolioService.getPortfolio("userId");
//
//        // Assertion
//        assertEquals(50.0, response.getTotalBuyPrice()); // Ensure this is the correct value
//    }

    @Test
    void testGetPortfolio_ThrowException_WhenPortfolioNotFound() {
        // Arrange
        when(portfolioRepository.findById("user123")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> portfolioService.getPortfolio("user123"));
        assertEquals("Portfolio not found for this userid : user123", exception.getMessage());
    }
}
