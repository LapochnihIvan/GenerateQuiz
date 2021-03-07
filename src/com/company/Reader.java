package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    protected Reader() {
        filesNames = new String[]{"input/quests.in.txt",
                                  "input/textsAns.in.txt",
                                  "input/correctAns.in.txt"};
    }

    protected void read(final String[] quests, final String[][] textsAns, final short[] correctAns) throws IOException {
        reader = new BufferedReader(new FileReader("input/quests.in.txt"));
        for (short i = 0; i < 20; i++) quests[i] = reader.readLine();

        reader = new BufferedReader(new FileReader("input/textsAns.in.txt"));
        for (short i = 0; i < 20; i++) {
            for (short j = 0; j < 4; j++) {
                textsAns[i][j] = reader.readLine();
            }
        }

        reader = new BufferedReader(new FileReader("input/correctAns.in.txt"));
        for (short i = 0; i < 20; i++) {
            correctAns[i] = (short) (reader.readLine().charAt(0) - 48);
        }
    }

    protected final ArrayList<String> catchFatalErrors() throws IOException {
        final ArrayList<String> errorFiles = new ArrayList<>();
        file = new File("input/");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                errorFiles.add("input/");
                return errorFiles;
            }
        }
        for (String fileName : filesNames) {
            file = new File(fileName);
            if (!file.exists()) {
                if (!file.createNewFile()) errorFiles.add(fileName);
            }
        }
        return errorFiles;
    }

    protected final ArrayList<String> catchErrors() throws IOException {
        final ArrayList<String> errorFiles = new ArrayList<>();
        for (String fileName : filesNames) {
            file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            short numLn = 0;
            while (reader.readLine() != null) numLn++;
            if (numLn < 20) errorFiles.add(fileName);
        }
        return errorFiles;
    }

    private BufferedReader reader;
    private File file;
    private final String[] filesNames;
}