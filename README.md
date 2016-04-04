# clean-architecture-example
This is an example project to show what Clean Architecture would look like (in Java).

It goes together with [this presentation](TODO) (**TODO** put link to presentation when published)

**Table of Contents**
* [Why Clean Architecture?](why-clean-architecture)
* [Application Structure](#application-structure)
* [Testing Strategy](#testing-strategy)
* [Building and Running the application](#building-and-running-the-application)
* [The example domain](#the-example-domain)
* [Resources](#resources)

## Why Clean Architecture?
**TODO**
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
> ./gradlew clean build
- running the application (from the jar, after having built it):
java -jar application/build/clean-architecture-example.jar
- running the application (on the fly):
./gradlew bootRun
- running the application (in the IDE): open and run the main class
com.clean.example.configuration.Application
- more info on available tasks:
./gradlew tasks

Once the application is running, you can:
- open <http://localhost:8080/broadbandaccessdevice/device1.exlon.com/> and you should see some json
- look at the log and you should see a scheduled job running every 5 seconds (it prints something like _"Job Starting: ReconcileBroadbandAccessDeviceJob..."_)

## The example domain
**TODO**
Explain what the domain used in the example is

## Resources
**TODO**
