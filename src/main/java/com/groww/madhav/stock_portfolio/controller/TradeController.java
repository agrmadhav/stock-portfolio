package com.groww.madhav.stock_portfolio.controller;


import com.groww.madhav.stock_portfolio.dto.TradeRequest;
import com.groww.madhav.stock_portfolio.dto.TradeResponse;
import com.groww.madhav.stock_portfolio.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    // Record a trade (Buy/Sell)
    @PostMapping
    public ResponseEntity<TradeResponse> recordTrade(@RequestBody TradeRequest tradeRequest) {
        TradeResponse response = tradeService.executeTrade(tradeRequest);
        return ResponseEntity.ok(response);
    }
}
