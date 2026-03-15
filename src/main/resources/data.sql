-- Assign ~90% of customers to PrivateCustomer
INSERT INTO private_customer (id)
SELECT c.id
FROM customers c
WHERE RAND() < 0.9
  AND c.id NOT IN (SELECT id FROM private_customer)
  AND c.id NOT IN (SELECT id FROM company_customer);

-- Assign the remaining to CompanyCustomer
INSERT INTO company_customer (id, company_name, billing_email)
SELECT c.id,
       CONCAT('Company_', c.id),
       CONCAT('billing_', c.id, '@example.com')
FROM customers c
WHERE c.id NOT IN (SELECT id FROM private_customer)
  AND c.id NOT IN (SELECT id FROM company_customer);

-- Update CustomerAddress to link properly
UPDATE customeraddresses ca
    JOIN customers c ON ca.customer_id = c.id
    SET ca.customer_id = c.id;

-- Update locked in all products to n
UPDATE products p SET locked = 'n';

-- Update the versioning to 0 of all products
UPDATE products p SET VERSION = 0 WHERE p.version is Null;

-- Randomize the product categories
INSERT INTO product_category (product_id, category_id)
SELECT p.id,
       (SELECT id FROM productcategories ORDER BY RAND() LIMIT 1)
FROM products p
WHERE RAND() < 0.7;

INSERT INTO product_category (product_id, category_id)
SELECT p.id,
       (SELECT id FROM productcategories ORDER BY RAND() LIMIT 1)
FROM products p
WHERE p.id NOT IN (SELECT product_id FROM product_category);

INSERT INTO product_category (product_id, category_id)
SELECT pc.product_id,
       (SELECT id FROM productcategories ORDER BY RAND() LIMIT 1)
FROM product_category pc
GROUP BY pc.product_id
HAVING RAND() >= 0.7;