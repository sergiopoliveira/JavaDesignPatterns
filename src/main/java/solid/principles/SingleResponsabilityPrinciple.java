package solid.principles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    public void save(String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(toString());
        }
    }

}

class Persistence {
    public void saveToFile(Journal journal, String filename,
                           boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
        }
    }
}

class Demo {

    public static void main(String[] args) throws Exception {
        Journal j = new Journal();
        j.addEntry("Im happy today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence persistence = new Persistence();
        String filename = "c:\\projects\\JavaDesignPatterns\\journal.txt";
        persistence.saveToFile(j, filename, true);

        Runtime.getRuntime().exec("notepad.exe " + filename);

    }
}