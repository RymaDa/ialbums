# iAlbums

This is simple albums app that follows MVVM architectural design pattern and uses android jetpack components.

## MVVM Architecture

__MVVM__ - MVVM stands for Model, View, ViewModel. MVVM is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage”.


![mvvm_architecture](https://github.com/RymaDa/ialbums/tree/main/app/src/main/res/drawable-v24/album_architecture.png)


## Android Jetpack components:-
1. __Android Room Persistence__ - It is a SQLite object mapping library. Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.

2. __Kotlin Coroutines__ - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously. On Android, coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive.

3. __ViewModel__ - It manages UI-related data in a lifecycle-conscious way. It stores UI-related data that isn't destroyed on app rotations.

4. __LiveData__ - It notifies views of any database changes. Use LiveData to build data objects that notify views when the underlying database changes.

5. __Koin__ - A pragmatic lightweight dependency injection framework for Kotlin developers.

5. __Kotlin__ - Kotlin is a modern statically typed programming language used by over 60% of professional Android developers that helps boost productivity, developer satisfaction, and code safety.
