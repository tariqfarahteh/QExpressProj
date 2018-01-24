# QExpress Project!

This is a solution to track shipments and to check the sold items.
We used in this solution a RESTfull API  to:

 1. Retrieve shipments, facilities etc… 
 2. Create new shipments.
 3. Filter Shipments per the from facility

# Prerequisites
For the project to run, a Database needs to be available with the structure specified in shipment_db.sql, due to time constraints the database needs to be with the following DB information:

> JDBC URL: jdbc:mysql://localhost:3306/Shipment_DB
> username: root
> password: root

Ideally the database information needs to be configurable either as environment variables or in a properties file.

## How to start the Shipment application

 1. Clone this repository `git clone https://github.com/tariqfarahteh/QExpressProj.git`
 2. Run `mvn clean install tomcat7:run` to build and start the application
 3. To check that your application is running enter url <http://localhost:9090>

# DB Structure:

A shipment contains header and details Tables
Shipment header has information about the seller, buyer, from facility, to facility and creation date
Shipment details contains the relational key to make a link between header, details and items information.

## Front End:

A single web page contains a table of shipments and there is an option to filter the facilities to retrieve shipments in the selected facility with the pagination feature enabled.

To insert a new shipment, you need to perform a post request similar to the example below:

> {
		"sellerID": 1,
		"buyerID": 1,
		"fromFacilityID": 1,
		"toFacilityID": 2,
		"items": [{
				"id": 1
			}, {
				"id\":3
			}, {
				"id": 5
			}
		]
}


## Utilities


Also, there are services to get available data for sellers, buyers and facilities to get the id’s that you can use it in insert statement.


## Technologies
The Technologies used in this project are:

 1. Maven to build the project
 2. Jersey for rest webservice
 3. Gson library to convert from and to Json
 4. C3P0 for connection pooling
 5. MySQL driver as a DB provider
