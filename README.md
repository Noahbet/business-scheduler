# scheduler-for-businesses

## 1. Problem Statement

Smaller business sometimes do not always have enough time and/or money to make a website of their own which can hinder their ability to be seen in the community and attract clients. They also may be using outdated scheduling software or paper planners that can become unwieldly with time.

## 2. Technical Solution

### Scenario 1
Thoma the barber is not able to make a website of his own and is new to the city he is currently in. By registering for Appoints, he is able to create a dashboard for his business and display all the services he offers as a barber. His business slowly gets raving reviews and grows exponentially by being displayed near the top of the hairstyle category on Apppoints.

### Scenario 2
Diluc the dentist has trouble keeping up with his increase in clients' appointments. He used to have clients call him and he would write the appointments down in a scheduler but recently he has been getting calls even when he doesn't have it with him. He registers for Appoints and enters his availability per day. Now he has a site where he is able to track who is registered for what day and his customers have the ease of scheduling appointments from their phone.

## 3. Glossary

Define key domain terms. This won't map one-to-one with model classes, but it may be close.

### Example

> ### Running Club
> An organization based on a shared love of running. Clubs have members. They host runs. Some are informal with infrequent runs. Others are large, have budgets, and charge membership fees.
> ### Runner
> Anyone who signs up for a run. Runners can be members of a club, but don't have to be. All members are runners but not all runners are members.
> ### Member
> A runner who is formally affiliated with a running club. A runner can be a member of more than one club.
> ### Club Admin
> A running club member with an administrator role. They have more privileges in the Group Run application. All admins are members, but not all members are admins.
> ### Run
> A running event with a specific time, date, and location. A run may also include a route (stretch goal).

## 4. High Level Requirement

Briefly describe what each user role/authority can do. (These are user stories.)

### Example

> - Create a run (MEMBER, ADMIN).
> - Edit a future run (MEMBER, ADMIN).
> - Cancel a future run (ADMIN).
> - Approve a run (ADMIN).
> - Browse runs (anyone).
> - Sign up for a run (authenticated).
> - Apply for membership (authenticated).
> - Approve a membership (ADMIN).

## 5. User Stories/Scenarios

Elaborate use stories.

### Example

> ### Create a Run
> 
> Create a run that runners can join.
> 
> Suggested data:
> - brief description (e.g. "Saturday run along the river road.")
> - date and time (must be in the future)
> - a location (choose a level of difficulty from a single address field to a separately-tracked data entity)
> - running club identifier (runs are always attached to a club. If a runner belongs to more than one club, they may need to choose)
> - max participants (`null` for unlimited?)
> - a route (data from a map integration, if appropriate)
> 
> **Precondition**: User must be logged in with the MEMBER or ADMIN role.
> 
> **Post-condition**: If the user is a MEMBER, the run is not automatically posted. It must be approved by an ADMIN. If the user is an ADMIN, they can choose to post it immediately or keep it in a pending status.
> 
> ### Edit a Run
> 
> Can only edit a run in the future.
> 
> **Precondition**: User must be logged in with the MEMBER or ADMIN role. Run datetime must be in the future.
> 
> **Post-condition**: If the user is a MEMBER, the run is set to a pending status even if it was initially posted. If the user is an ADMIN, they can choose to post it immediately or keep it in a pending status.
> 
> ### Cancel a Run
> 
> Can only cancel a run in the future.
> 
> **Precondition**: User must be logged in with the ADMIN role. Run datetime must be in the future.
> 
> **Post-condition**: Data is not deleted. The run is set to a canceled status and is no longer visible in the public UI. It *is* visible to the admin.
> 
> ### Approve a Run
> 
> Through an administrative UI, the ADMIN user finds pending runs for their club. They can choose to: post directly, edit and post, or cancel.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: None
> 
> ### Browse Runs
> 
> Decide how to display runs to anyone who uses the application.
> 
> - Text-based: Users filter by date and location. Display results as HTML with action UI to sign up.
> - Calendar-based: Users page through a calendar UI. Limit by location or manage the UI so there's not 200 runs on a single day.
> - Map-based: Users navigate to different locations to see future runs as pins on the map.
> 
> **Precondition**: None
> 
> **Post-condition**: None
> 
> ### Sign Up for a Run
> 
> Once a runner finds a run they're interested in, they can sign up.
> 
> **Precondition**: User must be logged in. The run must not be over-capacity. The runner cannot already be registered for the run.
> 
> **Post-condition**: Runner is registered for the run.
> 
> ### Apply for Membership (Optional)
> 
> If a runner enjoys a club's runs, they may wish to join the club. Give them an easy way to apply for membership.
> 
> **Precondition**: User must be logged in. The user cannot already be a member of the club.
> 
> **Post-condition**: Membership is in a pending status waiting for ADMIN approval.
> 
> ### Approve a Membership (Optional)
> 
> Through an administrative UI, the ADMIN user finds pending memberships for their club. They can choose to accept or reject the membership application.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is not deleted. The membership is set to a rejected status. This prevents the runner from applying again and again.