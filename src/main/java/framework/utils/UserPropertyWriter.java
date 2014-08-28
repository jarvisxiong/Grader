package framework.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrew on 2/11/14.
 */
public class UserPropertyWriter {
    private HashMap<String, String> properties;

    public UserPropertyWriter() {
        properties = new HashMap<String, String>();
    }

    public UserPropertyWriter(String defaultProperties) {
        this();
        setupDefault(defaultProperties);
    }

    public void setUserProperties(String... userProperties) {
        for(int i = 0; i < userProperties.length; i ++) {
            String propName = null;
            String propValue = null;

            userProperties[i] = userProperties[i].trim();
            switch (userProperties[i]) {
                case "--use-framework-gui":
                    propName = "grader.controller.useFrameworkGUI";
                    propValue = "true";
                    break;
                case "--no-framework-gui":
                    propName = "grader.controller.useFrameworkGUI";
                    propValue = "false";
                    break;
                case "--project-requirements":
                    propName = "project.requirements";
                    propValue = userProperties[++i].trim();
                    break;
                case "--project-name":
                    propName = "project.name";
                    propValue = userProperties[++i].trim();
                    break;
                case "--grader-controller":
                    propName = "grader.controller";
                    propValue = userProperties[++i].trim();
                    break;
                case "--headless-path":
                    propName = "grader.headless.path";
                    propValue = userProperties[++i].trim();
                    break;
                case "--headless-start":
                    propName = "grader.headless.start";
                    propValue = userProperties[++i].trim();
                    break;
                case "--headless-end":
                    propName = "grader.headless.end";
                    propValue = userProperties[++i].trim();
                    break;
                case "--logger":
                    propName = "grader.logger";
                    propValue = userProperties[++i].trim();
                    break;
            }

            if (propName != null) {
                properties.put(propName,propValue);
            }
        }
    }

    private void setupDefault(String defaultProperties) {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration(defaultProperties);
            Iterator<String> keys = configuration.getKeys();
            while(keys.hasNext()) {
                String key = keys.next();
                properties.put(key, configuration.getString(key));
            }
            /*if (configuration.containsKey("project.requirements")) {
                properties.put("project.requirements", configuration.getString("project.requirements"));
            }
            if (configuration.containsKey("project.name")) {
                properties.put("project.name", configuration.getString("project.name"));
            }
            if (configuration.containsKey("grader.controller")) {
                properties.put("grader.controller", configuration.getString("grader.controller"));
            }
            if (configuration.containsKey("grader.headless.path")) {
                properties.put("grader.headless.path", configuration.getString("grader.headless.path"));
            }
            if (configuration.containsKey("grader.headless.start")) {
                properties.put("grader.headless.start", configuration.getString("grader.headless.start"));
            }
            if (configuration.containsKey("grader.headless.end")) {
                properties.put("grader.headless.end", configuration.getString("grader.headless.end"));
            }
            if (configuration.containsKey("grader.logger")) {
                properties.put("grader.logger", configuration.getString("grader.logger"));
            }
            if (configuration.containsKey("grader.controller.useFrameworkGUI")) {
                properties.put("grader.controller.useFrameworkGUI", configuration.getString("grader.controller.useFrameworkGUI"));
            }*/
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void writeUserProperties(File userPropertiesFile) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(userPropertiesFile));
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                bw.write(entry.getKey() + "=" + entry.getValue());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeUserProperties(String userPropertiesFile) {
        writeUserProperties(new File(userPropertiesFile));
    }
}
