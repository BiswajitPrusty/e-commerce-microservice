
-- Products for Electronics
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'A variety of electronics including phones, laptops, and accessories.', 'Electronics');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'iPhone 15 with 128GB storage, 6.1-inch display, and 12MP camera.', 'iPhone 15', 200, 999.99, currval('category_seq')),
    (nextval('product_seq'), 'MacBook Pro with Apple M2 chip, 16GB RAM, and 512GB SSD.', 'MacBook Pro M2', 150, 1999.99, currval('category_seq')),
    (nextval('product_seq'), 'Samsung Galaxy S23 with 256GB storage and 120Hz display.', 'Samsung Galaxy S23', 300, 899.99, currval('category_seq')),
    (nextval('product_seq'), 'Bose QuietComfort 45 wireless noise-cancelling headphones.', 'Bose QC45 Headphones', 180, 329.99, currval('category_seq')),
    (nextval('product_seq'), 'Sony PlayStation 5 Console with one controller and HDMI cable.', 'PlayStation 5', 500, 499.99, currval('category_seq'));

-- Products for Apparel

INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Clothing and accessories for men, women, and children.', 'Apparel');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Slim-fit denim jeans for men with stretch fabric, available in sizes 30-36.', 'Mens Slim-fit Denim Jeans', 400, 39.99, currval('category_seq')),
  (nextval('product_seq'), 'Casual sundress for women, made from breathable cotton fabric, available in multiple sizes.', 'Womens Casual Sundress', 300, 49.99, currval('category_seq')),
    (nextval('product_seq'), 'Running shoes for men with memory foam insole and breathable mesh.', 'Mens Running Shoes', 250, 89.99, currval('category_seq')),
  (nextval('product_seq'), 'Leather handbag for women with gold-tone hardware and adjustable straps.', 'Womens Leather Handbag', 150, 129.99, currval('category_seq')),
    (nextval('product_seq'), 'Cotton T-shirt with a graphic print, unisex, available in various colors.', 'Graphic T-shirt', 500, 19.99, currval('category_seq'));

-- Products for Home Appliances
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Home appliances such as refrigerators, washing machines, and microwaves.', 'Home Appliances');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Samsung 25 cu. ft. French Door Refrigerator with Family Hub touchscreen.', 'Samsung French Door Refrigerator', 80, 2299.99, currval('category_seq')),
    (nextval('product_seq'), 'Whirlpool 5.3 cu. ft. Smart Front Load Washer with 36 wash cycles.', 'Whirlpool Smart Washer', 120, 799.99, currval('category_seq')),
    (nextval('product_seq'), 'Dyson V15 Detect Cordless Vacuum Cleaner with laser illumination for hidden dirt.', 'Dyson V15 Vacuum', 90, 699.99, currval('category_seq')),
    (nextval('product_seq'), 'GE Profile 30-inch Built-In Microwave Oven with air fry and convection features.', 'GE Profile Microwave Oven', 150, 499.99, currval('category_seq')),
    (nextval('product_seq'), 'Keurig K-Elite Single Serve Coffee Maker with iced coffee option.', 'Keurig K-Elite Coffee Maker', 300, 129.99, currval('category_seq'));

-- Products for Beauty & Personal Care
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Beauty and personal care products including skincare, haircare, and makeup.', 'Beauty & Personal Care');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Oréal Paris Revitalift Anti-Aging Day Cream with SPF 25.', 'Revitalift Anti-Aging Cream', 500, 19.99, currval('category_seq')),
  (nextval('product_seq'), 'Neutrogena Hydro Boost Water Gel Moisturizer for dry skin.', 'Neutrogena Hydro Boost Moisturizer', 400, 24.99, currval('category_seq')),
  (nextval('product_seq'), 'Dyson Supersonic Hair Dryer with intelligent heat control for shiny hair.', 'Dyson Supersonic Hair Dryer', 120, 399.99, currval('category_seq')),
  (nextval('product_seq'), 'Maybelline New York Superstay Matte Ink Liquid Lipstick in multiple shades.', 'Maybelline Matte Ink Lipstick', 250, 9.99, currval('category_seq')),
  (nextval('product_seq'), 'Clinique Moisture Surge 72-Hour Auto-Replenishing Hydrator Gel Cream.', 'Clinique Moisture Surge Gel', 350, 39.99, currval('category_seq'));

-- Products for Sports & Outdoors
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Sports equipment and gear for outdoor activities, fitness, and team sports.', 'Sports & Outdoors');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
  (nextval('product_seq'), 'Nike Air Zoom Pegasus 39 running shoes with responsive cushioning.', 'Nike Air Zoom Pegasus 39', 400, 119.99, currval('category_seq')),
  (nextval('product_seq'), 'Yeti Tundra 45 Cooler, rotomolded for durability, keeps ice for up to 5 days.', 'Yeti Tundra 45 Cooler', 100, 299.99, currval('category_seq')),
  (nextval('product_seq'), 'Fitbit Charge 5 Fitness and Health Tracker with GPS and heart rate monitor.', 'Fitbit Charge 5', 150, 179.99, currval('category_seq')),
  (nextval('product_seq'), 'Coleman RoadTrip 225 Portable Propane Grill with 2 burners.', 'Coleman RoadTrip Grill', 80, 249.99, currval('category_seq')),
  (nextval('product_seq'), 'Under Armour Mens HeatGear Armour Compression Shorts, moisture-wicking fabric.', 'Under Armour Compression Shorts', 500, 24.99, currval('category_seq'));
