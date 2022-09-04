Feature: Test register, login, search ,add,delete functionalities

Background:
					Given the user is on MyBooks home page
					
Scenario: Create a New User 
					When user clicks on register   
					And user enters the following details 
					And user clicks register user button
					Then user should be registered

Scenario: Verification of Login Function
					When user enters user id and password
					And user click log in button
					Then user should be logged in
				
Scenario: Verification of books search feature
					Given user is logged in				
					When the user enters a bookname
					And presses enter button
					Then the basic search result should be displayed
				
					 
Scenario: Verification of my-profile update feature
					Given user is logged in	
					When the user clicks on myprofile tab
					And changes user details
					And clicks on update profile button
					Then user details should be updated
 
Scenario: Verification of adding books to favourites
					Given user is logged in
					When the user enters a bookname
					And presses enter button
					And clicks on add to favourite button
					Then book should be added to favourites
					
Scenario: Verification of deleting books from favourites
					Given user is logged in
					When clicks on favourite tab
					And deletes a book from favourites
					Then book should be deleted from favourites