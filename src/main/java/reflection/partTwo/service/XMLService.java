package reflection.partTwo.service;

import reflection.partOne.Reflection;

import java.lang.reflect.Field;
import java.util.Collection;

public class XMLService {


    public static String convertToXml(Object object) {
        try {
            return convert(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convert(Object object) throws IllegalAccessException {

        var xmlBuilder = new StringBuilder();

        if (object instanceof Collection<?> collection) {
            xmlBuilder.append("<list>");
            collection.forEach(o -> {
                var xmlField = convertToXml(o);
                xmlBuilder.append(xmlField);
            });
            xmlBuilder.append("</list>");
        } else {
            var className = object.getClass().getName();
            var fields = Reflection.reflection(className)
                    .declaredPrivateFields();

            addStartXMLField(xmlBuilder, className);

            for (Field field : fields) {

                var fieldName = field.getName();
                var fieldValue = field.get(object);

                addStartXMLField(xmlBuilder, fieldName);
                xmlBuilder.append(fieldValue);
                addEndXMLField(xmlBuilder, fieldName);
            }

            addEndXMLField(xmlBuilder, className);
        }
        return xmlBuilder.toString();
    }

    private static void addStartXMLField(StringBuilder xmlBuilder, String fieldName) {
        xmlBuilder.append("<")
                .append(fieldName)
                .append(">");
    }

    private static void addEndXMLField(StringBuilder xmlBuilder, String fieldName) {
        xmlBuilder.append("<")
                .append(fieldName)
                .append(">");
    }
}
