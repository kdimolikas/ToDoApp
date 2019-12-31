# ToDoApp
This is an Android application that provides a simple way to create and manage a list of "TO-DO" tasks.

The components of the application are classified into three packages based on their functionality:
1. [model](app/src/main/java/com/example/todoapp/model): includes the classes that represent the data of the application.
2. [view](app/src/main/java/com/example/todoapp/view): includes the UI of the application.
3. [controller](app/src/main/java/com/example/todoapp/controller): includes the business logic of the application.

## Usage
The use cases of this application are the following:
* Add a new task.
* (Un-)check a task.

##Testing
[Unit tests](app/src/test) were created for the majority of the app's classes using [Robolectric][1], [JUnit4][2] and [Mockito][3] frameworks.

[1]:http://robolectric.org/ 
[2]:https://junit.org/junit4/
[3]:https://site.mockito.org/