<h1>Redis를 이용한 토큰 갱신</h1>
<h2>1. 개요</h2>

- 프론트 페이지 접속시 cookie에 ip 저장
- 로그인시 Redis에 유저의 정보와 cookie에 저장된 ip를 혼합한 key값을 가진 refresh-token을 저장
- 로그인시 Access-Token을 cookie에 저장
- 로그인 후 호출해야 하는 api 호출시 Access-Token 검사 (Access-Token의 시간 주기는 1분)
- 로그인 후 호출해야 하는 api 호출시 Access-Token이 만료 되었으면 유저의 정보와 cookie에 저장된 ip를 혼합한 key로 Redis에서 Refresh-Token 검색
- Refresh-Token이 정상적일 때 Access-Token을 cookie에 저장 (Access-Token 갱신)

<h2>2. 설정 정보</h2>

- DB: MySql
- Redis
- Java Version: 17
- Spring Boot Version: 3.1.1
- front: react

<h2>3. React 폴더</h2>

![image](https://github.com/dlqudals12/redisToken/assets/22268579/42acd8ab-e26b-40c0-9e0b-22e48b1a87fc)

- filePath: /redisToken/front

- 실행 방법

```
npm i
npm start
```

<h2>4. </h2>
