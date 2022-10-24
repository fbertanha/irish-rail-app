# Irish Rail App

The purpose of this app is show the trains forecast for the Irish Rail system using the [Irish Rail API](https://api.irishrail.ie/realtime/).

### How the app works

When the app is opened a request for the `getStationDataByCodeXML?StationCode=[perse|howth]` is made automaticaly, the `StationCode` will follow the rules below:
- If the device's time is between `00:00 - 12:00` the app will only show trains from `Pearse Street` station towards to `Northbound`.
- If the device's time is between `12:01 - 23:59` the app will only show trains from `Howth` station towards to `Southbound`.


### Tools and libraries
- To build this app I used MVVM with Clean Architecture.
- [Retrofit](https://square.github.io/retrofit/) with Gson for rest apis calls.
- [Dagger-Hilt](https://dagger.dev/hilt/) for DI.
- [JUnit4](https://junit.org/junit4/) for unit test.
- [Mockk](https://mockk.io/) for make easy the unit tests with `every`, `mockk`, etc.
- [Truth](https://github.com/google/truth) for more readable assert.

### Running the app

Just clone and run, no configuration is required.
