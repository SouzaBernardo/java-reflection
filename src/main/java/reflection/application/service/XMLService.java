package reflection.application.service;

import reflection.core.service.ReflectionImpl;
import reflection.core.annotations.TagXML;

import java.lang.reflect.Field;
import java.util.Collection;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

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
            openXMLObject(xmlBuilder, "list");
            collection.forEach(o -> {
                var xmlField = convertToXml(o);
                xmlBuilder.append(xmlField);
            });
            closeXMLObject(xmlBuilder, "list");
        } else {
            var objectClass = object.getClass();
            var fields = new ReflectionImpl().reflect(objectClass)
                    .declaredFields();

            String className = getXmlClassName(objectClass);

            openXMLObject(xmlBuilder, className);
            for (Field field : fields) {
                var fieldName = getXmlFieldName(field);
                var fieldValue = field.get(object);
                addXMLField(xmlBuilder, fieldName);
                xmlBuilder.append(fieldValue);
                addCloseXMLFieldBreakingLine(xmlBuilder, fieldName);
            }
            closeXMLObject(xmlBuilder, className);
        }
        return xmlBuilder.toString();
    }

    private static String getXmlClassName(Class<?> objectClass) {
        String name = objectClass.getDeclaredAnnotation(TagXML.class).value();
        if (nonNull(name)) {
            return name;
        }
        return objectClass.getSimpleName();
    }

    private static String getXmlFieldName(Field field) {
        var annotation = field.getAnnotation(TagXML.class);
        if (nonNull(annotation)) {
            var name = requireNonNull(annotation).value();
            if (nonNull(name)) {
                return name;
            }
        }
        return field.getName();
    }

    private void addXMLField(StringBuilder xmlBuilder, String fieldName) {
        addTab(xmlBuilder);
        xmlBuilder.append("<")
                .append(fieldName)
                .append(">");
    }

    private void addOpenXMLFieldBreakingLine(StringBuilder xmlBuilder, String fieldName) {
        addTab(xmlBuilder);
        xmlBuilder.append("<")
                .append(fieldName)
                .append(">\n");
    }

    private void addCloseXMLFieldBreakingLine(StringBuilder xmlBuilder, String fieldName) {
        xmlBuilder.append("</")
                .append(fieldName)
                .append(">\n");
    }

    private void openXMLObject(StringBuilder xmlBuilder, String fieldName) {
        addTab(xmlBuilder);
        addOpenXMLFieldBreakingLine(xmlBuilder, fieldName);
        GAPS++;
    }

    private void closeXMLObject(StringBuilder xmlBuilder, String fieldName) {
        GAPS--;
        addTab(xmlBuilder);
        addCloseXMLFieldBreakingLine(xmlBuilder, fieldName);
    }

    private void addTab(StringBuilder xmlBuilder) {
        if (GAPS > 0) {
            xmlBuilder.append(GAP.repeat(GAPS));
        }
    }
}
