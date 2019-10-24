# Github commits
Kotlin, Dagger 2, RxJava 2, Retrofit, MVP and MVVM demo

In this project, I will share a practical example of Model View Presenter (MVP) pattern in Android development with Kotlin as basic language.
The consideration of, and the handling of Activity/Fragment life cycles and other unique Android way of life that we have to deal with, makes it hard to adopt generic design patterns into Android development.
To understand Android  MVP pattern we first need to understand the problem it is trying to solve. 
The goal of MVP in Android is to separate the known from the unknown.

Android MVP pattern is a design approach that separates your programming logic from the Activity and the Fragment. 
With this approach, the Activity or the Fragment  is the View and the Java/Kotlin class,  holding the logic is the Presenter.

Android MVP By Example:

I will use a list of class/files to demonstrate Android MVP pattern. 
Here are the steps to display a list of classes using MVP.

Step 1. Create the Contract: 

The Contract is the glue that binds the participating components in MVP pattern together. 
It is nothing but the good old Java interface that defines the responsibility of the Model, the View and the Presenter.
we can create these three interfaces within one class file or as create three separate class files.


1. ViewIterface:  this defines the methods that the concrete View aka Fragment or Activity will implement. 
This way you can proceed to create and test the Presenter without worrying about Android-specific components such as Context.

2. ActionInterface:  this defines the methods that the concrete Presenter class will implement. 
Also known as user actions, this is where the business logic for the app is defined.

3. RepositoryInterface: This defines the methods that the concrete persistence class will implement. 
This way the Presenter does not need to be concerned about how data is persisted.

Step 2. Create Presenter class and Update Dagger Component: 

Since the Presenter is a POJO/POKO class, the components that we need in this class such as the concrete implementation of the View, Repository, or Downloading data from server, etc has to be passed to us.
We can either receive these components via the constructor or we can have them be injected using Dagger 2. We will use the later approach.
Presenter does not need to know who the View is, and because of this, you can refine, refactor and test your Presenter logic independently. 
When business requirements change, you just define more methods in the Contract and then have the respective classes to implement them.

Step 3. Create Persistence Module:

 We need to let Dagger 2 know which concrete class will be used to satisfy the dependency on the Repository interface. This concrete class can use either in-memory data, SQLite, Realm Database, or new Architecture component Room. 

Step 4. Implement the View: 

The View can be implemented in the Activity of the Fragment, however for a number of reasons including handling of configuration changes it is better to implement the View with Fragment. 

What about the Adapter? – The adapter is a component of the View and should not directly participate in the MVP pattern. 
The Fragment works with the Adapter. We will need to create a method in the adapter that accepts a list of the items we want to display. 
We can then call this method from the Fragment and pass it the list of items the Presenter passed to the Fragment and the Adapter will repaint itself.

Dagger2:

Dagger2 is a dependencies injector. You can see more details about Dagger2 in Google Android web site.

RxJava2:

RxJava2 is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.

Retrofit2:

Retrofit2 is a REST Client for Android and Java. It makes it relatively easy to retrieve and upload JSON. 
Retrofit2 uses the OkHttp library for HTTP requests. Here we can replace OkHttp with any other library.
Now, let us take advantage of dependency injection to let the context and Retrofit services to be injected in the presenter.
Injecting Context
We will have to create a module to inject the Context in the presenter. We will name this module ContextModule 
and will place it in a package module which is placed in a package named DI in Shared Package.
We will take the opportunity to also let ContextModule provide the Application instance.

Injecting Retrofit:

We just have to create a NetworkModule class in order to provide Retrofit services. No need to add any property to the class as the network dependencies can be instantiated from scratch.

Presenter Component and Injection:

In order to be able to inject the required dependencies to the PostPresenter, we will have to define a PresenterInjector interface in order to provide method to inject it.

PostPresenter:

Because one of the role of PostPresenter is to subscribe to API call events, we will have to add dependencies to RxJava and RxAndroid libraries in the build.gradle file of the module.

What the PostPresenter will have to do is:

Right after the view is created, call the JSONPlaceholder API to retrieve the Posts
Right after the call is made, show the ProgressBar on the view When the API call ends, hide the loader If retrieving the posts from the API has been made without any problem, update the retrieved posts in the view If retrieving the posts from the API has failed, show the error in the view

PostActivity:

Because our PostActivity only consists in a RecyclerView with a loader which may be generic in the application, we will use BindingAdapter methods to set properties of the views.
So now, let us add a DataBinder.kt file to the utils package in order to map BindingAdapter methods.

