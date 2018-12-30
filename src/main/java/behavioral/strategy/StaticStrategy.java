package behavioral.strategy;

import java.util.List;
import java.util.function.Supplier;

enum OutputFormatStatic {
    MARKDOWN, HTML
}

interface ListStrategyStatic {
    default void start(StringBuilder sb) {
    }

    void addListItem(StringBuilder sb, String item);

    default void end(StringBuilder sb) {
    }
}

class MarkdownListStrategyStaticStatic implements ListStrategyStatic {

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategyStatic implements ListStrategyStatic {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());

    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessorStatic<LS extends ListStrategyStatic> {
    private StringBuilder sb = new StringBuilder();
    private LS listStrategy;

    public TextProcessorStatic(Supplier<? extends LS> ctor) {
        listStrategy = ctor.get();
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class StaticStrategy {

    public static void main(String[] args) {
        TextProcessorStatic<MarkdownListStrategyStaticStatic> tp
                = new TextProcessorStatic<>(MarkdownListStrategyStaticStatic::new);
        tp.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp);

        TextProcessorStatic<HtmlListStrategyStatic> tp2
                = new TextProcessorStatic<>(HtmlListStrategyStatic::new);
        tp2.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp2);
    }
}
