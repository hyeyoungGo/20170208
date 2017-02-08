-- Author Dao SQL 연습

-- 1. insert

insert into author values ( author_seq.nextval, '...', '...' );

select * from author;

rollback;

select no, name, decription from author;

delete from author where no = 1;