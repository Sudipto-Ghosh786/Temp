import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class FieldComparator {

    public static void main(String[] args) {
        compareFields(ClassA.class, ClassB.class);
    }

    public static void compareFields(Class<?> classA, Class<?> classB) {
        Set<String> fieldsA = getFields(classA);
        Set<String> fieldsB = getFields(classB);

        Set<String> uniqueToA = new HashSet<>(fieldsA);
        uniqueToA.removeAll(fieldsB);

        Set<String> uniqueToB = new HashSet<>(fieldsB);
        uniqueToB.removeAll(fieldsA);

        System.out.println("Fields unique to ClassA: " + uniqueToA);
        System.out.println("Fields unique to ClassB: " + uniqueToB);
    }

    private static Set<String> getFields(Class<?> clazz) {
        Set<String> fields = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields;
    }
}
