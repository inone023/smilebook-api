☁️ SmileBook – Smart Library System (Graduation Project)
# 📚 SmileBook – Smart Library System  

스마트 도서관 서비스 **SmileBook**은  
도서 대여·반납 과정을 자동화하고, 연동된 모바일 앱을 이용해 여러 기능을 제공하는 **IoT 기반 스마트 도서 관리 시스템**입니다.  

> 🧩 **프로젝트명:** SmileBook  
> 🧠 **역할:** 백엔드 개발 (Spring Boot)  
> 🕓 **개발 기간:** 2025.03 ~ 2025.06  
> 👥 **팀 구성:** Android 1명 / Backend 1명 / Arduino 1명  
> 💡 **기술 스택:** Spring Boot · MySQL · Spring Security · JPA · Retrofit2 · Firebase · Arduino RC522  

---

## 🖼️ 프로젝트 개요
> 프로젝트의 목적과 구현 배경, 문제 해결 포인트를 간단히 기술하세요.

예시:
- 도서관 내 도서 대여/반납 과정을 자동화하고, 관리자의 업무 효율을 높이기 위해 개발  
- 기존 수동 대여 시스템의 불편함을 RFID 및 모바일 앱으로 보완  
- 사용자는 Android 앱을 통해 대여 및 반납 가능, 관리자는 서버에서 실시간 상태 확인 가능  

📷 *[프로젝트 시연 이미지 or 시연 영상 썸네일 추가]*

---

## ⚙️ 시스템 아키텍처

> 전체 구성도 (Android – Spring Boot – Arduino 간 통신 구조)를 한눈에 볼 수 있는 다이어그램을 넣으세요.

📌 *예시 파일:* `/docs/architecture.png`  
📷 *TODO: 시스템 아키텍처 다이어그램 삽입*

---

## 🧱 기술 스택

| 분류 | 기술 |
|------|------|
| **Backend** | Java 17, Spring Boot 3.x, Spring Security, JPA, MySQL |
| **Frontend (Mobile)** | Java, Android Studio, Retrofit2, Firebase Messaging |
| **Hardware** | Arduino Wemos D1 R2 (ESP8266), RFID-RC522 |
| **Infra & Tools** | AWS EC2, Gradle, IntelliJ IDEA, GitHub, Postman |

---

## 🔐 인증 구조 (Authentication Flow)

> 본 프로젝트에서는 **Spring Security의 FormLogin 구조를 REST 환경에 맞게 커스터마이징**하였습니다.  
> Android 앱에서 ID/PW를 JSON 형태로 전송하면, 서버에서 DB 검증 후 JSON 응답을 반환합니다.  

📋 인증 흐름 요약  
1. Android → `/api/login` (JSON POST 요청)  
2. Server → DB 조회 및 비밀번호 검증 (`CustomAuthenticationProvider`)  
3. SuccessHandler → 사용자 객체 JSON 응답  
4. Android → 로그인 성공 시 SharedPreferences로 상태 유지  

📷 *TODO: 인증 흐름 다이어그램 (`/docs/auth_flow.png`)*

---

## 📡 주요 기능

| 구분 | 기능 요약 |
|------|-------------|
| 회원 | 로그인 / 회원가입 / 계정 관리 |
| 도서 | 등록 / 수정 / 삭제 / 대여 / 반납 |
| 하드웨어 | RFID 인식 → 서버 전송 → 도서 상태 갱신 |
| 알림 | Firebase FCM 토큰 등록 및 푸시 알림 발송 |

📷 *TODO: 각 기능별 화면 캡처 (`/docs/features/`) 추가*

---

## 🗃️ 데이터베이스 설계 (ERD)

> 데이터 간 관계를 명확히 보여주는 ERD를 포함하세요.  
> 예: `member`, `book`, `rental`, `firebase_token` 테이블 중심

📷 *TODO: `/docs/erd.png` (ERD 다이어그램 삽입)*

---

## 📍 API 설계

> 대표 API만 표 형식으로 요약하고, 세부 내용은 `/docs/api-spec.md` 등으로 분리해도 좋습니다.

| 기능 | Method | Endpoint | 설명 |
|------|---------|-----------|------|
| 로그인 | POST | `/api/login` | 사용자 인증 (JSON 요청/응답) |
| 회원가입 | POST | `/api/member/signup` | 새 계정 등록 |
| 도서 조회 | GET | `/api/book/{id}` | 도서 상세 정보 반환 |
| 대여 | POST | `/api/rental` | 도서 대여 처리 |
| 반납 | PATCH | `/api/rental/{id}` | 반납 처리 |
| RFID 이벤트 | POST | `/rfid/events` | Arduino UID 이벤트 수신 |

---

## 🧩 시스템 동작 흐름

📷 *TODO: `/docs/system_flow.png` (전체 시퀀스 다이어그램 추가)*

---

## 🧠 담당 역할 (본인 기여도)

> 포트폴리오용으로 매우 중요합니다 — 기술 역량을 명확히 보여주세요.

- 백엔드 전반 설계 및 구현  
- 로그인 인증 커스터마이징 (FormLogin → JSON 응답)  
- 도서/회원/대여 관리 API 설계  
- JPA 기반 데이터베이스 설계 및 쿼리 최적화  
- Arduino 연동 및 REST 통신 검증  
- EC2 서버 배포 및 외부 접근 테스트

📷 *TODO: 직접 작성한 코드 스니펫 예시 또는 로그 화면 첨부 (`/docs/code_snippet.png`)*

---

## 🔧 트러블슈팅

| 문제 | 원인 | 해결 |
|------|------|------|
| RFID 간헐적 인식 불량 | 3.3V 전원 불안정 | 배선 재정비 및 안정화 |
| 로그인 실패 | 요청 JSON 키 불일치 | DTO 필드명(`username`, `password`) 일치시킴 |
| 403 Forbidden | 세션 미유지 | FormLogin 기반 JSON 응답 구조로 커스터마이징 |
| DB 연결 오류 | MySQL 접속 정보 누락 | `application.yml` 환경변수 적용 |

📷 *TODO: 실제 에러 로그 또는 콘솔 캡처 추가 (`/docs/errors.png`)*

---

## 🧭 실행 방법

```bash
# 1️⃣ 프로젝트 클론
git clone https://github.com/USERNAME/smilebook-api.git

# 2️⃣ 환경변수 설정
export DB_PASSWORD=비밀번호

# 3️⃣ 서버 실행
./gradlew bootRun


서버 기본 주소: http://3.39.9.175:8080

📷 TODO: EC2 실행 캡처 또는 Postman 테스트 캡처 추가 (/docs/run_server.png)

📱 연동 리포지토리
구성	리포지토리	설명
📱 Android	SmileBook Android
	사용자 UI, Retrofit 통신
🔧 Arduino	SmileBook Arduino
	RFID 인식 및 서버 통신
🧰 개발 환경
항목	버전
OS	Windows 11 / Android 14
IDE	IntelliJ IDEA, Android Studio
Build	Gradle 8.x
DB	MySQL 8.x
Server	AWS EC2 (Ubuntu)
🧾 프로젝트 결과 및 시연

완성본 이미지, 시연 영상, 발표 자료 등을 추가하세요.

📷 TODO: /docs/demo.gif, /docs/presentation.pdf 삽입

💬 프로젝트를 통해 배운 점

포트폴리오용 README에서 가장 중요한 부분 중 하나예요.

예시 문장:

Spring Security의 내부 구조(Filter, Provider, Handler)를 직접 구현하며 인증 과정을 깊이 이해함

Android – Backend – Hardware 간 통신을 직접 설계하며 전체 시스템 아키텍처를 구축하는 경험을 얻음

RESTful API 설계 원칙과 예외 처리, 실시간 통신 구조의 중요성을 학습함

협업 중 코드 버전 관리와 역할 분담의 중요성을 체감함

🔗 Reference

Spring Security 공식 문서

Retrofit2 Developer Guide

Arduino MFRC522 Library

AWS EC2 Documentation

📬 Contact

포트폴리오용 README는 반드시 “연락처”가 포함되어야 합니다.

👩‍💻 개발자: [홍소연]
📧 Email: example@email.com

🌐 GitHub: https://github.com/USERNAME
