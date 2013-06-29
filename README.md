spark-template-freemarker - Freemarker template view route for Spark.
==============================================


How to use the FreeMarker template route for Spark
---------------

```java
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
                attributes.put("message", "Hello FreeMarker");
                
                // The ftl files need to be located in the directory:
                // {resources-dir}/spark/template/freemarker
				// hence in maven: src/main/resources/spark/template/freemarker
                return new ModelAndView(attributes, "hello.ftl");
            }
        });

    }

}
```
