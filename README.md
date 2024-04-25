# P6-Full-Stack-reseau-dev

## Front

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

Don't forget to install your node_modules before starting (`npm install`).  

## Database
I'm using MySQL Database.  
You can find the script right there in the folder `sql/`  
To install the database, start MySQL and type : `mysql -u root -p mdd < Table\ projet.sql`  

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.
Run `mvn clean install` to install the java part.  

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.
Run `mvn spring-boot:run` to build the java part.

### Change properties

Java - Database URL, username and password are defined in application.properties as environment variable. You will have to set them in your IDE or replace directly by your URL, username and password.  
Angular - Go to src\proxy.config.json and change the target if necessary.