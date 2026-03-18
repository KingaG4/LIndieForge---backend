# LIndieForge – Studio Manager

A university project — a web application for managing indie game production.  
Built with Spring Boot, MySQL and plain HTML/CSS/JavaScript as part of a Web Technologies course.

The app lets a small game dev team track their games, tasks and deadlines in one place instead of juggling spreadsheets and chat messages.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3, Spring MVC, Spring Security |
| ORM | Hibernate, Spring Data JPA |
| Database | MySQL 8 |
| Frontend | HTML5, CSS3, JavaScript, Bootstrap 5 |
| Charts | Chart.js |
| Drag & Drop | Sortable.js |
| Build tool | Maven |

---

## What it does

- Create and manage game projects (genre, engine, platform, production phase, release date)
- Add tasks to games and assign them to team members
- Kanban board with drag-and-drop (To Do / In Progress / In Review / Done)
- Colour-coded deadline indicators (green / yellow / red)
- Dashboard with basic statistics and a personal task list
- Comment threads on tasks
- Export task list to CSV
- Four user roles: Admin, Producer, Developer, Viewer

---

## Repository Structure

```
indieforge/
├── src/
│   ├── main/
│   │   ├── java/com/indieforge/
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── service/          # Business logic
│   │   │   ├── repository/       # Spring Data JPA repositories
│   │   │   ├── model/            # JPA entities and enums
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   └── config/           # Spring Security configuration
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/          # Stylesheets
│   │       │   └── js/           # Frontend scripts (dashboard, kanban, etc.)
│   │       ├── templates/        # Thymeleaf HTML templates
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## Requirements

Before running the project make sure you have installed:

- [Java 17](https://adoptium.net/) — check with `java -version`
- [Maven](https://maven.apache.org/) — check with `mvn -version`
- [MySQL 8](https://dev.mysql.com/downloads/mysql/) — and have it running locally

---

## Setup & Running

### 1. Clone the repository

```bash
git clone https://github.com/your-username/indieforge.git
cd indieforge
```

### 2. Create the database

Open MySQL (via terminal or MySQL Workbench) and run:

```sql
CREATE DATABASE indieforge;
```

That's all — Hibernate will create the tables automatically on first run.

### 3. Edit application.properties

Open `src/main/resources/application.properties` and fill in your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/indieforge
spring.datasource.username=root
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

> If you set up MySQL with a different username or port, adjust the values above accordingly.

### 4. Run the application

```bash
mvn spring-boot:run
```

Wait for the console to say something like `Started IndieForgeApplication`. Then open your browser and go to:

```
http://localhost:8080
```

---

## First Login

There is no automatic seed data. To get started:

1. Register the first account manually (or add a user directly via MySQL Workbench).
2. Set its role to `ADMIN` in the database.
3. Log in and create the remaining accounts from the Admin panel inside the app.

---

## API Endpoints

The backend exposes a REST API used by the frontend via AJAX calls.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/login` | Log in |
| `POST` | `/api/auth/logout` | Log out |
| `GET` | `/api/games` | List games |
| `POST` | `/api/games` | Create a game |
| `GET` | `/api/games/{id}` | Game details |
| `PUT` | `/api/games/{id}` | Update game |
| `PATCH` | `/api/games/{id}/archive` | Archive game |
| `GET` | `/api/games/{id}/stats` | Game statistics |
| `GET` | `/api/games/{gameId}/tasks` | List tasks for a game |
| `POST` | `/api/games/{gameId}/tasks` | Create a task |
| `GET` | `/api/tasks/{id}` | Task details |
| `PUT` | `/api/tasks/{id}` | Update task |
| `PATCH` | `/api/tasks/{id}/status` | Change task status |
| `GET` | `/api/tasks/my` | My tasks |
| `GET` | `/api/tasks/overdue` | Overdue tasks |
| `POST` | `/api/tasks/{taskId}/comments` | Add comment |
| `GET` | `/api/dashboard/stats` | Dashboard statistics |
| `GET` | `/api/games/{id}/export/csv` | Export tasks to CSV |

---

## User Roles

| Role | What they can do |
|---|---|
| **Admin** | Everything — manage users, games, tasks, system settings |
| **Producer** | Create and manage games and tasks, view statistics |
| **Developer** | View assigned games, update status of own tasks, add comments |
| **Viewer** | Read-only access to games and their overview |

---

## Known Limitations

This is a student project built for learning purposes. Some things are intentionally kept simple:

- No automated tests
- No Docker setup
- No deployment configuration
- Passwords are not yet hashed in early development builds — make sure to add BCrypt encoding before any kind of demo

---

## Authors

- _Kinga Głowacka_ | Developer / Designer |
- _Natalia Michalak_ | Developer / Designer |