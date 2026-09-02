# Fulfillment Match

이커머스 화주와 3PL 업체를 연결하는 B2B 매칭 서비스입니다.

화주는 물류 요청을 등록하고,
3PL 업체는 자신의 서비스 가능 조건에 맞는 요청을 확인한 뒤 견적을 제출할 수 있습니다.

현재는 Spring Boot 기반으로 MVP 백엔드 구조와 핵심 비즈니스 로직을 구현하고 있습니다.

---

## 주요 기능

### Shipping Request
- 화주 물류 요청 등록
- 요청 목록 / 상세 조회
- 요청 수정 / 삭제
- DTO 기반 Validation

### Fulfillment Company
- 3PL 업체 등록
- 업체 목록 / 상세 조회
- 업체 수정 / 삭제
- 서비스 가능 조건 관리

### Quote
- 화주 요청과 3PL 업체를 연결한 견적 등록
- 견적 목록 / 상세 조회
- 견적 수정 / 삭제
- JPA 연관관계를 이용한 요청 / 업체 연결

### Matching
화주의 요청 조건과 3PL 업체의 서비스 가능 조건을 비교하여
조건에 맞는 업체를 추천합니다.

현재 비교 조건:
- 서비스 가능 지역
- 냉장 / 냉동 가능 여부
- 반품 검수 가능 여부
- 특수 포장 가능 여부

조건을 만족한 업체는 매칭 점수를 계산하여 높은 점수순으로 정렬합니다.

매칭 결과에서 바로 견적 작성 화면으로 이동할 수 있으며,
화주 요청과 3PL 업체가 자동 선택됩니다.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Bean Validation

### Database
- PostgreSQL
- Docker

### View
- Thymeleaf

### Build
- Gradle

---

## 프로젝트 구조

```text
src/main/java/com/fulfillment/match

├── controller
├── domain
├── dto
├── exception
├── repository
└── service

```markdown
현재는 Thymeleaf 기반 MVP이며, 이후 REST API + React 구조로 전환할 예정입니다.