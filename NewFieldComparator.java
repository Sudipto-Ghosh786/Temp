import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class NewFieldComparator {

    public static void main(String[] args) {
        compareFields("input1.ClassA", "input2.ClassA");
    }

    public static void compareFields(String classAName, String classBName) {
        try {
            Class<?> classA = Class.forName(classAName);
            Class<?> classB = Class.forName(classBName);

            Set<String> fieldsA = getFields(classA);
            Set<String> fieldsB = getFields(classB);

            Set<String> uniqueToA = new HashSet<>(fieldsA);
            uniqueToA.removeAll(fieldsB);

            Set<String> uniqueToB = new HashSet<>(fieldsB);
            uniqueToB.removeAll(fieldsA);

            System.out.println("Fields unique to " + classAName + ": " + uniqueToA);
            System.out.println("Fields unique to " + classBName + ": " + uniqueToB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Set<String> getFields(Class<?> clazz) {
        Set<String> fields = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields;
    }
}
