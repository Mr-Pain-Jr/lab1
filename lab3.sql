-- select one row
SELECT name FROM users;

--select two rows
SELECT name, phone FROM users;

--JOIN operand 
SELECT users.id, taco.tacoName
FROM users
JOIN tacos ON users.id=tacos.tacoName;

--4 
-- FULL JOIN returns all data from tables
SELECT users.id, tacos.tacoName
FROM users
FULL JOIN tacos ON users.id=tacos.tacoName;


--5
--select from tacos where price between 100 and 500
SELECT *
FROM tacos
WHERE tacoPrice > 100 AND tacoPrice < 500

--6
-- select taco by user
SELECT tacos t
     FROM users u 
        WHERE u.users_id = t.id
        
        
--7
-- top 10 expensive tacos
SELECT ТОР 10 tacos.tacoName, tacos.tacoPrice
FROM tacos
ORDER BY tacos.tacoPrice DESC;
        
        
       
--8
--BETWEEN operand 
  SELECT * 
    FROM tacos  
    WHERE tacoPrice  BETWEEN "20" AND "100";

--9
--compare 2 fields from 2 tables
SELECT tacoName, tacoDesc
FROM tacos
UNION ALL
SELECT name, phone
FROM users

--10 
--Всі записи з правої таблиці, якщо в правій таблиці немає відповідного рядка для лівої таблиці, то в відповідних рядках будуть пусті значення.
SELECT users.name, users.id, tacos.tacoName
FROM users
RIGHT JOIN tacos ON users.name=tacos.tacoName;
;
