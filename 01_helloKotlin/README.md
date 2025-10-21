# DSA Assignment 1: Hello Kotlin

## Project Overview

For this assignment, I chose to rewrite part of a SoftSys unit testing and typing assignment in Kotlin. In this assignment, you are given a functioning and nonfunctioning version of a few different functions and asked to write unit tests for each. Because SoftSys is all in C, this assignment lets you explore the types of mistakes C will let you get away with, which shows the necessity of unit testing.

## Reflection on Kotlin Conversion

This was an interesting script to rewrite in Kotlin because, as I've discovered, Kotlin is a much safer language than C. In the original assignment, the incorrect version of one function fails because of irresponsibility with type-checking. When recreating these functions and their unit tests in Kotlin, I realized that Kotlin actually prevented me from making that typing mistake in the first place. It reminded me of how much I like statically typed languages (as I have been doing a lot of Python recently) and also the convenience of higher-level languages. However, it did make this assignment a little tricky because I had to make Kotlin's type-checker happy, even though arguably the point of the original assignment was to leave it unhappy and prove functionality through the unit tests.

One thing that I find intimidating about Kotlin so far is the inability to ignore anything related to building and dependencies. There are a lot of build-related files in the Kotlin sample project, and I feel like I never know how confident I should be with editing those directly. I also want to learn more about the Kotlin unit testing framework, particularly how to best connect test files with source files and how unit tests interact with the interactive debugger in a Kotlin programmer's workflow.