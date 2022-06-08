package id206214280_id316650399;

import listeners.modelListener;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class QuestionReservoir implements Serializable {


    private int numberOfQuestions = 0;
    private ArrayList<Questions> questionArray;
    private Exam manualExam;
    private Exam manualExamClone;
    private Exam automaticExam;
    private Exam automaticExamClone;
    private Vector<modelListener> qrListeners;


    public QuestionReservoir() {
        questionArray = new ArrayList<>();
        manualExam = new Exam();
        automaticExam = new Exam();
        qrListeners = new Vector<modelListener>();

    }


    public boolean changeAnswerWordingOfOpenQuestion(String newAnswerText, Questions editorQuestionAnswer) {
        if (editorQuestionAnswer instanceof OpenQuestions) {
            if (((OpenQuestions) editorQuestionAnswer).getAnswerText().equalsIgnoreCase(newAnswerText)) {

                for (modelListener l : qrListeners) {
                    l.fireOpenAnswerUpdateResult("Can't change Answer text-There a Answer with the same text");
                }
                return false;
            } else {
                ((OpenQuestions) editorQuestionAnswer).setAnswerText(newAnswerText);

                for (modelListener l : qrListeners) {
                    l.fireOpenAnswerUpdateResult("Question answer changed successfully !");
                }


                return true;
            }

        }
        return false;
    }


    public void saveBin() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("QuestionArray.dat"));
        Questions[] questionArray = new Questions[this.questionArray.size()];
        for (int i = 0; i < this.questionArray.size(); i++) {
            questionArray[i] = this.questionArray.get(i);
        }
        outFile.writeObject(questionArray);
        outFile.close();
        for (modelListener l : qrListeners) {
            String msg = "your Questions Saved";
            l.fireSaveMsg(msg);
        }


    }

    public void readBin() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("QuestionArray.dat"));
        Questions[] tempQuestionArr = (Questions[]) inFile.readObject();
        inFile.close();
        for (int i = 0; i < tempQuestionArr.length; i++) {
            if (tempQuestionArr[i] instanceof OpenQuestions) {
                OpenQuestions newOpenQuestion = new OpenQuestions((OpenQuestions) tempQuestionArr[i]);
                this.getQuestionArray().add(newOpenQuestion);
                numberOfQuestions++;

            }
            if (tempQuestionArr[i] instanceof AmericanQuestions) {
                AmericanQuestions newAmericanQuestion = new AmericanQuestions((AmericanQuestions) tempQuestionArr[i]);
                this.getQuestionArray().add(newAmericanQuestion);
                numberOfQuestions++;

            }
        }
    }


    public boolean changeAnswerWordingOfAmericanQuestions(String newAnswerText, AmericanQuestions editorQuestionAnswer, int numOfAnswer, boolean opt) {


        if (editorQuestionAnswer instanceof AmericanQuestions) {

            Object[] americanAnswerArr = new Object[0];
            for (int i = 0; i < (editorQuestionAnswer).getNumOfAmericanAnswers(); i++) {
                americanAnswerArr = editorQuestionAnswer.getAnswerArray().toArray();

                if (((AmericanAnswer) americanAnswerArr[i]).getAnswerText().equalsIgnoreCase(newAnswerText)) {
                    String msg = "Can't change Answer text-There's an Answer with the same text";
                    for (modelListener l : qrListeners) {
                        l.fireUpdateAmericanAnswerResult(msg);
                    }
                    return false;
                }
            }

            ((AmericanAnswer) americanAnswerArr[numOfAnswer]).setCorrectness(opt);
            ((AmericanAnswer) americanAnswerArr[numOfAnswer]).setAnswerText(newAnswerText);


            String msg = "Answer changed successfully !";
            for (modelListener l : qrListeners) {
                l.fireUpdateAmericanAnswerResult(msg);
            }

            return true;
        }
        return false;
    }


    public boolean changeQuestionWording(String newQuestionText, int choosenId) {
        // loop that checks if the question exists
        String msg = null;
        Questions testQuestionText = new Questions(newQuestionText);
        for (int i = 0; i < this.numberOfQuestions; i++) {
            if (testQuestionText.equals(this.questionArray.get(i))) {
                System.out.println("Can't change question text-There a Question with the same text");
                msg = "Can't change question text-There a Question with the same text";
                for (modelListener l : qrListeners) {
                    l.updateResult(msg);
                }
                return false;
            }
        }

        fetchQuestionById(choosenId).setQuestionText(newQuestionText);
        System.out.println("Question wording changed succesfully!");
        msg = "Question wording changed succesfully!";
        for (modelListener l : qrListeners) {
            l.updateResult(msg);
        }
        return true;


    }

    public Set<AmericanAnswer> copyArrayListToSet(ArrayList arrayList) {
        Set<AmericanAnswer> newSet = new Set<>();
        for (int i = 0; i < arrayList.size(); i++) {
            newSet.add((AmericanAnswer) arrayList.get(i));
        }
        return newSet;

    }

    public void automaticExam(int automaticExamNumberOfQuestions) throws FileNotFoundException {
        automaticExam = new Exam<>();
        //new question array for the exam
        ArrayList<Questions> newQuestionArray = new ArrayList<>(automaticExamNumberOfQuestions);
        //the reservoir question array
        ArrayList<Questions> qrQuestionArr = this.questionArray;
        //random index array example:5,3,2,4,1
        int[] randomIndex = randomNumbersArray(this.numberOfQuestions);
        for (int i = 0; i < automaticExamNumberOfQuestions; i++) {

            //r random index
            int r = randomIndex[i];

            //checks if the question is open
            if (qrQuestionArr.get(r) instanceof OpenQuestions) {
                OpenQuestions newOpenQuestion = new OpenQuestions(qrQuestionArr.get(r).questionText, ((OpenQuestions) qrQuestionArr.get(r)).getAnswerText());
                newQuestionArray.add(newOpenQuestion);
                automaticExam.addQuestion(newOpenQuestion);
            }


            if (qrQuestionArr.get(r) instanceof AmericanQuestions) {
                int americanAnswersSize = ((AmericanQuestions) qrQuestionArr.get(r)).getNumOfAmericanAnswers();

                //random American question chosen from the reservoir
                AmericanQuestions randomAmericanQuestion = ((AmericanQuestions) qrQuestionArr.get(r));

                //automated american question
                AmericanQuestions automaticAmericanQuestion;

                Object[] answerArr = randomAmericanQuestion.getAnswerArray().toArray();
                //another random index array for the answers
                int[] randomAnswerIndex = randomNumbersArray(americanAnswersSize);
                //initializing answers array size of 4
                Set<AmericanAnswer> automaticAmericanAnswerSet = new Set<>();
                //counts how mnay true answer encountered
                int trueCounter = 0;
                //iteration of the answer array
                int t = 0;
                //loop will keep running as long as inside the array and there are less than 4 answer collected
                for (int j = 0; j < americanAnswersSize && t < 4; j++) {
                    //index for random answer array
                    int p = randomAnswerIndex[j];

                    //if current chosen answer is the first true answer encountered add to automated array
                    if (((AmericanAnswer) answerArr[p]).getCorrectness() && trueCounter != 1) {
                        automaticAmericanAnswerSet.add(((AmericanAnswer) answerArr[p]));
                        //automaticAmericanAnswersArray[t] = new AmericanAnswer(randomAmericanQuestion.getAnswerArray().get(p));
                        t++;
                        trueCounter = 1;
                    }
                    //if current answer is false then add to automated answer array
                    else if (!((AmericanAnswer) answerArr[p]).getCorrectness()) {
                        automaticAmericanAnswerSet.add(((AmericanAnswer) answerArr[p]));

                        //automaticAmericanAnswersArray[t] = new AmericanAnswer(randomAmericanQuestion.getAnswerArray().get(p));
                        t++;

                    }
                    //if current answer is true and answer array has already a true ansewr in it
                    else if (((AmericanAnswer) answerArr[p]).getCorrectness() && trueCounter == 1) {
                        //initializing k to j value to iterating out of loop
                        int k = j;
                        //foundFalse turns into true if a false answer is found
                        boolean foundFalse = false;
                        //next random answer index
                        k++;
                        //inner while loop that finds a false answer
                        while (!foundFalse) {
                            p = randomAnswerIndex[k];
                            //if found false answer
                            if (!((AmericanAnswer) answerArr[p]).getCorrectness()) {
                                automaticAmericanAnswerSet.add(((AmericanAnswer) answerArr[p]));
                                t++;
                                j = k;
                                foundFalse = true;
                            } else {
                                k++;
                            }

                        }

                    }

                }
                automaticAmericanQuestion = new AmericanQuestions(randomAmericanQuestion.questionText, automaticAmericanAnswerSet);
                automaticAmericanQuestion.add2Answers();
                automaticExam.addQuestion(automaticAmericanQuestion);
            }
        }
        automaticExam.saveToText();
        automaticExam.sortExamByShortestAnswers();
        for (modelListener l : qrListeners) {
            l.fireAutoExam(automaticExam.toString());
        }
        System.out.println(automaticExam.toString());

    }

    public void cloneExam(int whichExamOpt) throws CloneNotSupportedException, FileNotFoundException {
        String msg = null;

        if (whichExamOpt == 1) {
            if (manualExam.getNumOfQuestions() == 0) {
                System.out.println("Can't clone the exam. Manual exam not created yet.");
                msg = "Can't clone the exam. Manual exam not created yet.";

            } else {
                manualExamClone = manualExam.clone();
                System.out.println("Manual exam cloned");
                System.out.println(manualExamClone.toString());
                manualExam = automaticExam;
                System.out.println(manualExam.toString());
                System.out.println(manualExamClone.toString());
                msg = "Manual exam cloned";
            }

        }

        if (whichExamOpt == 2) {
            if (automaticExam.getNumOfQuestions() == 0) {
                System.out.println("Can't clone the exam. Automatic exam not created yet.");
                msg = "Can't clone the exam. Automatic exam not created yet.";
            } else {
                automaticExamClone = automaticExam.clone();
                System.out.println("Automatic exam cloned");
                System.out.println(automaticExamClone.toString());
                msg = "Automatic exam cloned";
            }


        }
        for (modelListener l : qrListeners) {
            l.fireCloneMassege(msg);
        }

    }


    public void addOpenQuestion(String questionText, String answerText) {
        String updateUserMessage = "Error";
        OpenQuestions newQuestion = new OpenQuestions(questionText, answerText);

        if (this.equals(newQuestion.getQuestionText())) {
            System.out.println("Cannot add:This question is already in the reservoir");
            updateUserMessage = "Cannot add:This question is already in the reservoir";
            //decreasing id counter by 1
            newQuestion.decreaseIdCounter();

            for (modelListener l : qrListeners) {
                l.fireOpenQuestionAddedResult(updateUserMessage);

            }


        }


        if (newQuestion instanceof OpenQuestions) {
            Questions newOpenQuestion = newQuestion;
            if (questionArray.contains(newOpenQuestion)) {
                System.out.println("Cannot add:This question is already in the reservoir");
                updateUserMessage = "Cannot add:This question is already in the reservoir";
                //decreasing id counter by 1
                newQuestion.decreaseIdCounter();
                for (modelListener l : qrListeners) {
                    l.fireOpenQuestionAddedResult(updateUserMessage);
                }

            } else {
                questionArray.add(newOpenQuestion);
                numberOfQuestions++;
                updateUserMessage = "Successful";
                for (modelListener l : qrListeners) {
                    l.fireOpenQuestionAddedResult(updateUserMessage);
                }
            }

        }
    }

    public void addAmericanQuestion(String
                                            questionText, ArrayList<String> answersArray, ArrayList<Boolean> correctnessArray) {


        Set<AmericanAnswer> answerArrayList = new Set<>();
        for (int i = 0; i < answersArray.size(); i++) {
            answerArrayList.add(new AmericanAnswer(answersArray.get(i), correctnessArray.get(i)));
        }
        AmericanQuestions newAmericanQuestion = new AmericanQuestions(questionText, answerArrayList);
        if (questionArray.contains(newAmericanQuestion)) {
            for (modelListener l : qrListeners) {
                l.fireAmericanQuestionAddedResult("Question wasn't added:Already in the reservoir");
            }

        } else {
            questionArray.add(newAmericanQuestion);
            numberOfQuestions++;
            for (modelListener l : qrListeners) {
                l.fireAmericanQuestionAddedResult("Question added successfully!");
            }

            System.out.println("American Question added");
        }


    }

    public int takeNumOfAnswers(QuestionReservoir qr1, int index) {
        AmericanQuestions americanQuestion = (AmericanQuestions) this.getQuestionArray().get(index);
        return americanQuestion.getNumOfAmericanAnswers();

    }

    public void deleteAmericanAnswer(int id, int answerNumber) {
//        try {
        AmericanAnswer americanAnswerToDelete = ((AmericanQuestions) fetchQuestionById(id)).getAnswerArray().get(answerNumber);
        String result = ((AmericanQuestions) fetchQuestionById(id)).americanAnswerRemove(answerNumber);
        Vector<String> americanAnswerString = new Vector<>();
        System.out.println("Answer name" + americanAnswerToDelete.getAnswerText());
        System.out.println("Answer number" + answerNumber);
        System.out.println(((AmericanQuestions) fetchQuestionById(id)).getAnswerArray().size());
        for (int i = 0; i < ((AmericanQuestions) fetchQuestionById(id)).getAnswerArray().size(); i++) {
            americanAnswerString.add(((AmericanQuestions) fetchQuestionById(id)).getAnswerArray().get(i).toString());
        }
        for (modelListener l : qrListeners) {
            l.fireRemoveAmericanAnswerMsg(this.fetchQuestionById(id).getQuestionText(), americanAnswerString, id, result);
        }
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage()+"American answer deletion");
//        }
    }


    public void manualExamCreate(int numOfQuestInTest, ArrayList<ArrayList<Integer>> manualQuestionArrayList) throws
            FileNotFoundException {

        for (int arrayIndex = 0; arrayIndex < numOfQuestInTest; arrayIndex++) {
            for (int allQuestionsIndex = 0; allQuestionsIndex < numberOfQuestions; allQuestionsIndex++) {
                if (this.questionArray.get(allQuestionsIndex).questionId == manualQuestionArrayList.get(arrayIndex).get(0) + 1) {
                    if (this.questionArray.get(allQuestionsIndex) instanceof OpenQuestions) {
                        OpenQuestions newOpenQuestions = new OpenQuestions((OpenQuestions) this.questionArray.get(allQuestionsIndex));
                        manualExam.addQuestion(newOpenQuestions);
                    }
                    if (this.questionArray.get(allQuestionsIndex) instanceof AmericanQuestions) {
                        Object[] answerArray = ((AmericanQuestions) this.questionArray.get(allQuestionsIndex)).getAnswerArray().toArray();
                        AmericanQuestions newAmericanQuestion;
                        Set<AmericanAnswer> newAmericanAnswer = new Set<>();
                        for (int i = 0; i < manualQuestionArrayList.get(arrayIndex).size() - 1; i++) {
                            newAmericanAnswer.add((AmericanAnswer) answerArray[manualQuestionArrayList.get(arrayIndex).get(i)]);
                        }
                        newAmericanQuestion = new AmericanQuestions(this.getQuestionArray().get(allQuestionsIndex).getQuestionText(), newAmericanAnswer);
                        manualExam.addQuestion(newAmericanQuestion);
                        newAmericanQuestion.add2Answers();
                    }

                }
            }
        }
        manualExam.saveToText();
        System.out.println("Manual exam created successfully !");
        manualExam.toString();
        for (modelListener l : qrListeners) {
            l.fireAutoExam(automaticExam.toString());
        }


    }

    //checks if there is the same question in the array
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Questions)) {
            return false;
        }
        Questions p = (Questions) other;
        for (int i = 0; i < numberOfQuestions; i++) {

            return this.questionArray.get(i).equals(p);


        }
        return false;
    }

    public static int[] randomNumbersArray(int size) {
        int[] numArray = new int[size];
        int i = 0;
        //creating number array
        while (i < size) {
            numArray[i] = (i);
            i++;
        }
        //creating random number array
        boolean hasChanged = false;
        int[] randomNumberArray = new int[size];
        //configuring random numbers array
        for (i = 0; i < size; i++) {
            hasChanged = false;
            int randomIndex = (int) (Math.random() * size);
            do {
                if (numArray[randomIndex] != -1) {
                    randomNumberArray[i] = numArray[randomIndex];
                    numArray[randomIndex] = -1;
                    hasChanged = true;
                } else {
                    randomIndex = (int) (Math.random() * size);
                }
            } while (!hasChanged);
        }
        return randomNumberArray;
    }

    public Exam getManualExam() {
        return manualExam;
    }

    public Exam getAutomaticExam() {
        return automaticExam;
    }

    public ArrayList<Questions> getQuestionArray() {
        return questionArray;
    }

    //new
    public void getIDNumberArray() {
        ArrayList<Integer> idArray = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            idArray.add(questionArray.get(i).getQuestionId());
        }
        for (modelListener l : qrListeners) {
            l.fireIdToUI(idArray);
        }
    }

    public int getNumberOfQuestions() {
        for (modelListener l : qrListeners) {
            l.fireNumberOfQuestion(numberOfQuestions);
        }

        return numberOfQuestions;
    }

    public Questions fetchQuestionById(int id) {
        for (int i = 0; i < numberOfQuestions; i++) {
            if (questionArray.get(i).getQuestionId() == id) {
                return questionArray.get(i);
            }
        }
        return null;
    }

    public void getAllQuestionsToView() {
        String allQuestionString = this.toString();
        for (modelListener l : qrListeners) {
            l.fireQuestionString(allQuestionString);
        }
    }

    public void getAllQuestionToManualExamView() {
        Vector<String> allQuestionsString = new Vector<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            allQuestionsString.add(questionArray.get(i).toString());
        }

        for (modelListener l : qrListeners) {
            l.fireQuestionStringManualExam(allQuestionsString);
        }

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("The number of questions in the Reservoir is: " + numberOfQuestions + "\n" + "\nThe questions are:\n");
        for (int i = 0; i < numberOfQuestions; i++) {
            sb.append(questionArray.get(i).toString());
        }
        return sb.toString();
    }


    public void registerListener(modelListener qrListener) {
        qrListeners.add(qrListener);
    }

    public String PrintAllQustionsModel() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getNumberOfQuestions(); i++) {
            sb.append("id is:(" + this.getQuestionArray().get(i).getQuestionId() + ")" + " ");
            sb.append("Question text is: " + this.getQuestionArray().get(i).getQuestionText() + "'\n");

        }
        return sb.toString();
    }

    public String getAmericanQuestionsToPrint() {
        for (modelListener l : qrListeners) {
            StringBuffer americanQuestionString = new StringBuffer();
            for (Questions q : questionArray) {
                if (q instanceof AmericanQuestions) {
                    americanQuestionString.append(q.toString());
                }
            }
            return americanQuestionString.toString();
        }
        return null;
    }


    public boolean checkIfAmerican(int id) {
        if (this.fetchQuestionById(id) instanceof AmericanQuestions) {
            return true;
        }
        return false;
    }

    public void sendIdAndQuestionsToUpdate() {
        ArrayList<Integer> idArray = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            idArray.add(questionArray.get(i).getQuestionId());
        }
        for (modelListener l : qrListeners) {
            l.fireQuestionsStringAndId(this.toString(), idArray);
        }

    }

    public void sendIdAndQuestionsToUpdateAnswer() {
        ArrayList<Integer> idArray = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            idArray.add(questionArray.get(i).getQuestionId());
        }
        for (modelListener l : qrListeners) {
            l.fireIdArrayAndQuestionStringToUpdateAnswer(this.toString(), idArray);
        }

    }


    public void getQuestionTextById(int id) {
        for (modelListener l : qrListeners) {
            l.fireQuestionText(fetchQuestionById(id).getQuestionText(), id);
        }
    }

    public void getAnswerById(int id) {
        if (fetchQuestionById(id) instanceof AmericanQuestions) {
            AmericanQuestions americanQuestions = (AmericanQuestions) fetchQuestionById(id);
            Object[] answerArray = americanQuestions.getAnswerArray().toArray();
            Vector<String> answerString = new Vector<>();
            for (int i = 0; i < answerArray.length; i++) {
                answerString.add(answerArray[i].toString());
            }
            for (modelListener l : qrListeners) {
                l.fireAmericanAnswersString(answerString, ((AmericanQuestions) fetchQuestionById(id)).getNumOfAmericanAnswers(), id);
            }
        }
        if (fetchQuestionById(id) instanceof OpenQuestions) {
            for (modelListener l : qrListeners) {
                l.fireOpenAnswerUpdateString(fetchQuestionById(id).getQuestionText(), ((OpenQuestions) fetchQuestionById(id)).getAnswerText(), id);
            }
        }
    }

    public void addOpenQuestionToExam(int numberOfQuestion) {
        if (questionArray.get(numberOfQuestion) instanceof OpenQuestions) {
            this.manualExam.addQuestion(questionArray.get(numberOfQuestion));
        }
    }

    public void addAmericanQuestionToExam(int numberOfQuestions, Vector<Integer> answersArray) {
        if (questionArray.get(numberOfQuestions) instanceof AmericanQuestions) {
            Set<AmericanAnswer> americanAnswerSet = new Set<>();
            for (int i = 0; i < answersArray.size(); i++) {
                americanAnswerSet.add(((AmericanQuestions) questionArray.get(numberOfQuestions)).getAnswerArray().get(answersArray.get(i)));
            }
            AmericanQuestions americanQuestions = new AmericanQuestions(questionArray.get(numberOfQuestions).getQuestionText(), americanAnswerSet);
            manualExam.addQuestion(americanQuestions);
        }
    }

    public void getAmericanQuestionsData() {
        String americanQuestionsString = getAmericanQuestionsToPrint();
        Vector<Integer> americanIdVector = new Vector<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            if (questionArray.get(i) instanceof AmericanQuestions) {
                int americanQuestionId = questionArray.get(i).getQuestionId();
                americanIdVector.add(americanQuestionId);
            }
        }
        for (modelListener l : qrListeners) {
            l.fireAmericanId(americanQuestionsString, americanIdVector);
        }
    }


    public void getSelectedAmericanQuestionData(int id) {
        try {
            for (modelListener l : qrListeners) {
                AmericanQuestions americanQuestions = (AmericanQuestions) fetchQuestionById(id);
                String americanQuestionString = americanQuestions.getQuestionText();
                Vector<String> americanAnswerString = new Vector<>();
                Set<AmericanAnswer> americanAnswerSet = americanQuestions.getAnswerArray();
                for (int i = 0; i < americanQuestions.getAnswerArray().size(); i++) {
                    americanAnswerString.add(americanAnswerSet.get(i).toString());
                }

                l.fireAmericanQuestionData(americanQuestionString, americanAnswerString, id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void addQuestionToManual(int questionNumber, int size, Vector<Integer> answers) {
        if (manualExam.getNumOfQuestions()<size) {
            System.out.println(manualExam.getNumOfQuestions());
            if (questionArray.get(questionNumber) instanceof OpenQuestions) {
                manualExam.addQuestion(questionArray.get(questionNumber));
            }
            if (questionArray.get(questionNumber) instanceof AmericanQuestions) {
                AmericanQuestions americanQuestions= (AmericanQuestions) questionArray.get(questionNumber);
                Set<AmericanAnswer> americanAnswerSet=new Set<>();
                for(int i=0;i<answers.size();i++){
                americanAnswerSet.add(americanQuestions.getAnswerArray().get(answers.get(i)));
                }
                AmericanQuestions manualAmericanQuestion=new AmericanQuestions(americanQuestions.getQuestionText(),americanAnswerSet);
                manualExam.addQuestion(manualAmericanQuestion);
            }
            System.out.println(manualExam.toString());

        }
        else{
            System.out.println(manualExam.toString());

        }
    }
}

