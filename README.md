[![build](https://snap-ci.com/leguimas/kontrola/branch/master/build_image)](https://snap-ci.com/leguimas/kontrola/branch/master)

#kontrola

kontrola is a simple system that help you to monitoring issues in a project. You can create any issue that you want and define color status (RED, YELLOW and GREEN). Also you can define a time to expire this information. When an issue had your color status expired, someone has to update the status to the real information and explain the reason.

##api

kontrola also provides a REST API so you can integrate your system to kontrola. To check the API services, check the [API Documentation](http://kontrola-web.appspot.com/doc/api/index.html).

##contribute

If you want to contribute with kontrola you will be very welcome. kontrola was designed to use [Google Application Engine](https://appengine.google.com/). So you can use this follow maven goals:
```
mvn eclipse:eclipse
```
```
mvn appengine:devserver
```