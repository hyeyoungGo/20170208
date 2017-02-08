-- 성 OR 이름에 en이 들어가는 사람

select first_name || ' ' || last_name as "NAME",
	   email,
	   phone_number,
	   to_char(hire_date, 'yyyy-mm-dd') as "HIRE_DATE" 
	from employees
	where first_name like '%en%'
	and last_name like '%en%';
	
-- minSalary ~ maxSalary
select first_name || ' ' || last_name,
	   salary
	from employees
	where 10000 < salary
	and 1000000 > salary;
	
select * from author;
	
delete from book;

select * from book;