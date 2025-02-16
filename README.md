# 팀 프로젝트 'Shoots'용 READ ME

##### 홈페이지 링크
[goshoots.site/Shoots/main](https://goshoots.site/Shoots/main) 

## 사용한 언어 / 툴 + 협업도구 / 환경설정 등.

Spring Boot 3.4.1 (+ Spring Security , Thymeleaf) , Java17 , JavaScript , MySQL 8.0.4 , Redis 3.0.504 , Mybatis , Html , 

API {로그인: 카카오·네이버·구글 , 지도 , 날씨, 채팅 (WebSocket), 주소(=우편번호) } ,
Docker , AWS EC2 , Nginx , Reverse-Proxy

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

## 간략한 프로젝트 소개

### 홈페이지 메인 홈 / 로그인 창 {회원가입 , 로그인 (스프링 시큐리티 멀티로그인 + 소셜 로그인 API) , 아이디 + 비밀번호 찾기 (메일 발송 + 인증번호 확인) }

![Shoots](https://private-user-images.githubusercontent.com/184598098/413613772-6a2af389-f5e3-4490-91a9-192a9d56e743.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3MDk0NDQsIm5iZiI6MTczOTcwOTE0NCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjEzNzcyLTZhMmFmMzg5LWY1ZTMtNDQ5MC05MWE5LTE5MmE5ZDU2ZTc0My5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQxMjMyMjRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00YTNlMGU5ODQyYTAwMTZmNzU1OWYxZTk2NDE3MGUwN2E3OTM0NTVjZTBmZjNkYjg0YWYxODlhZmIxYWU5NjZmJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.INNyhLiFroRN6tbjwUfbMxwGmN1ELwepGG6s_LDGegM)

![Shoots](https://private-user-images.githubusercontent.com/184598098/413647438-04fe7f34-182d-4670-9b0b-2e6fd0722534.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3NDM4LTA0ZmU3ZjM0LTE4MmQtNDY3MC05YjBiLTJlNmZkMDcyMjUzNC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1jNjkzZTI4NGRiNThkNGQwNzVlMjk3YWM0ZDg3NTViZWQ4ZWFhZDM3Y2ExYjU4NmNhY2I4N2E0OWM0MjhjMDEzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.42fzMhmeo3pNS53UVYSHPj35DelEZ7FqRtpmtYp90oE)

### 홈페이지 내 일반 기능들 : 경기장 찾기 (지도), 매칭 경기 목록 , 매칭 상세 정보 , 공지사항 + FAQ , 게시판 + 게시판 상세 , 1:1 문의 게시판 (로그인 필요, 본인 문의건만.)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647523-4051fe74-3ad3-4b13-8544-9bcea8573cb2.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3NTIzLTQwNTFmZTc0LTNhZDMtNGIxMy04NTQ0LTliY2VhODU3M2NiMi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT02ZjBiMzY0YTYzZWI5NjMyNDYxNmUxNDc5MmM2N2I3YmZiNzU1MjA5ZmE0ODAzYWFjNGQwZjM3MzViMDUwZmVlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.__slLFj6yKTYoTHH1M9KdlEw9Fqebs2t58WWRI_z4ps)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647607-c462c2c1-7353-453e-bfe0-a2e4db16c23d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3NjA3LWM0NjJjMmMxLTczNTMtNDUzZS1iZmUwLWEyZTRkYjE2YzIzZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1iMjUyNTc5NTNhZmVlNzdkNWFiNTZmNDNiNDVkODhmOGE4M2Y1YzA5MzBkNjdjMDA1M2RiNTU3NmI1MTAzM2QwJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.qpZ-tetb8SUHyGwl_pwaKOzCYRQsPpK6oPbMNIHllbo)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647638-16bdab9a-5858-4f09-81f3-e34f56468c72.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3NjM4LTE2YmRhYjlhLTU4NTgtNGYwOS04MWYzLWUzNGY1NjQ2OGM3Mi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1lNWRmNWE3MDRiOWMxOGU2MWNmYjczYjRhMjZlOGQ0ZDY3OTBhMjQzODJjNTkwY2Q3ZTI5NWI4MDc4MThhODMxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.MgAJB9NXC4rJB-gWgLdx8YEYFLByesMFTx1DhXUKvfY)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647793-e7f0c2b4-983b-44e4-8ae4-0bfd2f777170.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3NzkzLWU3ZjBjMmI0LTk4M2ItNDRlNC04YWU0LTBiZmQyZjc3NzE3MC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xMjAzOTI0Y2M0YjhhNTZiMDhiMmRmNTA2OWE2NDJjNzBmYzIyMTc5OGUwYmZhZGFjODVlMjM0N2QwNWE2ZmYxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kSaL1NnpZ047zF1P1BenZ30WVfTAxJVSKC9_BDV2VQY)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647862-415c4844-62a4-4999-ab94-ecb5fb03fe5e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3ODYyLTQxNWM0ODQ0LTYyYTQtNDk5OS1hYjk0LWVjYjVmYjAzZmU1ZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kYWFlNmQzMjgwYmJlYzljY2M0NjFlNzg1M2ViNGU4YjA1NjljZjAzYmJkY2NkYzE4NWNjYWYxYTU0OWQxOTVhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.MislALBjSAo4x5upDWhQ7xudfb7W7r_En9Vx0AdIwnY)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647890-76e3c5f4-2fc2-48a1-9a7c-fd6fcb6c0301.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM2MDgsIm5iZiI6MTczOTc0MzMwOCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3ODkwLTc2ZTNjNWY0LTJmYzItNDhhMS05YTdjLWZkNmZjYjZjMDMwMS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjAxNDhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yZmI1ZWQ5NDM4NzBlOTJlMGNmMGJhMTNmNTRhYWYwMzUwNDQ2MzFjMGQzMGRlZjU0NjE2OWEwZjQ3NGIzYzk4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.oLg7WcEDkaqznmL1ckfEJ8csUxKABI3KMRoVJrsJeI4)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647911-c705adb1-817e-4b6c-aa7c-daaf036efb60.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM4NTAsIm5iZiI6MTczOTc0MzU1MCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3OTExLWM3MDVhZGIxLTgxN2UtNGI2Yy1hYTdjLWRhYWYwMzZlZmI2MC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjA1NTBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zY2MxM2I5YTA2YTc0YzE0Nzg3ZjcxODUwOTMzNWYxMDU5NGIxMGEzZDJlMWFjOGI4MjI2YmViMjU3OWQ2MDk1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.JI8p7izTirJ9U-ffnjmcY0omV6gH9BahMwY6Z3rI9EU)

### 기업회원이 사용 가능한 기능들 (기업회원 로그인 필요 + 기업 회원가입 이후 관리자의 승인 필요) : 대시보드, 등록해둔 매칭 정보 (본인이 등록한 경기들만), 매치고객 리스트 , 전체 고객 리스트 , 차단 유저 리스트 + 관리 , 1:1 문의 (본 기업것만) , 매치 확정 그래프
![Shoots](https://private-user-images.githubusercontent.com/184598098/413647941-e07197cd-b00a-4143-85ae-84a1dcc178ec.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDM4NTAsIm5iZiI6MTczOTc0MzU1MCwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ3OTQxLWUwNzE5N2NkLWIwMGEtNDE0My04NWFlLTg0YTFkY2MxNzhlYy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjA1NTBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zZDMyZTExYzBhMjc0ODQxMjQ2NDFjYmMzNDZmY2Q3OWYyZjAzNzc1MGNkMmQzZDUzYTM3YWU1Y2ZiODY0NjI4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.p82uD185ulpRmMkgkesgA2Q_eCHKflucvvm-xEsG72U)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648241-d44a8882-e556-4974-bce9-3ba41f01fdf9.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4MjQxLWQ0NGE4ODgyLWU1NTYtNDk3NC1iY2U5LTNiYTQxZjAxZmRmOS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05OWY3YzU2OWMzMGIyOTY0ZmY4MmUxMWUzYmZkMDVjMTFhMGYyM2Y3NTQ5YTZmODkyNGQ0ZGQ4NTUwNzQzYTMzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.FjxQJy85sdz9-Yj6SVsIccwV7VSjGGia0IViuF8mTUE)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648277-0d7840ec-f536-495b-9385-688b2dbc654d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4Mjc3LTBkNzg0MGVjLWY1MzYtNDk1Yi05Mzg1LTY4OGIyZGJjNjU0ZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01MGViNThjMjYxMWQ1N2E4NWM3OThlNDU3MmVhNDY4OGQ3OTg1NTEwYzIzMmEwZDRmY2RlZTE0N2FlZWI2NmMxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.rd7_ne8G6JXZPp0XS4Ao8XLG1qx8h081obWpjGmSRLU)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648330-87bc012c-4b06-4b21-bc20-8c5d894aa041.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4MzMwLTg3YmMwMTJjLTRiMDYtNGIyMS1iYzIwLThjNWQ4OTRhYTA0MS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yMzI2ZjdlZWZmMDMyMmNmZmU1OGI1Y2Q0NWQ5Yzc4NjA3NjE1OTM4OWRmYjAwNGYyMmE0YWIzMmZmYTY3OTI3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.44j6dkuGqRZq5p7oaZM785roenZ7BDwFMrvgJzl3EFQ)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648335-08fd270e-6dd7-42f4-a610-1456ccca1f37.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4MzM1LTA4ZmQyNzBlLTZkZDctNDJmNC1hNjEwLTE0NTZjY2NhMWYzNy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZjc1ZmE0MzI0OTMyNmVhYjhiM2I5ZWQ0NTkwNWNmNjVhMDIyNmZmNjA2YmE1ZDFjMjEyM2U3ZWM4OTNlNGFjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kWBj6LPQOAygNJUTVAe-EuykOc9-2az-Fd1E5GEOTak)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648351-8927331b-d00e-47eb-90ba-5ba53f20507c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4MzUxLTg5MjczMzFiLWQwMGUtNDdlYi05MGJhLTViYTUzZjIwNTA3Yy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yY2M3NWViNmQwZjA4NDYxMTBkMjkwZjA1Yzk1MDcxNzdiMWI0NjBlMTQwM2U4MzczMDUyODIxYzQ0MjhmZTBkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.U011DWMdsktxUb9z00nIpxD6xpkqIP7S8CzljxcALJg)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648362-dcf0cd48-1cac-41d6-924e-7b1250b4414c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQxMDEsIm5iZiI6MTczOTc0MzgwMSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4MzYyLWRjZjBjZDQ4LTFjYWMtNDFkNi05MjRlLTdiMTI1MGI0NDE0Yy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjEwMDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00NzgwZDRlMGRhNWI4ZjliMzhlOGUyNDE4MjkwMGFjMDhhNjQ5YjZkYjRmM2QwZDAzYmEwNDBhNzI2OThlMGQ1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.gRQFXsB4cZhqAM4ctym03pFdvRkY1-k5aY_-1AKsFkY)


### 관리자 페이지 (관리자 권한 지닌 개인회원 로그인 필요) : 대시보드, 전체 유저 리스트 + 관리 , 전체 기업 리스트 + 관리 , 전체 게시글 리스트 + 관리 , 전체 문의 리스트 + 관리 , 전체 신고 리스트 + 관리

![Shoots](https://private-user-images.githubusercontent.com/184598098/413648572-97b2cfc4-a1de-4917-951f-7d829bdf5e85.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NTcyLTk3YjJjZmM0LWExZGUtNDkxNy05NTFmLTdkODI5YmRmNWU4NS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1lNzRmOWI1ZmRjNzg0ZjNhOTA5MjA2NGQyM2I3ZGE1MWM3MmEwZTdkOWMxZjhmODBiMzM3NzE5ODE5YWVmOGJjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.oCUBkyu734H3BJsU6g-yRoE4JzbxDGQcDWN6_jY7K7s)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648589-5cf9c118-158f-4e5d-b9a0-9d288d42ef09.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NTg5LTVjZjljMTE4LTE1OGYtNGU1ZC1iOWEwLTlkMjg4ZDQyZWYwOS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZTUyOTU0OGE3M2ViNmZkOWNjNzdmMzE5ODQyMWY1OTZhZmY1ZDQ3OWRjMGQyMGViMzZlNzc5MjAwY2ZmYjE1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.nZpFf2eA1FKMvWGDQUVyG4P_zhGpWE8jsQzPHYVA4pE)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648606-54d89b0b-ae8a-4259-9906-8f2391b2b577.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NjA2LTU0ZDg5YjBiLWFlOGEtNDI1OS05OTA2LThmMjM5MWIyYjU3Ny5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kNmE0ZTkzMmM4Zjc2NGI3OTFmYTVjZGI1ZTJjNzBhN2U4NTFiZDhkMGVjM2NkODZmZDAwMjIwYjQyZmUzYzAxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.gimaeL7ak4Oa20Mxh9ke_lyVKYqb7qJ_NX5sMAb71vI)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648621-7acf527a-ec85-4b28-8c58-62f1ba4145d5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NjIxLTdhY2Y1MjdhLWVjODUtNGIyOC04YzU4LTYyZjFiYTQxNDVkNS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNzM5MWM3ZTBjNjk5NDcxNDY0OGNhZGU2ZTM0Yzg0MWIwYzJkYzZmOWFiMDc4MTdmMTQxM2VkMjk1NmU1MGJlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.AgkYsRDdq06ckWrxVH218WpsoQc3CxjgKwVJTgPNAek)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648643-7be02c2e-a9f4-48ca-b67b-531bf825cf76.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NjQzLTdiZTAyYzJlLWE5ZjQtNDhjYS1iNjdiLTUzMWJmODI1Y2Y3Ni5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yZDMzYTZmMTIyOTAwMWU5NTQwZTEyMGM0MzRlMDk2YmY0ZDNmYzVkMmRjMzc1MjllMDRkOWM1MmM2NjI2MjBhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.BvEHEhvAqigwN6kG2U5Skwpt8XeBhgFocFAYLGyxnuQ)
![Shoots](https://private-user-images.githubusercontent.com/184598098/413648648-7e4631e0-36c0-47f2-aa2d-e7b228aeca14.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mzk3NDQzNjUsIm5iZiI6MTczOTc0NDA2NSwicGF0aCI6Ii8xODQ1OTgwOTgvNDEzNjQ4NjQ4LTdlNDYzMWUwLTM2YzAtNDdmMi1hYTJkLWU3YjIyOGFlY2ExNC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjE2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxNlQyMjE0MjVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT02NDJjOGQ1ZjhiNTUzOGZjNzQwODkyYTZiYWMwMmVhYWQ5ZjRhMGQyOWMwYWZhNzdjY2ZkYWRjMjc1ZGE0OTc2JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kPksQmXEGd0HmmLyFWAs-uTeWtGHJ_hL9O3gNFBSU18)



