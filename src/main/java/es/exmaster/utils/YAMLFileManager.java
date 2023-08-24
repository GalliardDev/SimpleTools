package es.exmaster.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class YAMLFileManager {
    public static Map<String, Boolean> readYAML(String filePath) {
        Map<String, Boolean> yamlData = new HashMap<>();
        
        try (FileInputStream input = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(input);
            
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() instanceof Boolean) {
                    yamlData.put(entry.getKey(), (Boolean) entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return yamlData;
    }
    
    public static void writeYAML(Map<String, Boolean> data, String filePath) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Para una salida más legible

        Yaml yaml = new Yaml(options);

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
