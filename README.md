<div id="top"></div>

# Task Manager JavaFX app

✅	Feedbacks and Presentation will be here -> https://drive.google.com/drive/folders/1FXKlnxErgd8L0d8JNkgxO7uu6youm7FN?usp=sharing

✅	University Logo is Included

✅	PostgreSQL Data Storage

The database is hosted on localhost. Checking the app may be troublesome, but I can show it working in person or record a video if needed.

The project is built solely by myself.


### Content

<p>(<a href="#about">About the project</a>)</p>

<p>(<a href="#buildWith">Built with</a>)</p>


<div id="about"></div>

### About the Project

This application helps to track your work on projects. Simple functionality is supported by a PostgreSQL database.


### 1. Sign in Page

Takes you to the app, if you enter all the credentials correctly, rejects if not. LogIn gets validated by sending a query to a **PostgreSQL database** using all the info you've entered.

Once user've entered the app, it saves user's information to work with it over the time that the Task Manager is working.

Sadly, multiple users can't work on the same project yet, because projectName property is set to be a primary key in projects table, and so it can't be repeated. I know it's a poor practice to set a string as a primary key, but now I've really learned my lesson.

<img src="https://github.com/renren-017/DMS_javafx/blob/main/img/signIn.png" alt="Sign In Page" width="700"/>

### 2. Sign Up Page

Here you can create your account, by filling in all empty fields. Just sends a query to create a new row in **users** table.

<img src="https://github.com/renren-017/DMS_javafx/blob/main/img/signUp.png" alt="Sign Up Page" width="700"/>

### 3. Here's how the app looks on the inside.

On the left there's a form for creating new tasks. And on the right all of the tasks related to your project are displayed.

<b>IMPORTANT!</b> Start the app by clicking **Start/Restart** button first.

**Start/Restart** button is responsible for retrieving all the tasks in your project from the database and displaying it on the right side of the window.

<img src="https://github.com/renren-017/DMS_javafx/blob/main/img/appInside.png" alt="Task Manager Page" width="700"/>

The form on the left takes four parameters for the new task:

* task itself,
* task deadline,
* whether it's urgent or not,
* its status - unsorted, in process, finished.

This info along with the name of the project you're currently working on is sent to a database creating a row in **tasks** table.

### Example Of Creating A Task

Creating Task             |  Task Created
:-------------------------:|:-------------------------:
<img src="https://github.com/renren-017/DMS_javafx/blob/main/img/creatingTask.png" alt="Creating Task" width="400"/>  |  <img src="https://github.com/renren-017/DMS_javafx/blob/main/img/creatingTask2.png" alt="Task Created" width="400"/>  


You also can delete a task if you want, by clicking a **delete** button under each task, or change its status by clicking a corresponding arrow buttons ("<<-", "<", "->", "->>") under it.

### Example Of Changing Task's Status

Clicking on **->** button at **"Additional Buttons fix"**            |  Status of the Task Changed, view updated
:-------------------------:|:-------------------------:
<img src="https://github.com/renren-017/DMS_javafx/blob/main/img/btnCheck.png" alt="Task Created" width="400"/>  |  <img src="https://github.com/renren-017/DMS_javafx/blob/main/img/btnCheck2.png" alt="Task Created" width="400"/> 

Both delete and status change functions are followed by a query to the database, deleting or changing a task in the **tasks** table.

**Task description, due time and urgency of the task can't be set or unset after creating a task.**

<div id="buildWith"></div>

## Built With

* [PostgreSQL JDBC](https://jdbc.postgresql.org/download.html)
* [JavaFX](https://openjfx.io/)


<p align="right">(<a href="#top">back to top</a>)</p>
