# Habitat Supervisor Explorer
    
 Habitat Supervisor Explorer is a client for Habitat Supervisor. It use Habitat Supervisor API [https://www.habitat.sh/docs/glossary/#supervisor-rest-api]
  
 Application based on Spring Boot 2. 

###Requirements
   
 For building and running the application you need:

    JDK 1.8
    Maven 3

###Build
    
  For build application use Maven command:
  
    mvn package
         
###Run

 There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method class from your IDE.
 
 Alternatively you can use JRE like so(JAVA_HOME must be set):
       
       java - jar artifact.war --bastion.endpoint=BastionURL --bastion.endpoint.bearer=BastionBearerToken
 
###Copyright
 Released under the Apache License 2.0. See the LICENSE file.    


