# A Simple Bank API

### Description

A simple API to be used for opening a new current account of already existent customers.

### Technology Stack

* Java 8
* Spring Boot
* Spring MVC
* Java Persistence API(JPA)
* Hibernate
* JAVA Server Faces(JSF)
* Derby In-Memory Database
* HTML
* CSS
* JUnit
* Mockito
* Maven

### Requirements
* The API will expose an endpoint which accepts the user information (customerID, initialCredit).
* Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.
* Also, if initialCredit is not 0, a transaction will be sent to the new account.
* Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

### Note
Lombok is a powerful plugin to prevent boilerplate codes.
To added it to IntelliJ

    Preferences (Ctrl + Alt + S)
        Build, Execution, Deployment
            Compiler
                Annotation Processors
                    Enable annotation processing

Make sure you have the Lombok plugin for IntelliJ installed!

    Preferences -> Plugins
    Search for "Lombok Plugin"
    Click Browse repositories...
    Choose Lombok Plugin
    Install
    Restart IntelliJ

