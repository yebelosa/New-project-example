# clean-architecture-example
This is an example project to show what Clean Architecture would look like (in Java).

It was originally created to go together with [this presentation](TODO) (**TODO** put link to presentation when published)

**Table of Contents**
* [Why Clean Architecture?](#why-clean-architecture)
* [Application Structure](#application-structure)
* [Testing Strategy](#testing-strategy)
* [Building and Running the application](#building-and-running-the-application)
* [The example domain](#the-example-domain)
* [Resources](#resources)

***

## Why Clean Architecture?
> The center of your application is not the database. Nor is it one or more of the frameworks you may be using. **The center of your application are the use cases of your application**  -  _Unclebob_ ([source](https://blog.8thlight.com/uncle-bob/2012/05/15/NODB.html "NODB"))

Clean architecture helps us solve, or at least mitigate, these common problems with architecture:
* **Decisions are taken too early**, often at the beginning of a project, when we know the least about the problem that we have to solve
* **It's hard to change**, so when we discover new requirements we have to decide if we want to hack them in or go through an expensive and painful re-design. We all know which one usually wins. _The best architectures are the ones that allow us to defer commitment to a particular solution and let us change our mind_
* **It's centered around frameworks**. Frameworks are tools to be used, not architectures to be conformed to. Frameworks often require commitments from you, but they don’t commit to you. They can evolve in different directions, and then you’ll be stuck following their rules and quirks
* **It's centered around the database**. We often think about the database first, and then create a CRUD system around it. We end up using the database objects everywhere and treat everything in terms of tables, rows and columns
* **We focus on technical aspects** and when asked about our architecture we say things like “it’s servlets running in tomcat with an oracle db using spring”
* **It's hard to find things** which makes every change longer and more painful
* **Business logic is spread everywhere**, scattered across many layers, so when checking how something works our only option is to debug the whole codebase. Even worse, often it's duplicated in multiple places
* **Forces/Encourages slow, heavy tests**. Often our only choice for tests is to go through the GUI, either because the GUI has a lot of logic, or because the architecture doesn't allow us to do otherwise. This makes tests slow to run, heavy and brittle. It results in people not running them and the build beind broken often
* **Infrequent deploys** because it's hard to make changes without breaking existing functionalities. People resort to long-lived feature branches that only get integrated at the end and result in big releases, rather than small incremental ones

Clean architecture gives us all these benefits:
* **Effective testing strategy** that follows the [testing pyramid](http://martinfowler.com/bliki/TestPyramid.html) and gives us a fast and reliable build
* **Frameworks are isolated** in individual modules so that when (not if) we change our mind we only have to change one place, with the rest of the app not even knowing about it
* **Independent from Database**, which is treated just like any other data provider. Our app has real use cases rather than being a CRUD system
* **Screaming architecture** a.k.a. it screams its intended usage. When you look at the package structure you get a feel for what the application does rather than seeing technical details
* **All business logic is in a use case** so it's easy to find and it's not duplicated anywhere else
* **Hard to do the wrong thing** because modules enforce compilation dependencies. If you try to use something that you're not meant to, the app doesn't compile
* **We're always ready to deploy** by leaving the wiring up of the object for last or by using feature flags, so we get all the benefits of continuous integration (no need for feature branches)
* **Swarming on stories** so that different pairs can easily work on the same story at the same time to complete it quicker
* **Good monolith** with clear use cases that you can split in microservices later one, once you've learnt more about them

Of course, it comes at a cost:
* **Perceived duplication of code**. Entities might be represented differently when used in business logic, when dealing with the database and when presenting them in a json format. You might feel like you're duplicating code, but you're actually favouring _decoupling over DRY_
* **You need interesting business logic** to "justify" the structure. If all you do in your use case is a one-line method to read or save from a database, then maybe you can get away with something simpler

**TODO add picture of the recap**

***

## Application Structure
**TODO**
Explain what the different modules do
Include the diagram from the presentation when ready
Show a sequence diagram for an API call
Show a sequence diagram for a scheduled job

***

## Testing Strategy
**TODO**
Explain how the testing pyramid is implemented

***

## Building and Running the application
- building the application:
```
./gradlew clean build
```
- running the application (from the jar, after having built it):
```
java -jar application/build/clean-architecture-example.jar
```
- running the application (on the fly):
```
./gradlew bootRun
```
- running the application (in the IDE): open and run the main class
```
com.clean.example.configuration.Application
```
- more info on available tasks:
```
./gradlew tasks
```

Once the application is running, you can:
- open <http://localhost:8080/broadbandaccessdevice/device1.exlon.com/> and you should see some json
- look at the log and you should see a scheduled job running every 5 seconds (it prints something like _"Job Starting: ReconcileBroadbandAccessDeviceJob..."_)

#### Importing the project in IntelliJ
- Simply open the _build.gradle_ file and IntelliJ should load everything

#### Importing the project in Eclipse
 - Make sure you've installed the Gradle plugin
 - "Import existing project", choose Gradle, select the main folder and follow the instructions

***

## The example domain
**TODO**
Explain what the domain used in the example is

***

## Resources
**TODO**

***

## Contacts
**TODO leave contacts for people to ask questions**
