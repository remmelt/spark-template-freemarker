/*
 * Copyright 2013 - Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import spark.ModelAndView;
import spark.TemplateViewRoute;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Renders HTML from Route output using FreeMarker.
 * 
 * FreeMarker configuration can be set with the {@link FreeMarkerRoute#setConfiguration(Configuration)}
 * method. If no configuration is set the default configuration will be used where
 * ftl files need to be put in directory spark/template/freemarker under the resources directory.
 * 
 * @author Alex
 * @author Per Wendel
 */
public abstract class FreeMarkerRoute extends TemplateViewRoute {

    /** The FreeMarker configuration */
    private Configuration configuration;

    /**
     * Creates a FreeMarkerRoute for a path
     * 
     * @param path The route path which is used for matching. (e.g. /hello, users/:name) 
     */
    protected FreeMarkerRoute(String path) {
        super(path);
        this.configuration = createDefaultConfiguration();
    }

    /**
     * Creates a FreeMarkerRoute for a path and accept type
     * 
     * @param path The route path which is used for matching. (e.g. /hello, users/:name) 
     * @param acceptType The accept type which is used for matching.
     */
    protected FreeMarkerRoute(String path, String acceptType) {
        super(path, acceptType);
        this.configuration = createDefaultConfiguration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();

            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);
            
            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    /**
     * Sets FreeMarker configuration.
     * Note: If configuration is not set the default configuration
     * will be used.
     * 
     * @param configuration the configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration createDefaultConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreeMarkerRoute.class, "");
        return configuration;
    }

}
