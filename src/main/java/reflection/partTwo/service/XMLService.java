package reflection.partTwo.service;

import reflection.partOne.Reflection;

import java.lang.reflect.Field;
import java.util.Collection;

public class XMLService {

    public static final String GAP = "  ";
    private static int GAPS = 0;


    public String convertToXml(Object object) {
        try {
            return convert(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String convert(Object object) throws IllegalAccessException {
        var xmlBuilder = new StringBuilder();
        if (object instanceof Collection<?> collection) {
            addStartXMLObject(xmlBuilder, "list");
            collection.forEach(o -> {
                var xmlField = convertToXml(o);
                xmlBuilder.append(xmlField);
            });
            addEndXMLObject(xmlBuilder, "list");
        } else {
            var className = object.getClass();
            var fields = Reflection.reflection(className)
                    .declaredPrivateFields();

            addStartXMLObject(xmlBuilder, className.getSimpleName());
            for (Field field : fields) {
                var fieldName = field.getName();
                var fieldValue = field.get(object);
                addXMLField(xmlBuilder, fieldName);
                xmlBuilder.append(fieldValue);
                addXMLFieldBreakingLine(xmlBuilder, fieldName);
            }
            addEndXMLObject(xmlBuilder, className.getSimpleName());
        }
        return xmlBuilder.toString();
    }

    private void addXMLField(StringBuilder xmlBuilder, String fieldName) {
        addTab(xmlBuilder);
        xmlBuilder.append("<")
                .append(fieldName)
                .append(">");
    }

    private void addXMLFieldBreakingLine(StringBuilder xmlBuilder, String fieldName) {
        xmlBuilder.append("</")
                .append(fieldName)
                .append(">\n");
    }

    private void addStartXMLObject(StringBuilder xmlBuilder, String fieldName) {
        addTab(xmlBuilder);
        addXMLFieldBreakingLine(xmlBuilder, fieldName);
        GAPS++;
    }

    private void addEndXMLObject(StringBuilder xmlBuilder, String fieldName) {
        GAPS--;
        addTab(xmlBuilder);
        addXMLFieldBreakingLine(xmlBuilder, fieldName);
    }

    private void addTab(StringBuilder xmlBuilder) {
        if (GAPS > 0) {
            xmlBuilder.append(GAP.repeat(GAPS));
        }
    }
}
