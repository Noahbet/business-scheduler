# scheduler-for-businesses

## 1. Problem Statement

Smaller business sometimes do not always have enough time and/or money to make a website of their own which can hinder their ability to be seen in the community and attract clients. They also may be using outdated scheduling software or paper planners that can become unwieldly with time.

## 2. Technical Solution

### Scenario 1
Thoma the barber is not able to make a website of his own and is new to the city he is currently in. By registering for Appoints, he is able to create a dashboard for his business and display all the services he offers as a barber. His business slowly gets raving reviews and grows exponentially by being displayed near the top of the hairstyle category on Apppoints.

### Scenario 2
Diluc the dentist has trouble keeping up with his increase in clients' appointments. He used to have clients call him and he would write the appointments down in a scheduler but recently he has been getting calls even when he doesn't have it with him. He registers for Appoints and enters his availability per day. Now he has a site where he is able to track who is registered for what day and his customers have the ease of scheduling appointments from their phone.

## 3. Glossary

### Customer
The current customer logged into the site. Can make apppointments with business for a business' service.
### Owner
The current owner for a business that is logged in. Can check who is scheduled with the business they are associated with and can cancel customer appointments as well. (May add employee)
### Business
The business which should be associated with a category and services. Customers can schedule appointments with them for a service they offer.
### Services
The servies offered by a specific business. Should be associated with a business, time length for the service, downtime for after the service, and price. (May add person who service is scheduled with)
### Appointments
Displays who the appointment is with, who scheduled the appoinment, what time/date is is, and the service it is for.
### Rating
The average rating by customers for a specific business.
### Notification
Notify customer or owner if an appointment was canceled. (Maybe add reason.)

## 4. High Level Requirement

- Create an appintment (CUSTOMER, OWNER).
- Cancel an appointment (CUSTOMER, OWNER).
- See all scheduled appointments by self (CUSTOMER, OWNER).
- Search available businesses (anyone).
- Search by category (anyone).
- See next available appointments (anyone).
- See all scheduled appointments for self (OWNER).
- Cancel customer appointment (OWNER).
- Set working hours for specific days (OWNER).
- Edit working hours for specific days (OWNER).
- Set services offered by business (OWNER).
- Edit services offered by business (OWNER).
- Upload picture for business dashboard (OWNER).
- (Maybe add) Confirm appointment (OWNER).

## 5. User Stories/Scenarios

### Create an Appointment

Create an appointment for a business.

Suggested data:
- date and time of appointment be selected
- business appointment is with
- service which appointment is with

**Precondition**: User must be logged in with the CUSTOMER or OWNER role.

**Post-condition**: Appointment may have to have appointment confirmed by business owner if added.

### Cancel an Appointment (Customer)

Can cancel an appointment in the future.

**Precondition**: User must be logged in with the CUSTOMER or OWNER role. Appointment datetime must be in the future.

**Post-condition**: Should notify owner that appointment was canceled.

### Cancel an Appointment (Owner)

Can cancel an appointment in the future.

**Precondition**: User must be logged in with the OWNER role. Appointment datetime must be in the future.

**Post-condition**: Should notify customer that appointment was canceled.

### Browse Businesses

Search through available business and should display business cards.

- Search query

**Precondition**: None

**Post-condition**: None

### See Next Available Appointments

Should be display when a service for a business is clicked.

**Precondition**: None

**Post-condition**: None

### Upload a Backdrop

Owner uploads a picture for business dashboard.

Suggested data:
- a picture file

**Precondition**: User must be logged in with the OWNER role.

**Post-condition**: None
