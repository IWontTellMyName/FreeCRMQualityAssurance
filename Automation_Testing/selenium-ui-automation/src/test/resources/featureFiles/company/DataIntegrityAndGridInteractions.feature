Feature: Data Integrity and Grid Interaction

	@aut_SnehalGhosh
	Scenario Outline: Validate address concatenation for company records
		Given User should be logged in with "<LoginRow>"
		And User has navigated to the Company dashboard
		Then the address for "<CompanyRow>" should display as "12th Floor, Cyber City, Hyderabad, Telangana, 500081, India"
		Examples:
			| LoginRow | CompanyRow |
			| 5        | 2			|
	
	@aut_SnehalGhosh
	Scenario Outline: Verify navigation via company name hyperlink
		Given User should be logged in with "<LoginRow>"
		And User has navigated to the Company dashboard
		When User clicks on the company name "<CompanyRow>"
		Then the user should be redirected to the detailed record page for "<CompanyRow>"
		Examples:
			| LoginRow | CompanyRow |
			| 5        | 2			|
	
	# @aut_SnehalGhosh
	# Scenario Outline: Confirm record count synchronization after deletion
	# 	Given User should be logged in with "<LoginRow>"
	# 	And User has navigated to the Company dashboard
	# 	Given the current record count label displays "Showing 3 records"
	# 	When User clicks the "Delete" icon for the "Capgemini" record
	# 	And User confirms the deletion in the alert modal
	# 	Then the record count label should update to "Showing 2 records"
	#	Examples:
	#		| LoginRow | CompanyRow |
	#		| 5        | 2			|