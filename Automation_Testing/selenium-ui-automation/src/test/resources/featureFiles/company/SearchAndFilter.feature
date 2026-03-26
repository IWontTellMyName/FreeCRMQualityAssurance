Feature: Global Search and Filtered Data Views


	# Scenario: Successful keyword search for a specific company
	# 	Given User should be logged in
	# 	And User has navigated to the Companies dashboard
	# 	When User enters "Quantum" in the global search input field
	# 	Then the data grid should filter to display the "Quantum FinTech Solutions" record
	# 	And the record count label should update to "Showing 1 records"

	Scenario: Toggle visibility of the advanced filter panel
		Given User should be logged in
		And User has navigated to Companies dashboard
		When User clicks on the "Show Filters" button in the toolbar
		Then the hidden filtering panel should expand above the data grid
		And the button text should change to "Hide Filters"

	# Scenario: Reset search and filter state to default
	# 	Given User should be logged in
	# 	And User has navigated to the Companies dashboard
	# 	And a search for "Quantum" has been previously applied
	# 	When User selects the "Default View" option from the View dropdown menu
	# 	Then the search input field should be cleared
	# 	And the data grid should refresh to display all available company records
		
	# Scenario: Search for a non-existent company record
	# 	Given User should be logged in
	# 	And User has navigated to the Companies dashboard
	# 	When User enters "XYZ_999" in the global search input field
	# 	And User presses the Enter key
	# 	Then the data grid should be empty
	# 	And a feedback message "No records found" should be displayed to the user