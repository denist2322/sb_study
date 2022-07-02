DROP DATABASE IF EXISTS testaa;
CREATE DATABASE testaa;
USE testaa;

CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    title CHAR(50) NOT NULL,
    `body` TEXT NOT NULL
);

INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = "제목1",
`body` = "내용1";

INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = "제목2",
`body` = "내용2";

INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = "제목3",
`body` = "내용3";

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED NOT NULL DEFAULT 3 COMMENT '권한레벨(3=사용자, 7=관리자)',
    `name` CHAR(20) NOT NULL,
    nickname CHAR(20) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    email CHAR(20) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(탈퇴 전=0, 탈퇴 후=1)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

INSERT INTO `member` SET
    regDate = NOW(),
    updateDate = NOW(),
    loginId = "admin",
    loginPw = "admin",
    authLevel = 7,
    `name` = "관리자이다",
    nickname = "관리자",
    cellphoneNo = "010-3443-2211",
    email = "test@com";

INSERT INTO `member` SET
    regDate = NOW(),
    updateDate = NOW(),
    loginId = "usr1",
    loginPw = "usr1",
    `name` = "유저이다",
    nickname = "유저1",
    cellphoneNo = "010-3222-3311",
    email = "test@com";

INSERT INTO `member` SET
    regDate = NOW(),
    updateDate = NOW(),
    loginId = "usr2",
    loginPw = "usr2",
    `name` = "유저이다",
    nickname = "유저2",
    cellphoneNo = "010-8888-3311",
    email = "test@com";

SELECT * FROM `member`;
SELECT LAST_INSERT_ID();