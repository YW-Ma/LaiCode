1. 构建owners和cats表
```sql
CREATE TABLE owners (
    id integer,
    name text,
    age integer,
    PRIMARY KEY(id)
);

CREATE TABLE cats (
    id integer,
    owner_id integer,
    name text,
    breed text,
    age integer,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES owners --- references的对象肯定是primary key，就不用指定了
);
```

```sql
INSERT INTO cats(id, owner_id, name, breed, age) VALUES (1, 1, 'Apricot', 'orange cat', 11), (2, 1, 'Cat Two', 'orange cat', 9), (3, 2, 'Cat Three', 'black cat', 13), (4, 3, 'Cat Four', 'White cat', 2)
```

##SQL queries
a. figure out the number of cats, over age of 10 of each breed of cat
```sql
select breed, count(id) as number
from cats
where age >= 10
group by breed
```

b. figure out the num of cats each owner owns, for owners id is greater than 10.
```sql
select owners.name, count(cats.id) as number_of_cat
from owners join cats on owners.id = cats.owner_id
where owner_id >= 10
group by owners.name
```
is similar to:
```sql
select owners.name, count(cats.id) as number_of_cat
from owners, cats
where owners.id = cats.owner_id AND owner_id >= 10
group by owners.name
```

c. figure out the owner who owns the most cats
```sql
select owners.name, count(cats.id) as number_of_cat
from owners, cats
where owners.id = cats.owner_id AND owner_id >= 10
group by owners.name
order by number_of_cat desc
limit 1
```

d. figure out the names of all of the cat owners who have a cat named Apricot
```sql
select distinct owners.name
from owners, cats
where owners.id = cats.owner_id and cats.name = 'Apricot' --- 这个是错的！ 两个条件各自的结果进行了笛卡尔积！
```

e. It's not possible to have a cat without its owner.

f. get a random sample of 5 random Maine Coons (a cat breed) with a name that starts with the letter A.
```sql
select cats.name
from cats
order by random() 
limit 5
```

g. create an almost identical table as cats, except with an additional column 'nickname' that 
- has the value 'Kitten' for cats age <= 1
- 'Catto' for ages (1, 15)
- 'Wise One' for >= 15
```sql
CREATE TABLE cats_and_nickname
AS (SELECT * --- 不太会生成这个列。
    )
```

h. select all rows from the cats table that have cats of the top 5 most popular cat breeds
```sql
select * from cats
where cats.breed IN (
    select cats2.breed
    from cats as cats2
    group by cats2.breed
    order by count(id) desc
    limit 1 --- 1可以看到效果，答案应该写5
    )
```