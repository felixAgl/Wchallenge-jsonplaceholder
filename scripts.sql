-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	bs varchar(255) NULL,
	catch_phrase varchar(255) NULL,
	city varchar(255) NULL,
	company_name varchar(255) NULL,
	email varchar(255) NULL,
	lat varchar(255) NULL,
	lng varchar(255) NULL,
	name varchar(255) NULL,
	phone varchar(255) NULL,
	street varchar(255) NULL,
	suite varchar(255) NULL,
	username varchar(255) NULL,
	website varchar(255) NULL,
	zipcode varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.albums definition

-- Drop table

-- DROP TABLE public.albums;

CREATE TABLE public.albums (
	id bigserial NOT NULL,
	title varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT albums_pkey PRIMARY KEY (id),
	CONSTRAINT fkcfmaqhra991wm7iiddqlnw88n FOREIGN KEY (user_id) REFERENCES users(id)
);


-- public.photos definition

-- Drop table

-- DROP TABLE public.photos;

CREATE TABLE public.photos (
	id bigserial NOT NULL,
	thumbnail_url varchar(255) NULL,
	title varchar(255) NULL,
	url varchar(255) NULL,
	album_id int8 NULL,
	CONSTRAINT photos_pkey PRIMARY KEY (id),
	CONSTRAINT fkoamp0ftyyl46e15v3inuu6ke5 FOREIGN KEY (album_id) REFERENCES albums(id)
);


-- public.posts definition

-- Drop table

-- DROP TABLE public.posts;

CREATE TABLE public.posts (
	id bigserial NOT NULL,
	body varchar(500) NULL,
	title varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT posts_pkey PRIMARY KEY (id),
	CONSTRAINT fk5lidm6cqbc7u4xhqpxm898qme FOREIGN KEY (user_id) REFERENCES users(id)
);


-- public.user_has_albums definition

-- Drop table

-- DROP TABLE public.user_has_albums;

CREATE TABLE public.user_has_albums (
	id bigserial NOT NULL,
	album_permissions varchar(255) NULL,
	album_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT user_has_albums_pkey PRIMARY KEY (id),
	CONSTRAINT fkbea1j3f9nfedda9iyiuyrc144 FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fkp1ywb5flpngx9bib60j8fouun FOREIGN KEY (album_id) REFERENCES albums(id)
);


-- public."comments" definition

-- Drop table

-- DROP TABLE public."comments";

CREATE TABLE public."comments" (
	id bigserial NOT NULL,
	body varchar(500) NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	post_id int8 NULL,
	CONSTRAINT comments_pkey PRIMARY KEY (id),
	CONSTRAINT fkh4c7lvsc298whoyd4w9ta25cr FOREIGN KEY (post_id) REFERENCES posts(id)
);