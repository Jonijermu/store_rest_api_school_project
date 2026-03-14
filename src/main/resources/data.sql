-- Step 1: Assign ~90% of customers to PrivateCustomer
INSERT INTO private_customer (id)
SELECT c.id
FROM customers c
WHERE RAND() < 0.9
  AND c.id NOT IN (SELECT id FROM private_customer)
  AND c.id NOT IN (SELECT id FROM company_customer);

-- Step 2: Assign the remaining to CompanyCustomer
INSERT INTO company_customer (id, company_name, billing_email)
SELECT c.id,
       CONCAT('Company_', c.id),
       CONCAT('billing_', c.id, '@example.com')
FROM customers c
WHERE c.id NOT IN (SELECT id FROM private_customer)
  AND c.id NOT IN (SELECT id FROM company_customer);

-- Step 3: Update CustomerAddress to link properly
UPDATE customeraddresses ca
    JOIN customers c ON ca.customer_id = c.id
    SET ca.customer_id = c.id;