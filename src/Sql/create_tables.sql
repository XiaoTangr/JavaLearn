-- 用户表
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_name VARCHAR(225) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- 车辆基表
CREATE TABLE vehicles (
    vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type VARCHAR(50) NOT NULL, -- 区分不同类型的车辆（passenger, commercial）
    vehicle_brand VARCHAR(255),
    vehicle_model VARCHAR(255),
    vehicle_price DECIMAL(10, 2) NOT NULL,
    vehicle_stock INT NOT NULL DEFAULT 0
);

-- 乘用车表（继承自vehicles）
CREATE TABLE passenger_vehicles (
    vehicle_id BIGINT PRIMARY KEY,
    seat_count INT NOT NULL, -- 座位数
    fuel_type VARCHAR(50) NOT NULL, -- 燃油类型（汽油、柴油、电动、混合动力等）
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

-- 商用车表（继承自vehicles）
CREATE TABLE commercial_vehicles (
    vehicle_id BIGINT PRIMARY KEY,
    load_capacity DECIMAL(10, 2) NOT NULL, -- 载重量（吨）
    cargo_volume DECIMAL(10, 2) NOT NULL, -- 车厢容积（立方米）
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

-- 订单表
CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    buy_count INT NOT NULL,
    total_price DECIMAL(10, 2),
    create_time BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);