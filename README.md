# 팀 프로젝트 'Shoots'용 READ ME

##### 홈페이지 링크
[goshoots.site/Shoots/main](https://goshoots.site/Shoots/main) 

## 사용한 언어 / 툴 + 협업도구 / 환경설정 등.

Spring Boot 3.4.1 (+ Spring Security , Thymeleaf) , Java (jdk17) , JavaScript , MySQL 8.0.4 , Redis 3.0.504 , Mybatis , Html , 

API {로그인: 카카오·네이버·구글 , 지도 , 날씨, 채팅 (WebSocket), 주소(=우편번호) } ,
Docker , AWS EC2 , Nginx (Reverse-Proxy 사용)

![used](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![used](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![used](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![used](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![used](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![used](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![used](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![used](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![used](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![used](https://img.shields.io/badge/redis-%23DD0031.svg?&style=for-the-badge&logo=redis&logoColor=white)
![used](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

<br>

## 프로젝트 배포
AWS EC2 + Docker + Jenkins (+ GitWebHook) 사용.

AWS에서 고정 IP 등록 후 가비아 에서 도메인 구매 후 주소 등록 (Nginx, Reverse Proxy 사용)
##### ↓ 아래는 젠킨스 파이프라인 코드
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/0.png?raw=true)


## 간략한 프로젝트 소개

### 홈페이지 메인 홈 / 로그인 창 {회원가입 , 로그인 (스프링 시큐리티 멀티로그인 + 소셜 로그인 API) , 아이디 + 비밀번호 찾기 (메일 발송 + 인증번호 확인) }

![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/1.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/2.png?raw=true)

### 홈페이지 내 일반 기능들 : 경기장 찾기 (지도), 매칭 경기 목록 , 매칭 상세 정보 , 공지사항 + FAQ , 게시판 + 게시판 상세 , 1:1 문의 게시판 (로그인 필요, 본인 문의건만.)

![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/3.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/4.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/5.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/6.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/7.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/8.png?raw=true)

### 기업회원이 사용 가능한 기능들 (기업회원 로그인 필요 + 기업 회원가입 이후 관리자의 승인 필요) : 대시보드, 등록해둔 매칭 정보 (본인이 등록한 경기들만), 매치고객 리스트 , 전체 고객 리스트 , 차단 유저 리스트 + 관리 , 1:1 문의 (본 기업것만) , 매치 확정 그래프

![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/9.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/10.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/11.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/12.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/13.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/14.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/15.png?raw=true)

### 관리자 페이지 (관리자 권한 지닌 개인회원 로그인 필요) : 대시보드, 전체 유저 리스트 + 관리 , 전체 기업 리스트 + 관리 , 전체 게시글 리스트 + 관리 , 전체 문의 리스트 + 관리 , 전체 신고 리스트 + 관리

![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/16.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/17.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/18.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/19.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/20.png?raw=true)
![Shoots](https://github.com/youngsoo0206/ShootsComplete/blob/main/readmeImage/21.png?raw=true)




