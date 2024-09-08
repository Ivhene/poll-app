# DAT250 Assignment 2 - Poll App

## Introduction
This repository contains my solution to the poll app api assignment in DAT250 at HVL. You can read about the assignment itself [here](https://github.com/selabhvl/dat250public/blob/master/expassignments/expass2.md). In this document I will go over what has been done, the problems I encountered while making the solution and what is left to do in order to make the solution complete.

## What has been done
As part of this assignment, I have made my solution for an api used in a poll app. The solution was made with spring boot, and I have divided the classes into 3 main packages
 - **Controller** - Contains all of the Controller classes. Decided to have 1 controller for each resource.
 - **Entity** - Contains all the entities (resource) classes. These are the objects as defined in the domain model (see task description)
 - **Repo** - Contains the class used to store resources in memory. This could have also been defined as a service package, but decided to have it as a repo.

### Test cases
There were a couple of test cases defined that the API had to cover in order to be completed. In some test cases I have decided to skip some of the checks on if something was created. I only do this if I feel like another test case already tests this, to avoid doubling up on some checks.
#### Case 1
- Create a new user
- List all users and check if the newly added user exist
- Create another user
- List all users again and check if both created users exist
- The first user creates a new poll
- List all polls and check if the new poll exist
- The second user votes on the poll
- The second user changes their vote on the poll
- List all votes and verify that only the most recent vote exist
- Delete the poll
- Check that all votes are deleted

#### Case 2
 - Create a new user
 - Create a new poll
 - Edit the poll
 - Get the poll by its ID and check if it matches the edited poll
 - List all voteoptions and check if they exist
 - Delete the poll
 - List all polls and check if it is empty
 - Check if the polls assosiated with the user is an empty list
 - Check that the voteoptions were deleted

#### Case 3
 - Create a new user
 - Get the user by username and check that it matches the created user
 - Edit the users email
 - Get the user by username and check that it matches the edited user
 - Edit the users username
 - List all users and check that it only contains the user with a new username

#### Case 4
- Create a new user
- Create another user
- The first user creates a new poll
- The second user votes on the poll
- List all votes and verify that the vote exist
- Delete the vote
- List all votes and verify that the vote is deleted
- Check that the voteoption on the poll no longer has a vote
- Check that the user do not currently have a vote registered


## Problems
The only real problem I encountered was when figuring out how to define the JSON objects without having infinite cycles of objects. I ended up solving this by using the ```@JsonManagedReference``` and ```@JsonBackReference```. There were still some struggles with this, as initially I got some errors from Spring being unable to find the reference pairings. I eventually figured out that this was due to all the references being unnamed, as Spring saw multiple references that shared the same identifier. This was solved by putting identifiers on all the references.

## Remaining work
To start off, I did not do the optional API Documentation step before submitting. If it is done when you look at this, then I decided to do it for myself at a later point. Other than that, the only things I would try to get done with more time is to test more, as I do believe that there are still some places where I have not correctly defined adding or removing the linked resources (A Vote might not always have the updated voteoption, if i edit the voteoptions, what happens to their votes, etc). I think there is more test cases like these that I could make in order to make the solution "complete". 
