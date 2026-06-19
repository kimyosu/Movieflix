CREATE TABLE streaming(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name varchar(100) NOT NULL
);