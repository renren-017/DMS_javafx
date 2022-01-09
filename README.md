<div id="top"></div>

# Task Manager JavaFX app

## About the Project

This application helps to track your work on projects. Simple functionality is supported by the PostgreSQL database, so that the tasks are there when you re-enter the app.

### 1. Sign in Page

![](https://github.com/renren-017/DMS_javafx/blob/main/img/signIn.png)

### 2. If not signed up yet, Sign Up Page

![](https://github.com/renren-017/DMS_javafx/blob/main/img/signUp.png)

### Here's how the app looks on the inside.

On the left there's a form for creating new tasks. And on the right all of the tasks related to your project are displayed.

<b>IMPORTANT!</b> The Task Manager does not refresh itself, so after creating or deleting a desk, I suggest you pressing the 'Start/Restart' Button.
![](https://github.com/renren-017/DMS_javafx/blob/main/img/appInside.png)
The form takes five parameters for the new task:
* task itself,
* task deadline,
* whether it's urgent or not,
* its status - unsorted, in process, finished,
* project its related to (this one it pulls from the user info after you pass the signIn form).

### Here's a closer look
![](https://github.com/renren-017/DMS_javafx/blob/main/img/creatingTask.png)


## Built With
* [Node.js](https://nodejs.org/en/)
* [React.js](https://reactjs.org/)
* [TailwindCSS](https://tailwindcss.com/?utm_source=cdnjs&utm_medium=cdnjs_link&utm_campaign=cdnjs_library)
* [NYArticle Search API](https://developer.nytimes.com/docs/articlesearch-product/1/overview)

<p align="right">(<a href="#top">back to top</a>)</p>
