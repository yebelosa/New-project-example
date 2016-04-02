# clean-architecture-example
Example project to show what Clean Architecture would look like (in Java)

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
