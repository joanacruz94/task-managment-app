insert into projects (id, name, description) values
(1, 'API for products of Finantech', 'Build an API in ASP .NET MVC to return all the products, modules, sub-modules, concepts and actions in the hierarchy'),
(2, 'Taxonomy and documentation of Finantech', 'Build a monolitic application using react, redux and redux-saga'),
(3, 'Online banking academic app', 'Build an online banking app like revolut using react in the front-end development and nodeJS in the back-end development');

insert into user_projects(id, user_id, project_id, owner) values
(1, 2, 1, true),
(2, 1, 1, false),
(3, 1, 2, false),
(4, 2, 2, true),
(5, 3, 2, false),
(6, 4, 2, false),
(7, 1, 3, true),
(8, 2, 3, false),
(9, 3, 3, false),
(10, 4, 3, false);