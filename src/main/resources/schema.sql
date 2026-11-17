-- PostgreSQL Schema for Dukaansarthi AI Supplier Bill Scanning & Inventory Management

CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    gstin VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
    product_id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(100),
    barcode VARCHAR(100) UNIQUE,
    hsn_code VARCHAR(50),
    category VARCHAR(100),
    unit VARCHAR(50) DEFAULT 'PCS',
    purchase_price DECIMAL(10, 2),
    stock_quantity DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS purchases (
    purchase_id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    supplier_id BIGINT REFERENCES suppliers(supplier_id),
    invoice_number VARCHAR(100) NOT NULL,
    invoice_date DATE,
    subtotal DECIMAL(12, 2),
    tax_total DECIMAL(12, 2),
    grand_total DECIMAL(12, 2),
    status VARCHAR(50) DEFAULT 'CONFIRMED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS purchase_items (
    item_id SERIAL PRIMARY KEY,
    purchase_id BIGINT REFERENCES purchases(purchase_id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES products(product_id),
    quantity DECIMAL(10, 2) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    discount_amount DECIMAL(10, 2) DEFAULT 0.00,
    tax_rate DECIMAL(5, 2),
    tax_amount DECIMAL(10, 2),
    line_total DECIMAL(12, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS ai_bill_scans (
    scan_id SERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    file_name VARCHAR(255),
    status VARCHAR(50),
    extracted_json TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
