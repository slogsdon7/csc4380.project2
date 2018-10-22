## Model Notes
* The controller should run
 `ConnectionFactory.createTables()` 
 on startup. This will create the database and/or tables if they don't exist for some reason.
* Don't directly set the ID field on any of the models. Those are all generated/used by the database.
* All models have update/delete/insert methods which perform database transactions. 
There is also a `create()` static method on each model class which is equivalent to instantiating the object and calling `insert()` on it. 


 ## Model Methods
 
 
 ### Student
     `getGrades()` will return a list of Grade objects for that student.
    
     `calculateAverage()` will return the average of that student's assignment grades
    
      
    
 ### Assignment
 ### Grade