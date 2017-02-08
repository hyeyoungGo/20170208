-- book sequence
drop sequence BOOK_SEQ;

create sequence book_seq
start with 1
increment by 1
maxvalue 9999999999;

select book_seq.nextval from dual;

-- author sequence
drop sequence author_seq;

create sequence author_seq
start with 1
increment by 1
maxvalue 9999999999;

select author_seq.nextval from dual;

commit;
