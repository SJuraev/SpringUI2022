$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/SearchFunctionality.feature");
formatter.feature({
  "line": 2,
  "name": "Search Functionality",
  "description": "",
  "id": "search-functionality",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@search"
    }
  ]
});
formatter.before({
  "duration": 1765800,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "the user navigates to Meetup homepage",
  "keyword": "Given "
});
formatter.match({
  "location": "BasicValidationSteps.theUserNavigatesToMeetupHomepage()"
});
formatter.result({
  "duration": 10640919800,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Verify search results",
  "description": "",
  "id": "search-functionality;verify-search-results",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "the user searches for \"Grayslake Bicycle Slow Roll\" in \"Lake Bluff, Illinois, US\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "verifies the result set contains search criteria in the title",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Grayslake Bicycle Slow Roll",
      "offset": 23
    },
    {
      "val": "Lake Bluff, Illinois, US",
      "offset": 56
    }
  ],
  "location": "SearchFunctionalitySteps.the_user_searches_for_in(String,String)"
});
formatter.result({
  "duration": 3587581900,
  "status": "passed"
});
formatter.match({
  "location": "SearchFunctionalitySteps.verifies_the_result_set_contains_search_criteria_in_the_title()"
});
formatter.result({
  "duration": 1522789500,
  "status": "passed"
});
formatter.after({
  "duration": 932512000,
  "status": "passed"
});
});