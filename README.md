# 스프링부트 로그인 시스템!!

### 1. 의존성
- devtools
- spring web(mvc)
- lombok
- jpa
- mariadb
- security
- validation

### 2. 기능
- 로그인(시큐리티 혹은 인터셉터를 사용)
- 회원가입
- 회원정보수정
- 회원탈퇴

### 3. 모델링
```sql
User
id
username
password(암호화)
address(도로주소명 API사용)
```

### 4. 도로명주소 API
- https://www.juso.go.kr/
