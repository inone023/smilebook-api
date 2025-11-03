☁️ IoT를 이용한 스마트도서관 – Smart Library System (by Team SmileBook)
# 📚 IoT를 이용한 스마트도서관  
> Team SmileBook Graduation Project

**IoT를 이용한 스마트도서관**은 RFID와 모바일 애플리케이션을 활용해  
도서 대여 및 반납 과정을 자동화한 **스마트 도서 관리 시스템**입니다.  

> 🧩 **팀명:** SmileBook  
> 🧠 **프로젝트명:** IoT를 이용한 스마트도서관  
> 🕓 **개발 기간:** 2023.09.06 ~ 2024.06.11  
> 👥 **팀 구성:**  
> • **시소연 (팀장)** – 서버 구축, Arduino 회로 연결 및 로직 개발, Android 로직 일부 개발, API 일부 개발  
> • **정보라** – Android XML, Android 로직 일부 개발, API 일부 개발  
> • **이나영** – UI 디자인(Figma), Android XML, Arduino 초기 회로 구성  
>
> 💡 **담당 역할:**  
> • 팀장으로서 프로젝트 일정 관리 및 보고서 작성 및 발표, 디자인 컨펌, 역할 분담 수행 
> • AWS EC2·RDS 서버 구축   
> • RC522 RFID 회로 재배치 및 통신 안정화  
> • Android 로직, springboot 기반 restAPI 기능 구현: Retrofit 통신/ 회원가입 및 로그인 / 도서 위치 조회 / 내 정보 조회 및 수정 / 관리자 모드 전환 / 도서 예약 기능 / FCM을 이용한 대출 및 반납 알림, 이용 정지 알림, 경고 알림 / 회원 이용정지, 경고 기능 구현
> • amazon RDS mysql 데이터베이스 생성, member DB 제작, admin DB 제작, book_category DB 제작
> • 구현 기능 단위 테스트 및 오류(403, JSON 매핑 등) 해결

---

## 🎯 프로젝트 개요  

기존 도서관의 수동 대여 시스템은 관리자의 수작업 입력과 이용자 대기 시간이 발생하는 비효율적인 구조였습니다.  
이 프로젝트는 **IoT 기술을 활용한 자동화된 대여/반납 시스템**을 구현함으로써,  
- 도서 이용자의 편의성을 향상하고,  
- 관리자의 실시간 모니터링을 가능하게 하는  
**스마트 도서관 서비스**를 목표로 합니다.

📷 *[프로젝트 시연 이미지 / 영상 썸네일 추가]*

---

## ⚙️ 시스템 아키텍처

> Android – Spring Boot – Arduino 간 전체 통신 구조를 도식화한 다이어그램을 추가하세요.

📊 *TODO: `/docs/architecture.png` (시스템 아키텍처 다이어그램 삽입)*  

📋 **구성 요소 개요**
- **Android (사용자 앱)**: 로그인, 도서 검색/대여/반납 기능  
- **Spring Boot (서버)**: 사용자 인증, 도서 데이터 관리, RFID 이벤트 처리  
- **Arduino (하드웨어)**: RFID 태그 인식 및 UID 전송  

---

## 🧱 기술 스택

| 분야 | 사용 기술 |
|------|------------|
| **Backend** | Java 17, Spring Boot 3.x, Spring Security, JPA, MySQL |
| **Mobile** | Java, Android Studio, Retrofit2, Firebase |
| **Hardware** | Wemos D1 R2 (ESP8266), RFID-RC522 |
| **Infra** | AWS EC2, Gradle |
| **Tools** | IntelliJ IDEA, Postman, GitHub, Arduino IDE |

---

## 🔐 인증 구조 (Authentication Flow)

본 프로젝트는 **Spring Security의 FormLogin 구조를 REST 환경에 맞게 커스터마이징**하여  
Android 클라이언트와 JSON 기반으로 통신하도록 설계했습니다.

📋 **인증 절차 요약**
1. Android에서 `/api/login` 으로 JSON 요청 (`username`, `password`) 전송  
2. 서버의 `CustomAuthenticationFilter`에서 요청 파싱  
3. `CustomAuthenticationProvider`가 DB 조회 및 검증  
4. 성공 시 `CustomAuthenticationSuccessHandler`가 사용자 정보를 JSON으로 반환  
5. Android에서는 SharedPreferences에 로그인 정보를 저장해 상태 유지  

📷 *TODO: `/docs/auth_flow.png` (인증 흐름 다이어그램 추가)*  

💡 **핵심 구현 포인트**
- Spring Security 기본 인증 플로우(Filter → Provider → Handler)를 직접 구현  
- JSON 요청/응답 구조를 통해 REST 환경과 호환  
- JWT나 세션 없이 Android 중심 인증 흐름 구성  

---

## 📚 주요 기능

| 구분 | 기능 |
|------|------|
| **회원** | 로그인, 회원가입, 계정 관리 |
| **도서** | 등록, 수정, 삭제, 대여, 반납 |
| **하드웨어** | RFID 태그 인식 → 서버 전송 → 도서 상태 갱신 |
| **알림** | Firebase FCM 토큰 등록 및 푸시 알림 발송 |

📷 *TODO: `/docs/features/` (앱 UI, RFID 인식 장면 등 추가)*

---

## 🗃️ 데이터베이스 설계 (ERD)

> 엔티티 간 관계를 명확히 보여주는 ERD를 추가하세요.

📷 *TODO: `/docs/erd.png` (ERD 다이어그램 삽입)*  

📋 **핵심 테이블**
| 테이블 | 주요 컬럼 | 설명 |
|--------|------------|------|
| `member` | member_id, password, name, role, status | 사용자 정보 |
| `book` | book_id, title, author, rfid_uid, status | 도서 정보 |
| `rental` | rental_id, member_id, book_id, rent_date, return_date | 대여 내역 |
| `firebase_token` | member_id, token | FCM 푸시 알림 토큰 |

---

## 📡 API 설계 요약

| 기능 | Method | Endpoint | 설명 |
|------|---------|-----------|------|
| 로그인 | POST | `/api/login` | 사용자 인증 |
| 회원가입 | POST | `/api/member/signup` | 신규 사용자 등록 |
| 도서 조회 | GET | `/api/book/{id}` | 도서 상세 정보 반환 |
| 대여 | POST | `/api/rental` | 도서 대여 처리 |
| 반납 | PATCH | `/api/rental/{id}` | 반납 처리 |
| RFID 이벤트 | POST | `/rfid/events` | Arduino UID 수신 |

📷 *TODO: `/docs/api_example.png` (Postman 캡처 추가)*

---

## 🧭 시스템 동작 흐름

📷 *TODO: `/docs/system_flow.png` (전체 시퀀스 다이어그램 추가)*  

**동작 시나리오**
1. 사용자가 Android 앱에서 로그인  
2. 서버에서 검증 후 JSON 응답 반환  
3. RFID 리더기가 도서 태그 인식 → UID 서버 전송  
4. 서버가 해당 UID를 기반으로 대여/반납 처리  
5. 푸시 알림(Firebase)을 통해 결과 전송  

---

## 🧩 담당 역할 (백엔드 개발)

> 포트폴리오에서 가장 중요하게 평가되는 섹션입니다.

- Spring Boot 기반 **API 서버 설계 및 구현 전체 담당**  
- Spring Security 커스터마이징(FormLogin → JSON 인증 구조)  
- 도서/회원/대여 관리 비즈니스 로직 개발  
- MySQL 기반 ERD 설계 및 JPA 매핑  
- Arduino – 서버 REST 통신 테스트 및 안정화  
- AWS EC2 서버 배포 및 외부 접근 설정  

📷 *TODO: `/docs/code_snippet.png` (직접 작성한 코드 예시 추가)*

---

## 🔧 트러블슈팅

| 문제 | 원인 | 해결 |
|------|------|------|
| RFID 인식 불안정 | 전원 불안정(3.3V) | 배선 재정비 및 모듈 교체 |
| 로그인 실패 | JSON 필드 불일치 | DTO(`username`, `password`) 수정 |
| 403 Forbidden | 세션 미유지 | FormLogin 구조 커스터마이징으로 JSON 인증 처리 |
| DB 연결 오류 | 환경변수 누락 | `application.yml`에 `DB_PASSWORD` 환경 변수 설정 |

📷 *TODO: `/docs/error_logs.png` (콘솔 로그 캡처 추가)*

---

## 🧰 실행 방법

```bash
# 1️⃣ 프로젝트 클론
git clone https://github.com/USERNAME/smilebook-api.git

# 2️⃣ 환경변수 설정
export DB_PASSWORD=비밀번호

# 3️⃣ 서버 실행
./gradlew bootRun


기본 주소: http://3.39.9.175:8080
배포 환경: AWS EC2 (Ubuntu)

📷 TODO: /docs/run_server.png (Postman 실행 캡처 추가)

📱 연동 리포지토리
구성	리포지토리	설명
☁️ Backend (Spring Boot)	IoT Smart Library API
	메인 API 서버
📱 Android (App)	IoT Smart Library Android
	사용자 앱, Retrofit2 기반 통신
🔧 Arduino (RFID)	IoT Smart Library Arduino
	RFID 인식 모듈 (RC522)

📷 TODO: /docs/integration.png (전체 연동 구조도 추가)

📈 프로젝트 결과 및 시연

시연 영상, PPT, 발표 자료 등을 함께 첨부하면 신뢰도가 높아집니다.

📷 TODO: /docs/demo.gif 또는 /docs/presentation.pdf 추가

💡 프로젝트를 통해 배운 점

Spring Security 내부 구조(Filter → Provider → Handler)를 직접 구현하며 인증 로직의 원리를 이해

IoT 하드웨어와 서버 간 REST 통신 구조를 설계하며 네트워크 프로토콜 이해도 향상

서버·앱·하드웨어 통합 시스템을 설계하며 전체 아키텍처 구성 능력 강화

협업 중 Git 관리, 역할 분담, 코드 리뷰를 통해 실무형 협업 프로세스 경험

📬 Contact

👩‍💻 개발자: 홍소연
📧 Email: example@email.com

🌐 GitHub: https://github.com/USERNAME

💡 Tip:

README 상단에는 “프로젝트 시연 이미지”나 “팀 로고”를 넣으면 훨씬 눈에 띕니다.

문단마다 /docs/ 폴더에 다이어그램·ERD·화면캡처를 추가해 깔끔하게 정리하면 완벽한 포트폴리오 리드미가 됩니다.
