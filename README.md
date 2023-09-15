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

<h2>4. Sequence Diagram</h2>

- 로그인 Sequence

![Redis_Login drawio](https://github.com/dlqudals12/redisToken/assets/22268579/0d0ea616-7e54-49a4-b927-4f3db4fe1b90)

- 로그인 이후 Token Filter

![Token_Filter drawio](https://github.com/dlqudals12/redisToken/assets/22268579/26ce631c-c30f-4934-a814-5ce89e8c2699)

<h2>영상</h2>

![React App - Chrome 2023-09-16 02-31-52](https://github.com/dlqudals12/redisToken/assets/22268579/04116c56-c2a6-4784-8830-a9fd2ebaad04)


