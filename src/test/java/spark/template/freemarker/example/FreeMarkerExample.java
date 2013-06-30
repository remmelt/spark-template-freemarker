package spark.template.freemarker.example;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerRoute;

public class FreeMarkerExample {

    public static void main(String args[]) {

        get(new FreeMarkerRoute("/hello") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("message", "Hello World!");
                
                // The hello.ftl file is located in directory:
                // src/test/resources/spark/template/freemarker
                return modelAndView(attributes, "hello.ftl");
            }
        });

    }

}