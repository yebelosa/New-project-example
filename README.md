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


## Why Clean Architecture?
Because
> The center of your application is not the database. Nor is it one or more of the frameworks you may be using. **The center of your application are the use cases of your application**  -  _Unclebob_([source](https://blog.8thlight.com/uncle-bob/2012/05/15/NODB.html "NODB"))

Explain what problem we're trying to solve
Explain pros and cons


## Application Structure
**TODO**
Explain what the different modules do
Include the diagram from the presentation when ready
Show a sequence diagram for an API call
Show a sequence diagram for a scheduled job


## Testing Strategy
**TODO**
Explain how the testing pyramid is implemented


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

## The example domain
**TODO**
Explain what the domain used in the example is


## Resources
**TODO**

## Contacts
**TODO leave contacts for people to ask questions**
