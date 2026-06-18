# Mini Order Project

상품(Product)과 주문(Order) 도메인으로 구성된 간단한 주문 관리 서비스입니다.

---

## 📌 프로젝트 소개

Spring Boot 기반의 주문 관리 프로젝트로 상품 등록/조회/수정/삭제와 주문 생성 및 조회 기능을 제공합니다.

추가적으로 다음과 같은 실무적인 요소를 적용했습니다.

- Soft Delete를 통한 데이터 정합성 유지
- Pessimistic Lock을 활용한 재고 원자성 보장
- Fetch Join을 통한 N+1 문제 해결
- Global Exception Handling 적용

---

## 🛠 Tech Stack

| Category | Technology |
|-----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.3 |
| ORM | Spring Data JPA, Hibernate |
| Database | H2 Database |
| Build Tool | Gradle |
| Documentation | Swagger (OpenAPI) |
| Utility | Lombok |

---

## ✨ 주요 기능

### Product

- 상품 등록
- 상품 단건 조회
- 상품 목록 조회
- 상품 수정
- 상품 삭제 (Soft Delete)

### Order

- 주문 생성
- 주문 단건 조회
- 주문 목록 조회 (Pagination)

---

## 🚀 도전 과제

### 재고 차감

주문 생성 시 상품 재고를 차감하도록 구현했습니다.

### 재고 원자성 보장

동시 주문 상황에서도 정확한 재고 관리가 가능하도록 **Pessimistic Lock**을 적용했습니다.

### N+1 문제 해결

주문 목록 조회 시 발생할 수 있는 성능 문제를 해결하기 위해 **Fetch Join**을 적용했습니다.

---

## ▶ 프로젝트 실행

### 1. 애플리케이션 실행

IntelliJ에서

```text
MinipjtOrderApplication 실행
```

---

### 2. Swagger 접속

```text
http://localhost:8080/swagger-ui/index.html
```

---

### 3. H2 Console 접속

```text
http://localhost:8080/h2-console
```

#### 접속 정보

```text
JDBC URL : jdbc:h2:~/orderdb
User Name : sa
Password : (공백)
```

---

## 🗄 ERD

### Product

| Column | Type |
|---------|------|
| id | Long |
| name | String |
| price | Integer |
| stock | Integer |
| deleted | Boolean |

### Order

| Column | Type |
|---------|------|
| id | Long |
| quantity | Integer |
| product_id | Long |

### Relationship

```text
Product (1) ----- (N) Order
```

---

## 📖 API Specification

### Product API

| Method | URL |
|---------|-----|
| POST | /products |
| GET | /products/{id} |
| GET | /products |
| PUT | /products/{id} |
| DELETE | /products/{id} |

### Order API

| Method | URL |
|---------|-----|
| POST | /orders |
| GET | /orders/{id} |
| GET | /orders |

---

## 🧠 기술적 의사결정

### Soft Delete

상품 삭제 시 실제 데이터를 제거하지 않고 논리 삭제를 적용했습니다.

**적용 이유**

- 주문 데이터와의 연관관계 유지
- 데이터 정합성 보장
- 삭제 이력 관리 가능

---

### Fetch Join

주문 목록 조회 시 Product 정보를 함께 조회하도록 Fetch Join을 적용했습니다.

**적용 이유**

- N+1 문제 방지
- 조회 성능 향상
- 불필요한 SQL 실행 감소

- 8-1. 주문 목록 조회 관련 테스트 이미지(참조시 이미지 파일명 인코딩 문제로 수정 예정)
- test-images/도전1test/15-3.도전1_주문목록조회2_pm.png
- test-images/도전1test/15-4.도전1_N+1해결_serverlog.png
---

### Pessimistic Lock

재고 차감 시 비관적 락을 적용했습니다.

**적용 이유**

- 동시성 문제 방지
- 재고 데이터 정합성 보장
- 재고 음수 발생 방지
    
- 9-1. 재고 1인 상품에 대한 주문 2번 시도 결과 테스트 이미지(참조시 이미지 파일명 인코딩 문제로 수정 예정)
- test-images/도전2test/16-4.도전2_재고0의 두번째주문(재고부족)_pm.png
---

### Custom Exception & GlobalExceptionHandler

공통 예외 처리 전략을 적용했습니다.

**적용 이유**

- 일관된 에러 응답 제공
- 예외 처리 코드 중복 제거
- 유지보수성 향상

---

## ✅ 테스트 결과

- 상품 CRUD 동작 확인
- 주문 생성 및 조회 확인
- Soft Delete 동작 확인
- 재고 차감 확인
- 재고 부족 예외 확인
- N+1 문제 해결 확인
- Pessimistic Lock 적용 확인
- Swagger API 테스트 완료
- H2 Console 데이터 확인 완료

---

## 📈 향후 개선 사항
- 상품 관리 및 주문 기능 UI/UX 개선
- Redis 기반 재고 관리 적용
- 인증/인가(JWT) 기능 추가
- 주문 취소 기능 구현
- Docker 환경 구성
- MySQL 연동
