# 📚 SmileBook API Server

**IoT 기반 스마트 도서관 시스템의 백엔드 서버**입니다.  
RFID 하드웨어와 Android 애플리케이션을 연동하여 **도서 대여·반납·예약·도난 방지 기능**을 제공합니다.  
Spring Boot 기반 REST API로 설계되었으며, AWS 환경에서 운영 가능합니다.

---

## 🧠 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | IoT를 이용한 스마트 도서관 |
| **팀명** | SmileBook |
| **개발 기간** | 2023.09.06 ~ 2024.06.11 |

---

## 👥 팀 구성

- **시소연 (팀장)** – 서버 구축, Arduino 회로 연결 및 로직 개발, Android 로직 일부 개발, API 일부 개발  
- **정보라** – Android XML, Android 로직 일부 개발, API 일부 개발  
- **이나영** – UI 디자인(Figma), Android XML, Arduino 초기 회로 구성  

---

## 담당 역할 (시소연 – 팀장)

팀장으로서 서버, 하드웨어, 안드로이드 전반을 아우르며 시스템 통합 및 주요 기능 개발을 담당했습니다.

### 주요 업무
- 프로젝트 일정 관리 및 역할 분담  
- AWS EC2·RDS 서버 구축 및 환경 설정  
- Spring Boot 기반 REST API 구현  
  - 회원, 도서, 대여, RFID 연동 API 설계 및 개발  
- Arduino RC522 RFID 회로 재배치 및 통신 안정화, 제어 로직 개발  
- Android Retrofit 통신 및 로직 개발  
- Amazon RDS(MySQL) 데이터베이스 설계 및 JPA Entity 매핑  
  - `member`, `admin`, `book_category` 엔티티 설계  
- 테스트 및 오류 해결  
  - 커스텀 필터/핸들러 적용을 통한 인증 구조 단순화 및 보안 강화  
  - 개발 기능 단위 테스트 및 오류(403, JSON 매핑 등) 해결  

---

## 관련 리포지토리

- [SmileBook-Android (Android)](https://github.com/inone023/smilebook-android)  
- [SmileBook-Arduino (Arduino)](https://github.com/inone023/smilebook-arduino)

---

## ⚙️ Tech Stack

| 분야 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.2.5 |
| ORM | Spring Data JPA |
| DB | MySQL (AWS RDS) |
| Infra | AWS EC2, RDS |
| Build Tool | Gradle |
| Security | Spring Security (Custom Filter / Handler) |
| Messaging | FCM (Firebase Cloud Messaging) |
| IDE | IntelliJ IDEA |

---

## 🧩 주요 기능 (API 기준)

### 🔐 인증 / 회원
- 회원가입, 로그인 (세션 기반 인증)
- 관리자 인증 (코드 검증)
- 회원 정보 조회 / 수정

### 📚 도서 관리
- 도서 등록 / 수정 / 삭제 (관리자)
- 도서 검색 / 조회 / 예약
- 예약 자동 취소 및 연체 알림

### 📡 RFID / IoT 연동
- RFID 태그를 통한 대출 / 반납 처리
- 예약자 불일치 시 경보 신호 송출
- PIR 센서 감지 후 리더기 활성화 로직 연동

### 🔔 푸시 알림
- FCM 기반 대출·반납·경고·이용정지 알림

---

## 🧾 시스템 구조

Android App ⇄ REST API (Spring Boot) ⇄ MySQL (RDS)
⇅
Arduino (RFID, PIR)

---

## 🧠 주요 구현 내용

- Spring Security의 기본 폼 인증 구조를 REST 환경에 맞게 커스터마이징  
- `CustomAuthenticationFilter`, `CustomAuthenticationFailureHandler`, `CustomAccessDeniedHandler` 구현  
- JWT 대신 세션 기반 구조로 간소화  
- JPA 기반 Entity 설계: Member / Admin / Book / BookCategory / Borrow / Reservation  
- FCM을 이용한 푸시 알림 로직 구현  

---

## 📸 예시 API 구조

👤 회원 (Member)
| 기능              | 메서드    | 엔드포인트                            |
| --------------- | ------ | -------------------------------- |
| 회원가입            | `POST` | `/member/join`                   |
| 로그인             | `POST` | `/api/login`                     |
| 회원 정보 조회        | `GET`  | `/member/{memberId}`             |
| 회원 상세 정보 조회     | `POST` | `/api/member/details`            |
| 회원 정보 수정        | `POST` | `/api/member/update`             |
| 회원의 모든 대출 도서 조회 | `GET`  | `/member/{memberId}/all-books`   |
| 경고 업데이트         | `POST` | `/member/updateWarning`          |
| 정지 사유 업데이트      | `POST` | `/member/updateSuspensionReason` |

🧑‍💼 관리자 (Admin)
| 기능           | 메서드    | 엔드포인트                 |
| ------------ | ------ | --------------------- |
| 관리자 코드 확인    | `POST` | `/api/checkAdminCode` |
| 전체 사용자 목록 조회 | `GET`  | `/admin/users`        |

📚 도서 (Book)
| 기능               | 메서드    | 엔드포인트                                   |
| ---------------- | ------ | --------------------------------------- |
| 전체 도서 목록 조회      | `GET`  | `/books`                                |
| 도서 단건 조회         | `GET`  | `/books/{bookId}`                       |
| 카테고리별 도서 목록 조회   | `GET`  | `/books/category/{category}`            |
| 도서 위치 조회 (제목 기준) | `GET`  | `/books/bookLocation?title={bookTitle}` |
| 도서 위치 조회 (ID 기준) | `GET`  | `/books/{bookId}/location`              |
| 도서 추가            | `POST` | `/books/add`                            |
| 대출 연장            | `PUT`  | `/books/{bookId}/extendLoan`            |

🗂️ 위시리스트 (Wishlist)
| 기능            | 메서드      | 엔드포인트                                  |
| ------------- | -------- | -------------------------------------- |
| 위시리스트 추가      | `POST`   | `/wishlist/add`                        |
| 위시리스트 조회      | `POST`   | `/wishlist/check`                      |
| 회원별 위시리스트 조회  | `POST`   | `/wishlist/{memberId}`                 |
| 위시리스트에서 도서 삭제 | `DELETE` | `/wishlist/delete/{memberId}/{bookId}` |

📅 예약 (Reservation)
| 기능    | 메서드    | 엔드포인트           |
| ----- | ------ | --------------- |
| 도서 예약 | `POST` | `/book/reserve` |

🛰️ IoT / RFID / Firebase
| 기능             | 메서드    | 엔드포인트                 |
| -------------- | ------ | --------------------- |
| Firebase 토큰 등록 | `POST` | `/firebase/token`     |
| 이미지 조회         | `GET`  | `/images/{imageName}` |

---

## 📈 결과 및 성과

- REST API와 RFID 시스템 간 통신 구조 설계 및 안정화  
- Spring Security 커스터마이징을 통한 인증 구조 단순화  
- 전시회 시연 시 IoT 대출/반납 기능 완전 구현 성공  

---

## 📄 License

This project is part of the **SmileBook Smart Library** graduation project.  
© 2024 SmileBook Team. All rights reserved.
