#MySQL + JSP연결 프로젝트
### 테이블 생성
```sql
CREATE table users(
	id int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    email varchar(50)
);
INSERT INTO users(username, password, email) VALUES('ssar', '1234', 'ssar@nate.com');
INSERT INTO users(username, password, email) VALUES('cos', '1234', 'cos@nate.com');
```