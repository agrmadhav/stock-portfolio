package com.groww.madhav.stock_portfolio;

import com.groww.madhav.stock_portfolio.entity.Stock;
import com.groww.madhav.stock_portfolio.repository.StockRepository;
import com.groww.madhav.stock_portfolio.service.StockService;
import com.groww.madhav.stock_portfolio.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @InjectMocks
    private StockService stockService = new StockServiceImpl();

    @Mock
    private StockRepository stockRepository;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStockById_Success() {
        // Given
        String stockId = "AAPL";
        Stock stock = new Stock();
        stock.setStockId(stockId);
        stock.setStockName("Apple Inc.");
        stock.setOpenPrice(150.0);
        stock.setClosePrice(155.0);
        stock.setHighPrice(160.0);
        stock.setLowPrice(145.0);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        Stock result = stockService.getStockById(stockId);

        // Then
        assertNotNull(result);
        assertEquals(stockId, result.getStockId());
        assertEquals("Apple Inc.", result.getStockName());
        assertEquals(150.0, result.getOpenPrice());
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testGetStockById_StockNotFound() {
        // Given
        String stockId = "AAPL";
        when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            stockService.getStockById(stockId);
        });
        assertEquals("Stock not found with id : AAPL", exception.getMessage());
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testProcessStockData_Success() throws Exception {
        // Given
        String stockData = "AAPL,Apple Inc.,150.0,155.0,160.0,145.0";
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(new java.io.ByteArrayInputStream(stockData.getBytes()));

        // When
        stockService.processStockData(file);

        // Then
        verify(stockRepository, times(1)).saveAll(anyList());
    }

//    @Test
//    void testProcessStockData_InvalidStockData() throws Exception {
//        // Given
//        String stockData = "AAPL,Apple Inc.,150.0,155.0";
//        MultipartFile file = mock(MultipartFile.class);
//        when(file.getInputStream()).thenReturn(new java.io.ByteArrayInputStream(stockData.getBytes()));
//
//        // When & Then
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            stockService.processStockData(file);
//        });
//        assertEquals("Invalid stock data", exception.getMessage());
//    }

    @Test
    void testGetAllStocks() {
        // Given
        Stock stock1 = new Stock();
        stock1.setStockId("AAPL");
        stock1.setStockName("Apple Inc.");
        stock1.setOpenPrice(150.0);
        stock1.setClosePrice(155.0);
        stock1.setHighPrice(160.0);
        stock1.setLowPrice(145.0);

        Stock stock2 = new Stock();
        stock2.setStockId("TSLA");
        stock2.setStockName("Tesla Inc.");
        stock2.setOpenPrice(600.0);
        stock2.setClosePrice(620.0);
        stock2.setHighPrice(630.0);
        stock2.setLowPrice(580.0);

        when(stockRepository.findAll()).thenReturn(List.of(stock1, stock2));

        // When
        List<Stock> stocks = stockService.getAllStocks();

        // Then
        assertNotNull(stocks);
        assertEquals(2, stocks.size());
        verify(stockRepository, times(1)).findAll();
    }
}

