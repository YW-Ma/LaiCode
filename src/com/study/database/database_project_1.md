##Logic:

[category1] <-- [category2] <-- [product]

cate1和cate2放在不同的两个表里\
好处是不容易被乱改。

[customer]

### customer表
```sql
create table ofs_customers (
    id int not null primary key, 
    name char(64) not null, 
    address text not null, 
    postcode int not null);
```

### category表（先定义category）
```sql
Create table ofs_category1_meta(
    id int not null primary key, 
    displayname char(32) not  null);

Create table ofs_category2_meta(
    id int not null primary key, 
    cate1id int not null, 
    displayname char(32) not  null, 
    constraint fk_cate1 foreign key (cate1id) 
        references ofs_category1_meta(id)); 
```

### product表
（有一个依赖category的分类，类似于一个tag，方便检索某类下的product）
```sql
create table ofs_products (
    id int not null primary key, 
    name char(128) not null, 
    categoryid int not null, 
    price numeric(8,2) not null, 
    available int not null check (available >= 0), 
    constraint fk_cate foreign key (categoryid) 
        references ofs_category2_meta(id));
```

### order表
```sql
create table ofs_orders (
    id int not null primary key,
    custid int not null,
    checkouttime timestamp not null,
    prodid int not null,
    prodquantity int not null check(prodquantity >= 1),
    prodprice numeric(8,2) not null check (prodprice >= 0.0),
    totalpaid numeric(8,2) not null check (totalpaid >= 0.0),
    address text not null,
    postcode int not null,
    deliveryid int,
    deliverytime timestamp,  --- 这可以null，null就代表还没有邮寄
    constraint fk_cust foreign key (custid) references ofs_customers(id),
    constraint fk_prod foreign key (prodid) references ofs_products(id)
);
```