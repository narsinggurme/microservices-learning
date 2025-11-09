CREATE TABLE IF NOT EXISTS tbl_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(255),
    sku_code VARCHAR(255),
    quantity INT,
    price DECIMAL(19,2)
);