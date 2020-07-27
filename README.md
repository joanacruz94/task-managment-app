# Taskey 

Taskey is an intuitive and easy-to-use application for management of projects, tasks and teams.

https://streamable.com/yctgzn Link to a video using Taskey

https://taskey-angular-app.herokuapp.com/ Link to Taskey

https://taskey-edge-service.herokuapp.com/swagger-ui.html#/ Link to Swagger documentation of Taskey API

## Techonologies:

- Netflix OSS Feign and Eureka for architecture in microservices
- Java Spring Boot and Hibernate for back-end development
- Angular and TypeScript for front-development
- MongoDB for logs storage
- Authentication with JSON Web Tokens
- Heroku for deployment
- Schedule tasks
- SMTP for e-mail sending(almost working)
- Drag and drop techonology

## Functionalities:

- Create a project 
- Update description of project
- Add member to one project
- Remove a project
- Create a task to a project(includes a description, category, urgency and deadline), attribute to some member of project
- Update status of task simpling dragging and drop
- Remove a task

## Development:

- Application segmented in four micro-services respectively to manage projects, tasks, users and notifications. Edge-service that acts as a gateway to all these services, handles all the requests and handle all the security as well. 
- Validations with Java API Bean Validation 2.0.
- Global handler to handle exceptions(custom and inherited from Java)
- Lombok to have cleaner classes and code
- API documentation in Swagger

## Future work:

- More robust logging and more notifications sent
- Complete SMTP implementation to send e-mail of confirmation when sign up
- Improvement of front-end: error, warning and success messages
- Improvement of design and responsive design
- Google calendar api to show deadlines
- Storage of appropriated files related to a project
- Search bars to filter tasks by project, category, urgency, status and deadline
- Concepts of sub-tasks and task-dependency
- Statistics and reports (for example if the deadlines are being successfully complete)
- Import or export tasks from an excel and export to pdf
- So many more ideas that can come, and I will continue to work on this on my free time

