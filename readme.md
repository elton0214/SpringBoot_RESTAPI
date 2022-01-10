**Register/SignIn**

* register (post)
  * register a user
  * if success, response {message="Registration successful"}
  * if failure, response {message="Password or username policy failed"}

* signin (post)

  * be able to sign in through **email** and **password**

  * if success, response: 

    {message="Authentication successful!",

    

    token = **JWToken**,

    id=user.Id};

  * if failure, response {message="Username or Password is incorrect."}

**Profile(ApplicationUser) api**

* editprofile/{userid} (get)
* viewprofile/{userid} (get)
  * return the details of all users

**Patient api**

* patients/register (post)
  * if success, response {message="Registration successful"}
  * if failure, response {message="Registration failure"}

* patients/list/ (get)
  * return the list of all patients 
* patients/view/{id} (get)
  * return the details of a specified patient id
* patients/delete/{id} (delete)
  * delete a specified patient id

**Appointment api**

* appointment/register (post)

  		* if success, response {message="Booking successful"}
	
  		* if failure, response {message="Booking failure"}

* /appointment/list/ (get)

  * return the list of all appointments

* /appointment/view/{appointmentId} (get)

  * return the details of a specified appointment id

* /appointment/list/{patientid} (get)

  * return the list of all appointments of a specified patient id

* /appointment/delete/{appointmentId} (delete)

  * delete a specified appointment Id

**Middleware**

create a Middleware to validate the api calls using JSON Web Token

* **Register/SignIn** api do not require Middleware
* if login in validated, send response with the JWT as token parameter