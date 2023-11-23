package parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {

    public Map<String, Object> objects = new HashMap<>();

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        this.objects = objects;
    }

    public void register(String key, Object object) {
        objects.put(key, object);
    }

    public Object get(String key) {
        return objects.get(key);
    }

}
