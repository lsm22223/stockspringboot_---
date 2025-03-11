package com.example.stock_springboot.service;

import com.example.stock_springboot.entity.StockEntity;
import com.example.stock_springboot.repository.StockRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class StockService {
    private static final String API_KEY = "78d370e508dc4a8ead01badc4b091edc";
    private static final String BASE_URL = "https://api.twelvedata.com/price?symbol=";

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    // 주식 조회
    public List<StockEntity> getAllStocks() {
        return stockRepository.findAll();
    }
    
    public boolean addStock(String stockName) {
        if (stockRepository.existsById(stockName)) {
            return false;
        }
        Double currentPrice = getStockPrice(stockName);
        if (currentPrice == null) {
            return false;
        }
        StockEntity stock = new StockEntity();
        stock.setName(stockName);
        stock.setPrice(currentPrice.intValue());
        stockRepository.save(stock);
        return true;
    }

    public boolean deleteStock(String stockName) {
        if (stockRepository.existsById(stockName)) {
            stockRepository.deleteById(stockName);
            return true;
        }
        return false;
    }

    public Double getStockPrice(String symbol) {
        try {
            String urlString = BASE_URL + symbol + "&apikey=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder jsonStr = new StringBuilder();
            while (scanner.hasNext()) {
                jsonStr.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject jsonObj = new JSONObject(jsonStr.toString());
            return jsonObj.has("price") ? jsonObj.getDouble("price") : null;

        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
