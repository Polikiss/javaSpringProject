delete from kitty_friend
where 1 = 1;
delete from kitty
where 1 = 1;
delete from owner
where 1 = 1;
INSERT INTO Owner(Id, bday, name)
VALUES (1, '2004-02-12 00:00:00.000000', 'Den');

INSERT INTO Kitty(kitty_id, name, bday, color, breed, owner_id)
VALUES (1, 'Garfild', '2004-02-12 00:00:00.000000', 2, 'ylichniy', 1);

INSERT INTO Kitty(kitty_id, name, bday, color, breed, owner_id)
VALUES (2, 'Angela', '2004-02-12 00:00:00.000000', 3, 'idk', 1);


