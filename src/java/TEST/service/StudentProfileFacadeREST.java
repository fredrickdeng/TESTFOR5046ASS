create or replace TRIGGER MEMBERSHIP_UPGRADE
AFTER UPDATE ON CUSTOMER 

FOR EACH ROW

/*DECLARE
  Number_exist Number;
*/

DECLARE
    x MEMBERSHIP.MINIMUM_CREDITS%TYPE;
    y CUSTOMER.MEMBERSHIP_TIER%TYPE;

BEGIN
  
--SELECT COUNT(*) INTO Number_Exist FROM PAYMENT WHERE BOOKING_ID = :new.BOOKING_ID;
/*IF(Number_Exist != 1)
THEN*/

    SELECT MAX(MINIMUM_CREDITS) into x 
    FROM MEMBERSHIP 
    WHERE MINIMUM_CREDITS <= :new.MEMBERSHIP_CREDIT;
    
    SELECT MEMBERSHIP_TIER into y 
    FROM MEMBERSHIP 
    WHERE MINIMUM_CREDITS = x;
    
    UPDATE CUSTOMER
    SET MEMBERSHIP_TIER = y
    WHERE CUSTOMER_ID = :new.CUSTOMER_ID;
--END IF;
  --NULL;
END;