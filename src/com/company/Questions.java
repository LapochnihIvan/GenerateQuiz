package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Questions {
    protected Questions() {
        quests = new String[20];
        correctAns = new short[20];
        textsAns = new String[20][4];
        numRound = 0;
        stream = new Reader();
    }

    protected void nextRound() {
        numRound++;
    }


    protected String getQuest() {
        return quests[numRound];
    }

    protected short getCorrectAns() {
        return correctAns[numRound];
    }

    protected boolean isEmpty() {
        return numRound == 20;
    }
    protected String[] getTextsAns() {
        return textsAns[numRound];
    }

    protected boolean isFatalError() throws IOException {
        return !stream.catchFatalErrors().isEmpty();
    }

    protected final ArrayList<String> getErrorFiles() throws IOException {
        ArrayList<String> errorFiles;
        if ((errorFiles = stream.catchFatalErrors()).isEmpty()) {
            if ((errorFiles = stream.catchErrors()).isEmpty()) {
                stream.read(quests, textsAns, correctAns);
            }
        }
        return errorFiles;
    }

    private final String[] quests;
    private final short[] correctAns;
    private final String[][] textsAns;
    private short numRound;
    private final Reader stream;
}