SELECT b.*, m.* FROM board b JOIN BOARD_COMMENT bc ON board_no=board_ref JOIN MEMBER M ON userid=BOARD_WRITER  where board_no=2;
SELECT B.*, M.* FROM BOARD B JOIN BOARD_COMMENT BC ON BOARD_NO=BOARD_REF JOIN MEMBER M ON USERID=BOARD_WRITER WHERE BOARD_NO=2;
SELECT * FROM BOARD B JOIN BOARD_COMMENT BC ON BOARD_NO=BOARD_REF JOIN MEMBER M ON M.USERID=BOARD_WRITER WHERE BOARD_NO=2;
SELECT * FROM MEMBER JOIN BOARD ON USERID=BOARD_WRITER JOIN BOARD_COMMENT BC ON BOARD_NO=BOARD_REF WHERE BOARD_NO=2;


SELECT * FROM BOARD B JOIN BOARD_COMMENT ON BOARD_NO=BOARD_REF JOIN MEMBER ON BOARD_WRITER=USERID WHERE BOARD_NO=2;
SELECT * FROM board;
SELECT * FROM MEMBER;