package com.groww.madhav.stock_portfolio;

import com.groww.madhav.stock_portfolio.dto.TradeRequest;
import com.groww.madhav.stock_portfolio.dto.TradeResponse;
import com.groww.madhav.stock_portfolio.entity.Portfolio;
import com.groww.madhav.stock_portfolio.repository.PortfolioRepository;
import com.groww.madhav.stock_portfolio.service.TradeService;
import com.groww.madhav.stock_portfolio.service.impl.TradeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService = new TradeServiceImpl();

    @Mock
    private PortfolioRepository portfolioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteTrade_Buy_Success() {
        // Given
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setUserAccountId("user1");
        tradeRequest.setTradeType("BUY");
        tradeRequest.setQuantity(10);
        tradeRequest.setPrice(150.0);
        tradeRequest.setStockId("AAPL");
        tradeRequest.setStockName("Apple Inc.");

        Portfolio portfolio = new Portfolio("user1", 0.0, 0.0);
        when(portfolioRepository.findById("user1")).thenReturn(Optional.of(portfolio));

        // When
        TradeResponse response = tradeService.executeTrade(tradeRequest);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Trade executed successfully.", response.getMessage());

        Portfolio.Holding holding = portfolio.getHoldings().get("AAPL");
        assertNotNull(holding);
        assertEquals(10, holding.getQuantity());
        assertEquals(150.0, holding.getBuyPrice());

        verify(portfolioRepository, times(1)).save(portfolio);
    }

    @Test
    void testExecuteTrade_Sell_Success() {
        // Given
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setUserAccountId("user1");
        tradeRequest.setTradeType("SELL");
        tradeRequest.setQuantity(5);
        tradeRequest.setPrice(150.0);
        tradeRequest.setStockId("AAPL");
        tradeRequest.setStockName("Apple Inc.");

        Portfolio.Holding holding = new Portfolio.Holding("Apple Inc.", 10, 150.0, 155.0);
        Portfolio portfolio = new Portfolio("user1", 0.0, 0.0);
        portfolio.getHoldings().put("AAPL", holding);

        when(portfolioRepository.findById("user1")).thenReturn(Optional.of(portfolio));

        // When
        TradeResponse response = tradeService.executeTrade(tradeRequest);

        // Then
        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Trade executed successfully.", response.getMessage());

        Portfolio.Holding updatedHolding = portfolio.getHoldings().get("AAPL");
        assertEquals(5, updatedHolding.getQuantity());

        verify(portfolioRepository, times(1)).save(portfolio);
    }

    @Test
    void testExecuteTrade_Sell_InsufficientQuantity() {
        // Given
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setUserAccountId("user1");
        tradeRequest.setTradeType("SELL");
        tradeRequest.setQuantity(15);
        tradeRequest.setPrice(150.0);
        tradeRequest.setStockId("AAPL");
        tradeRequest.setStockName("Apple Inc.");

        Portfolio.Holding holding = new Portfolio.Holding("Apple Inc.", 10, 150.0, 155.0);
        Portfolio portfolio = new Portfolio("user1", 0.0, 0.0);
        portfolio.getHoldings().put("AAPL", holding);

        when(portfolioRepository.findById("user1")).thenReturn(Optional.of(portfolio));

        // When
        TradeResponse response = tradeService.executeTrade(tradeRequest);

        // Then
        assertNotNull(response);
        assertEquals("Failure", response.getStatus());
        assertEquals("Insufficient quantity to sell.", response.getMessage());

        verify(portfolioRepository, never()).save(any());
    }

    @Test
    void testExecuteTrade_InvalidTradeType() {
        // Given
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setUserAccountId("user1");
        tradeRequest.setTradeType("INVALID");
        tradeRequest.setQuantity(10);
        tradeRequest.setPrice(150.0);
        tradeRequest.setStockId("AAPL");
        tradeRequest.setStockName("Apple Inc.");

        Portfolio portfolio = new Portfolio("user1", 0.0, 0.0);
        when(portfolioRepository.findById("user1")).thenReturn(Optional.of(portfolio));

        // When
        TradeResponse response = tradeService.executeTrade(tradeRequest);

        // Then
        assertNotNull(response);
        assertEquals("Failure", response.getStatus());
        assertEquals("Invalid trade type. Use 'BUY' or 'SELL'.", response.getMessage());

        verify(portfolioRepository, never()).save(any());
    }

//    @Test
//    void testUpdatePortfolioTotals() {
//        // Given
//        Portfolio portfolio = new Portfolio("user1", 0.0, 0.0);
//        Portfolio.Holding holding1 = new Portfolio.Holding("Apple Inc.", 10, 150.0, 155.0);
//        Portfolio.Holding holding2 = new Portfolio.Holding("Tesla Inc.", 5, 300.0, 310.0);
//        portfolio.getHoldings().put("AAPL", holding1);
//        portfolio.getHoldings().put("TSLA", holding2);
//
//        // When
//        tradeService.updatePortfolioTotals(portfolio);
//
//        // Then
//        assertEquals(3000.0, portfolio.getTotalBuyPrice());
//        assertEquals(3175.0, portfolio.getTotalCurrentValue());
//    }
}
