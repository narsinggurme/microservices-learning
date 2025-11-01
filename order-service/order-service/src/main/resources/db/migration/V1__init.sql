CREATE TABLE tbl_orders (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    order_number VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT(11) NOT NULL,
    sku_code VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
