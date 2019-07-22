SELECT S_NAME FROM SALESREP;
SELECT INV_NUM,	INV_DATE, CUS_ID FROM INVOICE;
SELECT CUS_ID, CUS_NAME FROM CUSTOMER;
SELECT CAR_NAME, CAR_MODEL, CAR_PRICE FROM CAR;
SELECT CAR_NAME, CAR_MODEL, CAR_PRICE-500 AS DISCOUNT FROM CAR;
SELECT CAR_NAME, CAR_MODEL, (CAR_PRICE-500)/2*2 AS DISCOUNT FROM CAR;
SELECT CAR_NUM AS IDNUM, CAR_NAME AS TITLE, CAR_MODEL AS MODEL, CAR_YEAR AS YEAR, CAR_PRICE AS PRICE FROM CAR;
SELECT S_NAME||' lives at '||S_ADDRESS FROM SALESREP;
SELECT CAR_NAME||' list price is '|| CAR_PRICE AS STICKER FROM CAR;
SELECT DISTINCT S_ID FROM INVOICE;
/*11*/UPDATE INVOICE
  SET INV_TOTAL=520026.98*1.075WHERE CAR_NUM=12;
UPDATE INVOICE
  SET INV_TOTAL=620026.98*1.075WHERE CAR_NUM=13;
UPDATE INVOICE
  SET INV_TOTAL=420026.98*1.075WHERE CAR_NUM=14;
UPDATE INVOICE
  SET INV_TOTAL=3.98*1.075WHERE CAR_NUM=15;
UPDATE INVOICE
  SET INV_TOTAL=650026.98*1.075WHERE CAR_NUM=16;
SELECT * FROM INVOICE;
/*12*/UPDATE INVOICE
  SET INV_TOTAL=0 WHERE CAR_NUM=12;
UPDATE INVOICE
  SET INV_TOTAL=0 WHERE CAR_NUM=13;
UPDATE INVOICE
  SET INV_TOTAL=0 WHERE CAR_NUM=14;
UPDATE INVOICE
  SET INV_TOTAL=0 WHERE CAR_NUM=15;
UPDATE INVOICE
  SET INV_TOTAL=0 WHERE CAR_NUM=16;
SELECT * FROM INVOICE;
UPDATE INVOICE
  SET INV_TOTAL=(SELECT CAR_PRICE*1.075 FROM CAR WHERE CAR.CAR_NUM=INVOICE.CAR_NUM);
SELECT * FROM INVOICE;