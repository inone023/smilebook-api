# 📚 SmileBook API Server

**IoT 기반 스마트 도서관 시스템의 백엔드 서버**입니다.  
RFID 하드웨어와 Android 애플리케이션을 연동하여 **도서 대여·반납·예약·도난 방지 기능**을 제공합니다.  
Spring Boot 기반 REST API로 설계되었으며, AWS 환경에서 운영 가능합니다.

---

## 🧠 프로젝트 개요

- **프로젝트명:** IoT를 이용한 스마트 도서관  
- **팀명:** SmileBook  
- **기간:** 2023.09.06 ~ 2024.06.11  
- **담당 역할:** 서버 구축, API 설계 및 구현, 인증 로직 커스터마이징  
- **관련 리포지토리:**  
  - [📱 SmileBook-Android](https://github.com/yourusername/smilebook-android)  
  - [🔌 SmileBook-Arduino](https://github.com/yourusername/smilebook-arduino)

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

yaml
코드 복사

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
