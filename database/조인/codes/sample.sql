CREATE DATABASE test-db default CHARACTER SET UTF8;

CREATE TABLE team
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT,
    name VARCHAR(32) NOT NULL,
    member_count INT DEFAULT 0,
    debut_date  DATE
) ENGINE=INNODB;

INSERT INTO team VALUES(1, "뉴진스", 5, "2022-07-22");
INSERT INTO team VALUES(2, "르세라핌", 5, "2022-05-02");
INSERT INTO team VALUES(3, "아이브", 6, "2021-12-01");
INSERT INTO team VALUES(4, "(여자)아이들", 5, "2018-05-02");
INSERT INTO team VALUES(5, "에스파", 4, "2020-11-17");

CREATE TABLE member
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    age INT DEFAULT 0,
    birthday  DATE,
    group_id INT
)

# 뉴진스
INSERT INTO member VALUES("하니", 19, "2004-10-06", 1);
INSERT INTO member VALUES("혜인", 15, "2008-04-21", 1);
INSERT INTO member VALUES("다니엘", 18, "2005-04-11", 1);
INSERT INTO member VALUES("해린", 17, "2006-05-15", 1);
INSERT INTO member VALUES("민지", 19, "2004-05-07", 1);

# 르세라핌
INSERT INTO member VALUES("사쿠라", 25, "1998-03-19", 2);
INSERT INTO member VALUES("김채원", 23, "2000-08-01", 2);
INSERT INTO member VALUES("허윤진", 22, "2001-10-08", 2);
INSERT INTO member VALUES("카즈하", 20, "2003-08-09", 2);
INSERT INTO member VALUES("홍은채", 17, "2006-11-10", 2);

# 아이브
INSERT INTO member VALUES("안유진", 20, "2003-09-01", 3);
INSERT INTO member VALUES("가을", 21, "2002-09-24", 3);
INSERT INTO member VALUES("레이", 19, "2004-02-03", 3);
INSERT INTO member VALUES("장원영", 19, "2004-08-31", 3);
INSERT INTO member VALUES("리즈", 19, "2004-11-21", 3);
INSERT INTO member VALUES("이서", 16, "2007-02-21", 3);

# (여자) 아이들
INSERT INTO member VALUES("미연", 26, "1997-01-31", 4);
INSERT INTO member VALUES("민니", 25, "1997-10-23", 4);
INSERT INTO member VALUES("소연", 24, "1998-08-26", 4);
INSERT INTO member VALUES("우기", 23, "1999-09-23", 4);
INSERT INTO member VALUES("슈화", 23, "2000-01-06", 4);

# 에스파
INSERT INTO member VALUES("카리나", 23, "2000-04-11", 5);
INSERT INTO member VALUES("지젤", 22, "2000-10-30", 5);
INSERT INTO member VALUES("윈터", 22, "2001-01-01", 5);
INSERT INTO member VALUES("닝닝", 20, "2002-10-23", 5);