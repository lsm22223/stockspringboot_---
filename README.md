Stock Simulation Service

1. 프로젝트 개요
이 애플리케이션은 가상의 주식 투자 시뮬레이션을 제공하는 Spring Boot 기반 웹 애플리케이션입니다.
사용자는 가상의 초기 자금을 등록한 후 실시간 주가 API를 통해 주식을 매수 및 매도할 수 있으며,
거래 내역과 투자 성과(수익금 및 수익률)를 관리할 수 있습니다.
모든 데이터는 JPA와 H2 데이터베이스를 사용하여 저장되며,
거래 내역은 TransactionEntity를 기반으로 기록됩니다.

2. 기술 스택

Java 17
Spring Boot
Spring Data JPA / Hibernate
H2 데이터베이스 
Maven
Twelve Data API (실시간 주가 조회)

3. 프로젝트 구조
src/main/java/com/example/stock_springboot
── controller
└─ PlayerController.java, StockController.java, StockMarketController.java, TransactionController.java
── entity
└─ PlayerEntity.java, StockEntity.java, PlayerStockEntity.java, TransactionEntity.java
── repository
└─ PlayerRepository.java, StockRepository.java, TransactionRepository.java
── service
└─ PlayerService.java, StockService.java, StockMarketService.java, TransactionService.java
── exception
└─ CustomException.java, GlobalExceptionHandler.java
── StockSpringbootApplication.java
src/main/resources
└─ application.properties

API 엔드포인트 및 사용법

4.1. 플레이어 추가
- HTTP Method: POST
- URL: http://localhost:8080/api/players
- 요청 본문 (JSON):
{
"playerId": "player3",
"playerMoney": 2000,
"playerStockList": []
}
- 설명: 사용자를 등록하면 시스템에 새로운 플레이어가 추가됩니다.

4.2. 플레이어 조회
- HTTP Method: GET
- URL: http://localhost:8080/api/players
- 설명: 모든 플레이어 정보를 JSON 배열로 반환합니다.

4.3. 주식 조회
- HTTP Method: GET
- URL: http://localhost:8080/api/stocks
- 설명: 등록된 모든 주식 정보를 JSON 배열로 반환합니다.

4.4. 주식 추가 (실시간 가격 등록)
- HTTP Method: POST
- URL: http://localhost:8080/api/stocks
- 요청 본문 (JSON):
{
"name": "{주식티커}"
}
- 설명: Twelve Data API를 사용하여 실시간 주가를 가져와 주식 정보를 등록합니다.

4.5. 주식 삭제
- HTTP Method: DELETE
- URL: http://localhost:8080/api/stocks/{주식이름}
- 예: http://localhost:8080/api/stocks/AAPL
- 설명: 지정된 주식이 삭제됩니다.

4.6. 주식 구매
- HTTP Method: POST
- URL: http://localhost:8080/api/market/buy
- 쿼리 파라미터: playerId, stockName, quantity
- 예: http://localhost:8080/api/market/buy?playerId=player3&stockName=AAPL&quantity=2
- 설명: 플레이어가 실시간 가격(AAPL)을 기반으로 주식을 구매하며, 구매 내역은 거래 내역에 기록됩니다.

4.7. 주식 판매
- HTTP Method: POST
- URL: http://localhost:8080/api/market/sell
- 쿼리 파라미터: playerId, stockName, quantity
- 예: http://localhost:8080/api/market/sell?playerId=player3&stockName=AAPL&quantity=1
- 설명: 플레이어가 주식을 판매하면, 판매 가격(실시간 주가) 기반으로 손익이 계산되고,
거래 내역과 함께 플레이어의 수익 및 수익률이 업데이트됩니다.

4.8. 거래 내역 조회
- HTTP Method: GET
- URL: http://localhost:8080/api/transactions
- 설명: 모든 거래 내역이 JSON 배열 형태로 반환됩니다.

실행 방법

5.1. 프로젝트 클론
git clone <repository-url>
cd stock_springboot

5.2. 데이터베이스 설정
application.properties 파일에서 H2 또는 MySQL 데이터베이스 설정을 확인 및 수정합니다.

5.3. 프로젝트 빌드 및 실행
mvn clean package
mvn spring-boot:run

5.4. API 테스트
Postman 또는 curl을 사용하여 위 API 엔드포인트들을 테스트합니다.

6. 참고 사항

기본적으로 H2 데이터베이스를 사용하며, application.properties 파일에서 데이터베이스 URL, 드라이버, 사용자 이름, 비밀번호 등을 설정할 수 있습니다.
DELETE 요청이 차단될 경우 Spring Security 설정을 확인하거나 CSRF 보호를 비활성화할 수 있습니다.
실시간 주가 API는 Twelve Data API를 사용하며, API 키는 StockService에 하드코딩 되어 있습니다.
거래 내역은 TransactionEntity를 기반으로 TransactionRepository를 통해 관리되며,
GET /api/transactions 엔드포인트로 조회할 수 있습니다.
플레이어의 수익 및 수익률은 PlayerEntity에 저장되며, 주식 매매 시 업데이트됩니다.

7. 라이선스
이 프로젝트는 MIT 라이선스 하에 배포됩니다.
