>#### About
This is basic Spring Boot Rest POC with Spring-Data JPA implementaion. use dependency as below 
- spring-boot-starter-data-jpa -2.7.13
- spring-boot-starter-web -2.7.13
- spring-boot-devtools -2.7.13
- mysql-connector-java - 5.1.40
- Java SE 1.8

 if we use @OneToOne only on employee unidirection and try to save employee nested department then we will get below error
> org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing :
- to resolve above issue we have to use cascade =cascadeType.All which means it will perform all the releated operation atomatically.
i.e it will save nested detartment object first and then save employee and update foreign key in employee table.
it will do all the opeeation like delete employee will delete nested department also.

- to perform bidirectional in this case , we have to declaire Employee in department and to avoid eid foreign key in department table
  means to avoid genration of extra column in department table we have to use `mappedBy="department"` where department is Department field declaire in employee class.
  here mapedBy means it will understand it has to manage only one coulmn by combining two table.
    
##### Jackson - Bidirection Relationship
 - it is used to manage ownership of relashion on using bidiection mapping.
 - to resolve infinity retrive isssue  when we use bidirectional mapping .
  #### `@JsonManagedReference`
     - apply  on parent field or on owner of the relation.
  #### `@JsonBackReference`
     - apply on child field.






