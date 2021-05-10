#Loyalty Program

 Using a public* endpoint, a client is able to reserve a room (if there are availabilities)
If he has enough bonus points, the status of this room <-> user relation changes to "RESERVED" and bonus points are subtracted
If he does not have enough bonus points, the status changes to “PENDING_APPROVAL”
Each modification to a room availability, reservation or the amount of bonus points of a user is being tracked in a database separately
Room status changes trigger a text based email to notify us (as service owner) of a change
[*] "restricted" = only one of the API-Keys is working for this endpoint | "public" = both API-Keys are working
Requirements

No "reservation" dates are considered for the rooms. If one is reserved, it is gone until being released. You know potential clients will call the public endpoints using REST

API authentication happens with two static API-Keys you define (one for public, one for restricted endpoints)
