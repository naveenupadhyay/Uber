
Requirements :
	1. Java 7 +
	2. Maven 3.3+
	3. Internet connection

Run : mvn spring-boot:run
Test : mvn clean test
The test cases test all restful endpoints.
Current test coverage is 82% and the results can be found in : Uber/target/site/jacoco/index.html


UberRestService class provides various RESTFUL endpoints to demo a functional uber product.
It provides following functionalities
- onboard Cabs.
- onboard Riders.
- book Cab.
- cancel Cab.
- updateCabLocation 
- cancel Booking.

class Location :
Furthermore the solution assumes a spatial region to which the Uber app caters too.
Spatial region has coordinates but since these coordinates are very granular and hence the user and cab might not be at the same point,
its imperative that we aggregate such coordinates into Polygons . See class Location which does that.

	
	
	