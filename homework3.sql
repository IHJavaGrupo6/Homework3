DROP SCHEMA IF EXISTS homework3;
CREATE SCHEMA homework3;
use homework3;

SELECT AVG(employee_count) as median FROM(SELECT employee_count FROM account as acc ORDER BY acc.employee_count) as query1 WHERE (SELECT @id = @id + 1) BETWEEN acc.employee_count/2.0 and acc.employee_count/2.0 + 1;