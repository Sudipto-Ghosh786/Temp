import java.lang.reflect.Field;
import java.util.*;

public class AdvancedFieldComparator {

    public static void main(String[] args) {
        compareFields("input1.ClassA", "input2.ClassA");
    }

    public static void compareFields(String classAName, String classBName) {
        try {
            Class<?> classA = Class.forName(classAName);
            Class<?> classB = Class.forName(classBName);

            Map<Class<?>, Set<String>> fieldsA = getFields(classA);
            Map<Class<?>, Set<String>> fieldsB = getFields(classB);

            compareClassFields(classA, fieldsA, fieldsB);
            compareClassFields(classB, fieldsB, fieldsA);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Class<?>, Set<String>> getFields(Class<?> clazz) {
        Map<Class<?>, Set<String>> fieldsMap = new HashMap<>();
        Set<String> fields = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
            if (!field.getType().isPrimitive()) {
                Map<Class<?>, Set<String>> nestedFields = getFields(field.getType());
                fieldsMap.putAll(nestedFields);
            }
        }
        fieldsMap.put(clazz, fields);
        return fieldsMap;
    }

    private static void compareClassFields(Class<?> clazz, Map<Class<?>, Set<String>> fieldsA, Map<Class<?>, Set<String>> fieldsB) {
        Set<Class<?>> uniqueClasses = new HashSet<>(fieldsA.keySet());
        uniqueClasses.removeAll(fieldsB.keySet());

        for (Class<?> uniqueClass : uniqueClasses) {
            Set<String> uniqueFields = fieldsA.get(uniqueClass);
            System.out.println("Fields unique to " + clazz.getSimpleName() + "'s " + uniqueClass.getSimpleName() + ": " + uniqueFields);
        }
    }
}
