DROP SCHEMA IF EXISTS homework3;
CREATE SCHEMA homework3;
use homework3;

SELECT employee_count PERCENTILE_CONT(0.5) FROM account AS median_employee_count;