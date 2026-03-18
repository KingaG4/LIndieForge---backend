# Project No. 5: LIndieForge – Studio Manager

---

**Project number:** 5  
**Title:** LIndieForge – a web application for managing indie game production  
**Technologies:** MySQL · Spring Framework + Hibernate · HTML / CSS / JavaScript  

---

## Project Authors

| Full name | Role |
|---|---|
| _Kinga Głowacka_ | Developer / Designer |
| _Natalia Michalak_ | Developer / Designer |

---

## Section 1. Application Domain, Motivation and Goals

### Application Domain

**LIndieForge** is a web application that supports project management for small, independent game development studios. Each game is treated as a separate project, within which a team (programmers, artists, testers) works on tasks assigned to them. The application allows the team to track progress, monitor deadlines, and know who is currently working on what.

### Motivation

Small indie studios often organise their work through spreadsheets and messaging apps. This means nobody has a full picture of what is going on: which tasks are overdue, who is available, or when an important deadline is coming up. LIndieForge brings all of this information into one place accessible through a browser — no installation required, available to the whole team.

### Goals

The application aims to enable:

1. **Game (project) management** – creating game projects with a description, status and release date.
2. **Task management** – each task has an assigned person, a status and a deadline.
3. **Kanban board** – a view of tasks organised in columns by status, with drag-and-drop support.
4. **Deadline tracking** – visual indicators for tasks that are approaching their deadline or already overdue.
5. **Basic dashboard** – a home screen with simple statistics and a list of today's tasks.
6. **Access control** – different permissions for different roles within the team.

---

## Section 2. User Roles

The system distinguishes between **business roles** (what each person does in the studio) and **technical roles** (how the system identifies a user).

### 2.1 Business Roles

#### Administrator (Admin)
Manages the entire application. Creates and deactivates user accounts, assigns roles, and edits system dictionaries (e.g. game genres, platforms). Has access to all system features.

#### Producer
Responsible for a game project — the equivalent of a project manager. Creates new games, adds tasks, assigns them to developers and sets deadlines. Can view statistics and change a project's status.

#### Developer
A member of the production team: programmer, artist, musician or tester. Can see tasks assigned to them, update their status and add comments. Cannot create projects or tasks.

#### Viewer
A read-only role — for example for an investor or external client. Can browse the list of games and their general status, but cannot make any changes.

### 2.2 Technical Roles

#### Guest
An unauthenticated user. Can only see the login page.

#### Authenticated User
Every logged-in user can edit their own profile (name, password) regardless of their business role.

### 2.3 Role and Permission Matrix

| Feature | Admin | Producer | Developer | Viewer |
|---|:---:|:---:|:---:|:---:|
| User account management | ✅ | ❌ | ❌ | ❌ |
| Create and edit games | ✅ | ✅ | ❌ | ❌ |
| Create and edit tasks | ✅ | ✅ | ❌ | ❌ |
| Change task status | ✅ | ✅ | ✅ (own tasks) | ❌ |
| Browse games and tasks | ✅ | ✅ | ✅ (assigned) | ✅ |
| Dashboard with statistics | ✅ | ✅ | ✅ | ❌ |
| Add comments | ✅ | ✅ | ✅ | ❌ |
| System configuration | ✅ | ❌ | ❌ | ❌ |
| Edit own profile | ✅ | ✅ | ✅ | ✅ |

---

## Section 3. Project Functionalities

### 3.1 Pages and Screens

#### 3.1.1 Login page (`/login`)
A form with email and password fields. Includes a "Forgot password" link (password reset via email). The only page accessible without logging in.

#### 3.1.2 Dashboard (`/dashboard`)
The home screen after logging in. Contains:
- **4 stat tiles**: number of active games, overdue tasks, tasks assigned to me, tasks with status "In Progress",
- **pie chart**: breakdown of tasks by status (To Do / In Progress / Done),
- **"My Tasks" list**: tasks assigned to the logged-in user, sorted by deadline.

The dashboard is personalised — a Developer sees only their own tasks, a Producer sees the full picture for their games.

#### 3.1.3 Games list (`/games`)
A page showing cards or a table of games available to the current user. Each entry shows: game name, genre, platform, production phase and completion percentage (calculated as the ratio of completed tasks to all tasks). Supports filtering by phase and searching by name. The "New Game" button is only visible to Producers and Admins.

#### 3.1.4 Game details (`/games/{id}`)
A page divided into three tabs:

- **Overview**: game name, description, genre, platform (PC / console / mobile), engine (Unity / Godot / Unreal / other), production phase (Concept / Production / Alpha / Beta / Released), planned release date, team member list, progress bar,
- **Tasks**: a list of all tasks for this game with filters (status, assigned person). Each task shows its title, assignee, priority and deadline with a colour indicator,
- **Kanban**: a board with four columns — *To Do*, *In Progress*, *In Review*, *Done*. Task cards can be dragged between columns.

#### 3.1.5 Create / edit game form (`/games/new`, `/games/{id}/edit`)
A form with fields: name (required), description, genre (dropdown: RPG / Platformer / Puzzle / Horror / Other), engine (dropdown), platform (checkboxes), production phase (dropdown), planned release date, assigned producer, team members (selected from the user list). Validated on both the client side (JavaScript) and the server side (Spring Validation).

#### 3.1.6 Task card (`/tasks/{id}`)
A page with the details of a single task:
- title and description,
- type (Programming / Art / Audio / Testing / Other),
- status (To Do / In Progress / In Review / Done / Cancelled),
- priority (High / Medium / Low),
- assigned person and deadline — with a colour indicator (green: more than 3 days left, yellow: 1–3 days left, red: overdue),
- comments section (list of comments + add-comment form),
- status change button available to the assignee, Producer and Admin.

#### 3.1.7 Create / edit task form (`/tasks/new`, `/tasks/{id}/edit`)
A form with fields: title (required), description, type, status, priority, assigned person (list of users assigned to the game), deadline. Available to Producers and Admins only.

#### 3.1.8 Admin panel (`/admin/users`) — Admin only
A table of all users with columns: name, email, role, account status. Features: create new account, change role, deactivate account, reset password.

#### 3.1.9 System settings (`/admin/settings`) — Admin only
Management of application dictionaries: game genres, engines, platforms, task types, production phases.

#### 3.1.10 User profile (`/profile`)
Edit own data: full name, password change. Overview of assigned games and tasks.

---

### 3.2 Core Production Management Features

#### 3.2.1 Games (projects)
- Create a new game with basic data (name, description, genre, engine, platform, dates, team).
- Change the production phase: Concept → Production → Alpha → Beta → Released.
- Archive a finished or cancelled game — it disappears from the main list but remains accessible in an archive view.
- Completion percentage calculated automatically as the ratio of "Done" tasks to all tasks in the game.

#### 3.2.2 Tasks
- Each task belongs to a specific game and has: title, description, type, priority, status, assigned person and deadline.
- Producers and Admins can create, edit and delete tasks.
- Developers can change the status of their own tasks and add comments.
- Task status can be changed either from the task card or by dragging the card on the Kanban board.

#### 3.2.3 Deadlines
- Each task has an optional deadline.
- Colour indicator next to the deadline: green (more than 3 days away), yellow (1–3 days), red (overdue).
- The dashboard shows a list of overdue tasks assigned to the logged-in user.

---

### 3.3 Statistics and Analytics

#### 3.3.1 Dashboard
Described in section 3.1.2. Data loaded via AJAX requests to the Spring REST API — the page does not reload when refreshing statistics.

#### 3.3.2 Game progress bar
On both the game details page and the games list, a progress bar (0–100%) is displayed and calculated automatically as: `(number of "Done" tasks / total number of tasks) × 100`.

#### 3.3.3 Pie chart
On the dashboard, a simple pie chart (Chart.js) shows the breakdown of the current user's tasks (or all tasks in a game for the Producer) by status.

#### 3.3.4 CSV export
Producers and Admins can download the task list for a selected game as a CSV file, generated server-side by Spring.

---

### 3.4 User Interface Elements

| Element | Description |
|---|---|
| **Kanban board** | Four columns (To Do / In Progress / In Review / Done). Task cards can be dragged between columns — moving a card updates the task status in the database. Implementation: Sortable.js or native HTML5 Drag & Drop. |
| **Deadline colour indicators** | Each task displays its deadline date in green, yellow or red depending on how close it is. |
| **Progress bar** | A visual bar (HTML `<progress>` or CSS) on the game page showing the percentage of completed tasks. |
| **Pie chart** | A simple chart on the dashboard implemented with Chart.js. |
| **Responsive layout** | Page layout adapts to screen size. Implementation: Bootstrap 5. |
| **Dark theme** | Default dark theme reflecting the gamedev aesthetic. A light/dark toggle saved in localStorage. |
| **Toast notifications** | Small pop-up messages in the corner of the screen confirming actions (e.g. "Task saved"). Implementation: Bootstrap Toast or Toastify.js. |
| **Search bar** | A text field that filters the games and tasks list in real time (JavaScript, no page reload). |
| **Breadcrumb navigation** | Navigation path at the top of each subpage, e.g. "Games → Dungeon Quest → Task #12". |

---

### 3.5 Access Control — Detailed Table

| Page / Feature | Admin | Producer | Developer | Viewer |
|---|:---:|:---:|:---:|:---:|
| Dashboard | ✅ | ✅ | ✅ | ❌ |
| Games list | ✅ | ✅ | ✅ (assigned) | ✅ |
| Create / edit game | ✅ | ✅ | ❌ | ❌ |
| Archive game | ✅ | ✅ (own) | ❌ | ❌ |
| Game details — Overview tab | ✅ | ✅ | ✅ | ✅ |
| Game details — Tasks tab | ✅ | ✅ | ✅ | ✅ (read) |
| Game details — Kanban board | ✅ | ✅ | ✅ | ✅ (read) |
| Create / edit tasks | ✅ | ✅ | ❌ | ❌ |
| Change task status | ✅ | ✅ | ✅ (own) | ❌ |
| Task card (read) | ✅ | ✅ | ✅ | ✅ |
| Add comments | ✅ | ✅ | ✅ | ❌ |
| CSV export | ✅ | ✅ | ❌ | ❌ |
| User management panel | ✅ | ❌ | ❌ | ❌ |
| System settings | ✅ | ❌ | ❌ | ❌ |
| Own profile | ✅ | ✅ | ✅ | ✅ |

---

*Specification prepared for the course: Internet Technologies / Web Applications*  
*Stack: MySQL · Spring Framework + Hibernate · HTML / CSS / JavaScript*