Feature: Bulk Processing and Admin Tools

	Scenario: Verify "Select All" functionality via header checkbox
		Given User should be logged in
		And User has navigated to the Companies dashboard
		When User clicks the "Select All" checkbox in the table header
		Then all checkboxes in the company data grid rows should be checked
		And the "Actions" dropdown menu should be visible

	Scenario: Conditional visibility of the Actions dropdown
		Given User should be logged in
		And User has navigated to the Companies dashboard
		And no company records are currently selected
		Then the "Actions" dropdown menu should not be visible
		When User selects a single company record checkbox
		Then the "Actions" dropdown menu should be visible

	# Scenario: Execute "Bulk Delete" for multiple records
	# 	Given User should be logged in
	#	And User has navigated to the Companies dashboard
	#	When User selects multiple company records
	# 	And User selects the "Delete" option from the Actions dropdown
	# 	And User clicks the execution checkmark button
	# 	Then the selected records should be removed from the data grid
	# 	And the record count label should update accordingly

	# Scenario: Execute "Assign to User" for multiple records
	# 	Given User should be logged in
	#	And User has navigated to the Companies dashboard
	# 	When User selects multiple company records
	# 	And User selects the "Assign to user" option from the Actions dropdown
	# 	And User selects "Snehal Ghosh" from the target user list
	# 	# And User clicks the execution checkmark button
	# 	Then the ownership of the selected records should be updated in the system