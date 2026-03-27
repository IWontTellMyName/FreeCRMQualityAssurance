Feature: Lifecycle Operations and CRUD Entry Points
	
	Scenario: Navigate to the New Company creation form
		Given User should be logged in
		And User has navigated to Company dashboard
		When User clicks on the "Create" button in the toolbar
		Then the page URL should contain "/companies/new"
	
	Scenario: Navigate to the Edit interface for an existing record
		Given User should be logged in
		And User has navigated to Company dashboard
		When User clicks the inline "Edit" icon for a record
		Then the page URL should contain "/edit/"
	
	Scenario: Access the read-only view of a company record
		Given User should be logged in
		And User has navigated to Company dashboard
		When User clicks the inline "View" icon for the "Quantum FinTech Solutions" record
		Then the non-editable record summary details page for "Quantum FinTech Solutions" should open
	
	# Scenario: Cancel record creation and return to the grid
	# 	Given User should be logged in
	# 	And User has navigated to Company dashboard
	# 	And User is currently on the "Create New Company" form
	# 	When User clicks the "Cancel" button on the form
	# 	Then the user should be returned to the Companies data grid
	# 	And the grid should display "Showing 3 records"