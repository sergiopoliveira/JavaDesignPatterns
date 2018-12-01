package builder;

import java.util.HashMap;
import java.util.Map;

class CodeBuilder {
    private String className;
    private Map<String, String> fields;
    private static String PUBLIC_STRING = "public ";

    public CodeBuilder(String className) {
        this.className = className;
        fields = new HashMap<>();
    }

    public CodeBuilder addField(String name, String type) {
        fields.put(name, type);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(className)
                .append("\n{\n");

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            sb.append(" " + PUBLIC_STRING)
                    .append(entry.getValue())
                    .append(" ")
                    .append(entry.getKey())
                    .append(";\n");
        }

        sb.append("}");
        return sb.toString();
    }

}

class Demo2 {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("solid.principles.Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}