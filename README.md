# boot-gradle-security
If you will use the Browser (Chrome), that after the start application go to the http://localhost:8080/login name(field) - user password(field) - password and after that you will go to the http://localhost:8080/welcome There are two links - info about books and authors, when user may doing almost anything with authors and books. If you want to get the info about concrete author in json format, go for example to http://localhost:8080/authors/author/info/short/3 and in the browser you will see the info in JSON about concrete author.

Postman: 1)POST http://localhost:8080/login - with next body keys and values (because I've used jdbc authentication): "password" : "password" "username" : "user" 2)GET http://localhost:8080/authors/author/info/short/3 { "firstName": "Joan", "lastName": "Rowling", "age": 52, "books": [ "Harry Poter" ] } 3)GET http://localhost:8080/authors/author/info/short/5 { "firstName": "Olga", "lastName": "Kiev", "age": 29, "books": [ "Horror book", "Math", "Crime story" ] }


I have bag with eclipse, which don't want to pull jar with h2 to the classpath. Maybe you will have the same problem. 
The conclusion - to add manually the jar with h2 to classpath.  
