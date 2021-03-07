package com.company;

import java.io.IOException;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IOException {
        new Main().process();
    }

    private final GUI gui;
    private final Questions questions;
    private final ArrayList<String> errorFiles;

    private Main() throws IOException {
        gui = new GUI();
        questions = new Questions();
        errorFiles = questions.getErrorFiles();
    }

    private void process() throws IOException {
        if (errorFiles.isEmpty()) {
            gui.generation();
            gui.setQuestFrame(questions.getQuest(), questions.getTextsAns());
            while (!questions.isEmpty()) {
                if (gui.getResponse() == questions.getCorrectAns()) {
                    questions.nextRound();
                    if (questions.isEmpty()) gui.endOfQuiz();
                    else gui.setQuestFrame(questions.getQuest(), questions.getTextsAns());
                }
                else gui.wrongAns();
            }
        }
        else gui.createErrorWindow(errorFiles, questions.isFatalError());
    }
}