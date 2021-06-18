#Loyalty Program

 Using a public* endpoint, a client is able to reserve a room (if there are availabilities). 
 
If he has enough bonus points, the status of this room <-> user relation changes to "RESERVED" and bonus points are subtracted.

If he does not have enough bonus points, the status changes to “PENDING_APPROVAL”.

Each modification to a room availability, reservation or the amount of bonus points of a user is being tracked in a database separately.

Room status changes trigger a text based email to notify us (as service owner) of a change.

API authentication happens with two static API-Keys you define (one for public, one for restricted endpoints)
