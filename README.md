# Personality Test Application REST Api
This is a Personality Test backend service demo application. 
It is aimed to work together with a Front End application. 
However you can test it by directly sending Rest commands. Details are given in this document.
<br /><br />
You can get pre-defined questions, send your answers, and query the previous answers.
Questions and Answers are saved in embedded H2 database so that you can connect and query tables. 
Please be aware that database is accessible only when the application is running and Answers will be cleared when it stops.

### Tech Stack 
This is a Maven Spring Boot Web Application with an embedded H2 database. Spring Data Jpa is used to access the database. 
It runs in RAM and gets cleared when the application terminates.
Question tables are initialized in resources\data.sql file. 
You can connect to local H2 database with a browser on http://localhost:8080/h2-console while the application is running.
JDBC URL should be set to **"jdbc:h2:mem:testdb"** to connect. <br />
User Name is **sa** ; Password is empty
### How to Install and Run
You need to install maven in your local computer. Then you may run
<pre>mvn clean install      to download dependencies and build project
mvn spring-boot:run    to run the application</pre>

### How to Docker Run
Alternatively you may run the application on a docker container. For this you need to install Docker in your local computer. 
<pre>mvn clean install                                       to download dependencies and build jar file
docker build -t spring-boot-personality-app.jar .          to build docker image
docker run -p 8080:8080 spring-boot-personality-app.jar    to run the image</pre>

### How to Use the Api

**Get categories:**
<pre>GET localhost:8080/question/category   
Response:
{
    "categoryList": [
        "hard_fact",
        "introversion",
        "lifestyle",
        "passion"
    ]
}
</pre>

**Get questions by category:**
<pre>GET localhost:8080/question/categoryId/hard_fact   
Response:
{
    "idList": [
        1,
        2,
        3,
        5,
        9
    ]
}
</pre>
Conditional questions are not listed here. Because they are asked according to the answers of other questions.

**Get a question by its id:**
<pre>GET http://localhost:8080/question/3   
Response:
{
    "id": 3,
    "questionText": "How important is the age of your partner to you?",
    "category": "hard_fact",
    "questionType": "single_choice_conditional",
    "options": [
        "not important",
        "important",
        "very important"
    ],
    "expectedAnswer": "very important",
    "conditionalQuestionId": 4
}
</pre>

**Answer a question:**
<pre>POST localhost:8080/answer   
Request body:
{
    "userId": "usr0002",
    "questionId": 3,
    "answerText": "very important"
}
Response:
{
    "id": 1,
    "userId": "usr0002",
    "questionId": 3,
    "answerText": "very important",
    "conditionalQuestionId": 4
}
</pre>
In this example this is a **single_choice_conditional** type of question and we gave the expected answer. 
As a result the **conditionalQuestionId** field shows the next question to be answered. 
Front End code should check this value and direct the user to answer that question next.<br /><br />

**Query the answer to a specific question:**
<pre>GET localhost:8080/answer/userId/usr0002/questionId/3
Response:
{
    "id": 1,
    "userId": "usr0002",
    "questionId": 3,
    "answerText": "very important",
    "conditionalQuestionId": 4
}
</pre>

**Query all the answers of a user:**
<pre>GET localhost:8080/answer/userId/usr0002
Response:
[
    {
        "id": 1,
        "userId": "usr0002",
        "questionId": 3,
        "answerText": "very important",
        "conditionalQuestionId": 4
    },
    {
        "id": 2,
        "userId": "usr0002",
        "questionId": 4,
        "answerText": "20",
        "conditionalQuestionId": null
    }
]
</pre>

### Error Cases

If we reply a single_choice question with an answer that is not one of the options then we get following response:
<pre>POST localhost:8080/answer   
Request body:
{
    "userId": "usr0002",
    "questionId": 3,
    "answerText": "highly important"
}
Response:
{
    "status": 500,
    "error": "Internal Server Error",
    "message": "Your answer should be one of those: [not important, important, very important]"
}
</pre>

If we reply a number_range question with an answer that is not within the range then we get following response:
<pre>POST localhost:8080/answer   
Request body:
{
    "userId": "usr0002",
    "questionId": 4,
    "answerText": "200"
}
Response:
{
    "status": 500,
    "error": "Internal Server Error",
    "message": "Your answer should be between 18 and 140"
}
</pre>

If we try to reply a question a second time this is not allowed and we get following response:
<pre>POST localhost:8080/answer   
Request body:
{
    "userId": "usr0002",
    "questionId": 4,
    "answerText": "35"
}
Response:
{
    "status": 500,
    "error": "Internal Server Error",
    "message": "User already answered this question, answer is: 20"
}
</pre>





