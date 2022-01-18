# Inventory Management - Shopify Backend Developer Intern Challenge - Summer 2022
 An inventory tracking web application for a logistics company as a part of  Shopify Backend Developer Intern Challenge - Summer 2022

This repo consists of both frontend and backend code.

# Frontend
- Frontend was developed using HTML, CSS, JS and Bootstrap
- It doesn't require any special environment setup. You just need a web browser(preferrably Google Chrome).
- Download the repo and the frontend code is present inside the UI folder.
- After the backend server is up and running(which we will discuss below), Double-click the index.html file to access the UI.

# Backend Setup
- Backend was developed using Spring Boot. I've used H2 database(Embedded DB/ In-Memory DB) to avoid hassle of DB setup.
- Download and install Eclipse IDE (https://www.eclipse.org/downloads/packages/release/luna/sr2/eclipse-ide-java-ee-developers)
- Search and Install Spring Tools 4 plugin from the Eclipse Marketplace (Eclipse IDE -> Help -> Eclipse Marketplace)
- Import the repo (shopifychallenge folder) into the workspace as a Maven Project (Eclipse IDE -> File -> Import -> Import Existing Maven Projects -> Select the shopifychallenge folder).
- Upon importing, the project downloads all the required dependencies.
- Once the project setup is complete, right click the project -> Run As -> Spring Boot App. This should start the application in the localhost with port 9898.


The front-end code is already integrated with the back-end application. The UI will show up some dummy data upon back-end server is up and running. 

# Troubleshooting:

- If the back-end code shows errors upon importing:
- It can be due to the project build. This can be fixed by Clicking Eclipse IDE -> Project -> Clean -> Select the project and Click on Clean.

- If the port is already in use error occurs when trying to start the application.
- The default port is 9898. You can change the port by modifying the value of 'server.port' in application.properties file in the back-end code and value of itemsUrl in index-html file in the front-end code.
