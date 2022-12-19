-- ¡Atention! This script must be executed with super user such as postgres

create role postgrespf with login password 'postgrespf';
    
create database postgrespf owner="postgrespf";
