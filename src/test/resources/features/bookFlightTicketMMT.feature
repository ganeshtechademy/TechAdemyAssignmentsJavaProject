Feature:  Search for flights in Make My trip application

Scenario: User is able to search for flights

Given the user to launch the application
And the user choose roundtrip option for journey
And the user selects the from "HYD"
And the user selects the to as "MAA"
And the user selects the fromdate and todate
When the user clicks on the search button
Then the flights details are displayed successfully