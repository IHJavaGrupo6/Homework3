DROP SCHEMA IF EXISTS homework3;
CREATE SCHEMA homework3;
use homework3;
SELECT AVG(employee_count) FROM account;
SELECT MAX(employee_count) from account;
SELECT MIN(employee_count) from account;


SELECT AVG(opportunity.id), account_id from opportunity join account on opportunity.account_id = account.id group by account.id;
SELECT MAX(opportunity.id), account_id from opportunity join account on opportunity.account_id = account.id group by account.id;
SELECT MIN(opportunity.id), account_id from opportunity join account on opportunity.account_id = account.id group by account.id;
SELECT AVG(opportunity.account_id) FROM opportunity GROUP BY opportunity.account_id;