drop table if exists customers;

create table customers(
    name varchar(32) not null UNIQUE,
    address varchar(128) not null, 
    email varchar(128) not null,
    
    primary key(name)

);

drop table if exists orders;

create table orders(
    order_id varchar(32) not null,
    delivery_id varchar(32),
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    status varchar(32),
    order_date DateTime, 


    primary key(order_id),
	foreign key(name) references customers(name)

);

drop table if exists line_item;

create table line_item(
    item_name varchar(32) not null,
    quantity int default '1',
    order_id varchar(32) not null,

    primary key(order_id)
    foreign key(order_id) references orders(order_id)

);


insert into customers(name, address, email)
values
    ('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com'),
    ('sherlock', '221B Baker Street London', 'sherlock@consultingdetective.org'),
    ('spongebob', '124 Conch Street Bikini Bottom', 'spongebob@yahoo.com'),
    ('dursley', '4 Privet Drive Little Whinging Surrey', 'dursley@gmail.com'),
    ('jessica', '698 Candlewood Land Cabot Cove', 'fletcher@gmail.com');
