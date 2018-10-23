create table city(
ibge_id serial,
uf varchar(2),
name varchar(100),
capital boolean,
lon varchar(20),
lat varchar(20),
no_accents varchar(100),
alternative_names varchar(100),
microregion varchar(60),
mesoregion varchar(60),
primary key(ibge_id));
