INSERT INTO roles(id,name) VALUES(1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES(2,'ROLE_ADMIN');
ALTER SEQUENCE roles_id_seq RESTART WITH 3;
INSERT INTO login_user(id, username, email, password, phone, firstname, lastname, isbuyer, isseller) VALUES (1, 'username', 'username@gmail.com', '$2a$10$9cOQijrPNVgUeMzqnSI1PezrYpZ07TwoSnrtxJWK/PSz9jSxAJt8a', 3467867981, 'firstname', 'lastname', true, true);
ALTER SEQUENCE login_user_id_seq RESTART WITH 2;
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);

INSERT INTO product_category(id,category_name) VALUES(1,'food');
ALTER SEQUENCE product_category_id_seq RESTART WITH 2;
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(1,'super random pizza', true,'pizza', 8, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(2,'super test pizza', true,'pizza', 6, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(3,'random burger', true,'burger', 5, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(4,'random pasta', true,'pasta', 3, 1, 1);
ALTER SEQUENCE product_id_seq RESTART WITH 5;
commit;
