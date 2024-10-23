the app contains

-main activity which contains fragment weather fragment

-weather fragment is communicate with get city repo and get weather repo to get the drop down list of cities which are placed in weather fragment 
-internet status interface is used to manage the operation between repo and source for getting weather and cities

-cities repo, weather repo are used as a data layer to be middleware between view model and the data layer

-room converter is used with Room database to convert from JSON to string and vice versa 

- Dao (city, weather) are used to represent the table queries related to get and insert items inside the DB

-Network Module, Network Module II are represent retrofit builder with 2 different domains one for cities 
-second one for weather forecast
