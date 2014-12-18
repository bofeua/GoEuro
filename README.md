Java Developer Test
The purpose of this test is to see how you approach a problem and what your solutions look like. The requirements for this test are simple and should be straightforward to grasp. When implementing a solution, please keep things simple as well. That said: Implement an API query and transform this data into a csv file Create a Java command line tool that takes as an input parameter a string

java -jar GoEuroTest.jar "STRING"

The program takes this string and queries with it our Location JSON API: The app should use this API endpoint:

http://api.goeuro.com/api/v2/position/suggest/en/STRING

Where STRING is the string that the user has entered as a parameter when calling the tool.

The API endpoint returns JSON documents like these:

[

 {

 _id: 377078,
 key: null,
 name: "Potsdam",
 fullName: "Potsdam, Germany",
 iata_airport_code: null,
 type: "location",
 country: "Germany",

 geo_position: {
 latitude: 52.39886,
 longitude: 13.06566
 },
 location_id: 377078,
 inEurope: true,
 countryCode: "DE",
 coreCountry: true,
 distance: null
 },

 {
 _id: 410978,
 key: null,
 name: "Potsdam",
 fullName: "Potsdam, USA",
 iata_airport_code: null,
 type: "location",
 country: "USA",

 geo_position: {
 latitude: 44.66978,
 longitude: -74.98131
 },

 location_id: 410978,
 inEurope: false,
 countryCode: "US",
 coreCountry: false,
 distance: null
 }
 ]
The endpoint always responds with a JSON array that contains JSON objects as elements. Each object, among other keys, has a name and a geo_position key. The geo_position key is an object with latitude and longitude fields. If no matches are found an empty JSON array is returned.

The program should query the API with the user input and create a CSV file from it. The CSV file should have the form: _id, name, type, latitude, longitude
