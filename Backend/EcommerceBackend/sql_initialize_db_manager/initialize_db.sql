INSERT INTO roles(id,name) VALUES(1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES(2,'ROLE_ADMIN');
ALTER SEQUENCE roles_id_seq RESTART WITH 3;
INSERT INTO login_user(id, username, email, password, phone, firstname, lastname, isbuyer, isseller) VALUES (1, 'username', 'market.mate.ecommerce@gmail.com', '$2a$10$9cOQijrPNVgUeMzqnSI1PezrYpZ07TwoSnrtxJWK/PSz9jSxAJt8a', 3467867981, 'firstname', 'lastname', true, true);
ALTER SEQUENCE login_user_id_seq RESTART WITH 2;
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO product_category(id,category_name) VALUES(0,'art and wall decor');
INSERT INTO product_category(id,category_name) VALUES(1,'arts and crafts');
INSERT INTO product_category(id,category_name) VALUES(2,'automotive and accessories');
INSERT INTO product_category(id,category_name) VALUES(3,'automotive parts and accessories');
INSERT INTO product_category(id,category_name) VALUES(4,'baby and kids');
INSERT INTO product_category(id,category_name) VALUES(5,'baby gear and accessories');
INSERT INTO product_category(id,category_name) VALUES(6,'beauty and personal care');
INSERT INTO product_category(id,category_name) VALUES(7,'beverages');
INSERT INTO product_category(id,category_name) VALUES(8,'board games and puzzles');
INSERT INTO product_category(id,category_name) VALUES(9,'books and stationery');
INSERT INTO product_category(id,category_name) VALUES(10,'collectibles and memorabilia');
INSERT INTO product_category(id,category_name) VALUES(11,'computer hardware and software');
INSERT INTO product_category(id,category_name) VALUES(12,'craft supplies');
INSERT INTO product_category(id,category_name) VALUES(13,'electronics accessories');
INSERT INTO product_category(id,category_name) VALUES(14,'electronics and gadgets');
INSERT INTO product_category(id,category_name) VALUES(15,'fashion and apparel');
INSERT INTO product_category(id,category_name) VALUES(16,'fitness equipment and accessories');
INSERT INTO product_category(id,category_name) VALUES(17,'food');
INSERT INTO product_category(id,category_name) VALUES(18,'furniture and home decor');
INSERT INTO product_category(id,category_name) VALUES(19,'gaming and accessories');
INSERT INTO product_category(id,category_name) VALUES(20,'gardening and outdoor living');
INSERT INTO product_category(id,category_name) VALUES(21,'handbags and wallets');
INSERT INTO product_category(id,category_name) VALUES(22,'health and fitness');
INSERT INTO product_category(id,category_name) VALUES(23,'hobbies and collectibles');
INSERT INTO product_category(id,category_name) VALUES(24,'home and kitchen appliances');
INSERT INTO product_category(id,category_name) VALUES(25,'home improvement and diy');
INSERT INTO product_category(id,category_name) VALUES(26,'home storage and organization');
INSERT INTO product_category(id,category_name) VALUES(27,'jewelry and accessories');
INSERT INTO product_category(id,category_name) VALUES(28,'kitchen and dining');
INSERT INTO product_category(id,category_name) VALUES(29,'mobile accessories');
INSERT INTO product_category(id,category_name) VALUES(30,'movies and entertainment');
INSERT INTO product_category(id,category_name) VALUES(31,'music and instruments');
INSERT INTO product_category(id,category_name) VALUES(32,'musical instruments and equipment');
INSERT INTO product_category(id,category_name) VALUES(33,'office supplies');
INSERT INTO product_category(id,category_name) VALUES(34,'outdoor clothing and gear');
INSERT INTO product_category(id,category_name) VALUES(35,'party decorations and supplies');
INSERT INTO product_category(id,category_name) VALUES(36,'party supplies');
INSERT INTO product_category(id,category_name) VALUES(37,'personalized gifts');
INSERT INTO product_category(id,category_name) VALUES(38,'pet care and supplies');
INSERT INTO product_category(id,category_name) VALUES(39,'pet supplies');
INSERT INTO product_category(id,category_name) VALUES(40,'photography and camera accessories');
INSERT INTO product_category(id,category_name) VALUES(41,'shoes and footwear');
INSERT INTO product_category(id,category_name) VALUES(42,'sporting goods');
INSERT INTO product_category(id,category_name) VALUES(43,'sports and outdoor equipment');
INSERT INTO product_category(id,category_name) VALUES(44,'tools and home improvement');
INSERT INTO product_category(id,category_name) VALUES(45,'toys and games');
INSERT INTO product_category(id,category_name) VALUES(46,'travel and luggage');
INSERT INTO product_category(id,category_name) VALUES(47,'virtual reality and augmented reality');
INSERT INTO product_category(id,category_name) VALUES(48,'watches');
INSERT INTO product_category(id,category_name) VALUES(49,'watches and accessories');
INSERT INTO product_category(id,category_name) VALUES(50,'wedding and event supplies');
ALTER SEQUENCE product_category_id_seq RESTART WITH 51;
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(1, 'super random pizza', true,'pizza', 8, 17, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(2, 'super test pizza', true,'pizza', 6, 17, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(3, 'random burger', true,'burger', 5, 17, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(4, 'random pasta', true,'pasta', 3, 17, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(5, 'Fashionable shoes for young people', true,'Winter Shoes', 110, 41, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(6, 'New product!', true,'Adidas Shoes', 80, 41, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(7, 'Cool!', true,'Vans', 120, 41, 1);
INSERT INTO product(id,description,  instock,name,price,product_category_id,login_user_id) VALUES(8, 'Young and Fresh!', true,'White', 260, 41, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(9, 'Brown! Show your individuality!', true,'Cropped-sho', 160, 41, 1);
INSERT INTO product(id,description, instock,name,price,product_category_id,login_user_id) VALUES(10, 'Take it! You will fall in love with sports!', true,'Blues', 90, 41, 1);
ALTER SEQUENCE product_id_seq RESTART WITH 11;
commit;