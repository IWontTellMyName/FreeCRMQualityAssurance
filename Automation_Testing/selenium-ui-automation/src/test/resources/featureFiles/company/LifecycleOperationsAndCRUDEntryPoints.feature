Feature: Lifecycle Operations and CRUD Entry Points
	
	@aut_SnehalGhosh
	Scenario Outline: Navigate to the New Company creation form
		Given User should be logged in with "<LoginRow>"
		And User has navigated to Company dashboard
		When User clicks on the "Create" button in the toolbar
		Then the page URL should contain "/companies/new"
		Examples:
			| LoginRow |
			| 5        |
	
	@aut_SnehalGhosh
	Scenario Outline: Navigate to the Edit interface for an existing record
		Given User should be logged in with "<LoginRow>"
		And User has navigated to Company dashboard
		When User clicks the inline "Edit" icon for a record
		Then the page URL should contain "/edit/"
		Examples:
			| LoginRow |
			| 5        |
	
	@aut_SnehalGhosh
	Scenario Outline: Access the read-only view of a company record
		Given User should be logged in with "<LoginRow>"
		And User has navigated to Company dashboard
		When User clicks the inline "View" icon for the "<CompanyRow>" record
		Then the non-editable record summary details page for "<CompanyRow>" should open
		Examples:
			| LoginRow | CompanyRow |
			| 5        | 2			|
	
	# @aut_SnehalGhosh
	# Scenario Outline: Cancel record creation and return to the grid
	# 	Given User should be logged in with "<LoginRow>"
	# 	And User has navigated to Company dashboard
	# 	And User is currently on the "Create New Company" form
	# 	When User clicks the "Cancel" button on the form
	# 	Then the user should be returned to the Companies data grid
	# 	And the grid should display "Showing 3 records"
	#	Examples:
	#	 	| LoginRow |
	#	 	| 5        |