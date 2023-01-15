package org.lukas.todoapp;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.impl.MimeMapping;
import io.vertx.core.json.Json;

public class Application {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
                .requestHandler(req ->
                    req.response()
                            .putHeader(
                                    HttpHeaders.CONTENT_TYPE,
                                    MimeMapping.getMimeTypeForExtension("json")
                            )
                            .end(Json.encodePrettily("hello"))
                ).listen(8080);
    }
}
