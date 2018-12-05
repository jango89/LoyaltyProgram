#Loyalty Program

This assignment is designed to give you a glimpse of some of the challenges you will be facing in this role. Please be aware there are no perfect solutions - for us, it's more important to see how you find solutions, process your ideas, structure your thoughts and how you make decisions.
Be creative but realistic about what's possible. We are thrilled to get to know a bit more about the way you solve tasks.
Good luck!
The goal of this challenge is to get insights into your approaches to implement and architect robust as well as maintainable and scalable services.
We recommend setting yourself a time limit of eight to twelve hours and submit your latest state as a form of minimal viable product even if everything is not 100% finished.
Scenario
For one of your next big projects, the team has decided to go with microservices. There are multiple experienced engineers with strong knowledge in NodeJS, Java, Python and PHP so all four programming languages are a viable choice for any service. You talked about maybe using Go, Rust or Elixir but agreed to these four only for now. It is your task to develop a small containerized prototype for a small part of the system using docker-compose.
This part should enable frequent travellers to bypass the regular checkout flow and reserve a hotel room using available "bonus points" when they have an account in the system. There are no given API or payload specs and you can come up with your own.
A list of available rooms might look like this for example:
     [
{
} ]
"id": "d635c003-fffc-4beb-87b8-eaf3458cf772",
"name": "Economy Single Room",
"available_amount": 10,
"required_points": 260
What the prototype should be able to handle:
Using a restricted* endpoint, one should be able to increase or decrease the amount of “bonus points” a user has
 
 Using a public* endpoint, a client is able to reserve a room (if there are availabilities)
If he has enough bonus points, the status of this room <-> user relation changes to "RESERVED" and bonus points are subtracted
If he does not have enough bonus points, the status changes to “PENDING_APPROVAL”
Each modification to a room availability, reservation or the amount of bonus points of a user is being tracked in a database separately
Room status changes trigger a text based email to notify us (as service owner) of a change
[*] "restricted" = only one of the API-Keys is working for this endpoint | "public" = both API-Keys are working
Requirements
Create at least two services using a different programming language
Only one service or gateway is accessible from outside, all other communication happens internally Some, determined by you, critical aspects are covered by tests
Assumptions
No "reservation" dates are considered for the rooms. If one is reserved, it is gone until being released. You know potential clients will call the public endpoints using REST
Each service will run on instances with a maximum of 1 CPU and 1 GB of RAM. Eventual scaling has to happen horizontally in the future, instances cannot be scaled vertically
API authentication happens with two static API-Keys you define (one for public, one for restricted endpoints)
You will not keep ownership of all services but will hand some over to other teams later You are free to choose SQL and/or NoSQL depending on what you see would fit
If a user relation/info is required we always use
About your delivery
Make sure to create a single compressed zip folder which contains all folders/files, instructions of how to get the project running, and a description of the setup you came up with.
                 {
 "id": "b9fa7d71-548c-4739-8e6a-0931cc218cd1",
 "name": "Jane Doe",
 "role": "USER"
}
