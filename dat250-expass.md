# DAT250 Assignment 2 - Poll App

## Introduction
This repository contains my solution to the poll app assignment in DAT250 at HVL. You can read about the assignment itself [here](https://github.com/selabhvl/dat250public/blob/master/expassignments/expass2.md). In this document I will go over what has been done, the problems I encountered while making the solution and what is left to do in order to make the solution complete.

## What has been done


## Problems
The only real problem I encountered was when figuring out how to define the JSON objects without having infinite cycles of objects. I ended up solving this by using the ```@JsonManagedReference``` and ```@JsonBackReference```. There were still some struggles with this, as initially I got some errors from Spring being unable to find the reference pairings. I eventually figured out that this was due to all the references being unnamed, as Spring saw multiple references that shared the same identifier. This was solved by putting identifiers on all the references.

## Remaining work
