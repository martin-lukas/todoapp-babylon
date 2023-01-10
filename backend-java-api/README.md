# Java Standard Library backend API

This is a practice app implementing a Backend API with Java Standard API only
(except Jackson library for JSON serializing/deserializing).

For the HTTP server, I'm using the com.sun.net.httpserver.HttpServer class,
which although it's a Sun class, it's established as a stable internal API.
For "REST controllers" that handle the actual API calls, I've created a hierarchy of handlers.
To add a controller, just implement the CustomHandler interface.

The resulting "REST controller" is not much more verbose
than e.g. Spring's RestController implementations, but of course much less powerful.
A big improvement could be to add a proxying class between these CustomHandlers,
and the very extensive HttpExchange object. This would clean up the handler classes even more,
moving even closer to the terseness of Spring.
