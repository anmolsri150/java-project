+ spring-boot-vuejs
/*
 * Developed by Anmol Srivastava - 189303035 and Vedant Gupta - 189303135
 * Uses Maven for project config and management of frontend and backend together 
 *   -- REFERENCE for maven build (https://github.com/jonashackt/spring-boot-vuejs)
 * Backend on Spring Boot
 * Frontend on Vue.js
 * Authentication and Database Provider -- Firebase
 * 
 */

Requires Maven
	- Extract the included maven zip and add it to PATH
	- Checkout this guide https://howtodoinjava.com/maven/how-to-install-maven-on-windows/ to install maven
	
Run install.bat to install maven, java and js dependencies
	-- or run "mvn clean install"
	
Run run.bat to start server
	- Or Open command prompt in the root directory of project and run the following command
	- "mvn --projects backend spring-boot:run"

Server will be running at http://localhost:8098

FRONTEND LOGIN CREDENTIALS
	-- ADMIN USER
		mail - anmol150srivastava@gmail.com
		pass - javaproject
	-- KITCHEN USER
		mail - anmol1901srivastava@gmail.com
		pass - javaproject

Api Endpoints 
- /api/data
	- /menu -- Returns all menu items in json format
	- /menu/{query} -- Returns menu items from the queried category
	- /menu/addItem -- Adds an item to menu
						Send Format
							{
								"name": "Tomasaaaaaaaadasdato-piasdzza",
								"category": "italian",
								"desc": "PIJJA",
								"price": 340
							}
	- /menu/deleteItem/{id} -- Deletes Item of* the specified Id
								- GET request
	-/menu/updateItem -- Updates the info of a 
- 