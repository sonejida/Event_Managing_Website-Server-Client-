INSERT INTO tickets (userName, name, price, age, gender, email, phone_Num) VALUES 
('Jon', 'Jon Maxwell', '35.50', '29', 'male', '1jon@gmail.com', '4377992203'),
('Alic', 'Alice Carter', '35.50', '28', 'female', '1alicecarter@gmail.com', '4379142838'),
('Alic', 'Alice Robert', '60.50', '28', 'female', '1alicealbert@gmail.com', '6478132954'),
('Dars', 'Darshit Soneji', '60.50', '19', 'male', '1darshitsoneji@gmail.com', '4377992505'),
('Dars', 'Karan Soneji', '60.50', '24', 'male', '1karansoneji@gmail.com', '4376342505'),
('Jack', 'Jack Doe', '60.50', '25', 'male', '1jackdoe@gmail.com', '6789026783'),
('Jack', 'Grace Doe', '35.50', '23', 'female', '1gracedoe@gmail.com', '5489376819'),
('Mali', 'Abhir Malik','35.50', '22', 'male', '1abhirmalik@gmail.com', '6479827384'),
('Mali', 'Rohan Malik','60.50', '20', 'male', '1rohanmalik@gmail.com', '4298731835'),
('Shar', 'Adarsh Sharma','35.50', '18', 'male', '1adarshSharma@gmail.com', '647915204'),
('Shar', 'Manoj Sharma','35.50', '18', 'male', '1manojSharma@gmail.com', '647913479');


insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Dars', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jack', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Mali', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Alic', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Shar', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into sec_role (roleName)
values ('ROLE_VENDER');

insert into sec_role (roleName)
values ('ROLE_GUEST');
 
insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);

 