package id206214280_id316650399;


import java.util.Iterator;
import java.util.Vector;

public class AmericanQuestions extends Questions implements Cloneable{

    private int numOfAmericanAnswers;
    private Set<AmericanAnswer> answerArrayList;


    public AmericanQuestions(String questionText, Set<AmericanAnswer> answerArrayList) {
        super(questionText);
        this.numOfAmericanAnswers = answerArrayList.size();
        this.answerArrayList = answerArrayList;
    }

    public AmericanQuestions(AmericanQuestions other) {
        super(other.questionText);
        this.numOfAmericanAnswers = other.numOfAmericanAnswers;
        this.answerArrayList=other.answerArrayList;
    }



    protected AmericanQuestions clone() throws CloneNotSupportedException{
       AmericanQuestions temp=(AmericanQuestions)super.clone();
        Set <AmericanAnswer> copyAnswers = new Set<>();
        Object[] americanAnswerArr=this.answerArrayList.toArray();
        for(int i=0;i<this.numOfAmericanAnswers;i++){
            copyAnswers.add(((AmericanAnswer) americanAnswerArr[i]).clone());
        } temp.answerArrayList= copyAnswers;
        return temp;

    }

    public String americanAnswerRemove(int answerNumber) {
        AmericanAnswer americanAnswerToDelete= this.getAnswerArray().get(answerNumber);
        String msg=null;
        if((counterTrueFalse(this.getAnswerArray())==4)&&americanAnswerToDelete.getCorrectness()){
            msg="Cannot delete must have at least 1 true answer";
            return msg;
        }
        if (numOfAmericanAnswers > 2) {
            this.answerArrayList.remove(americanAnswerToDelete);
            numOfAmericanAnswers--;
            msg=americanAnswerToDelete.toString() + "Deleted answer succesfuly ";
            return msg;
        }
        msg="Error:American question must have at least 2 answers";
        return msg;
    }


    public void add2Answers() {
            Object[] americanAnswers=answerArrayList.toArray();

            AmericanAnswer[] plus2AmericanAnswer=new AmericanAnswer[this.getNumOfAmericanAnswers()+2];
            for(int i=0;i<this.getNumOfAmericanAnswers();i++){
                plus2AmericanAnswer[i]=new AmericanAnswer(((AmericanAnswer) americanAnswers[i]).getAnswerText(),((AmericanAnswer) americanAnswers[i]).getCorrectness());
            }
            for(int i=0;i<numOfAmericanAnswers;i++){
                plus2AmericanAnswer[i].setCorrectness(false);
            }
            if(counterTrueFalse(this.getAnswerArray())==2){

                boolean correct= true;
                boolean notCorrect=false;
                String Ans2= "There is more than 1 right answers";
                String Ans1= "All the answers are false";
                americanAnswers=plus2AmericanAnswer;
                this.setNumOfAmericanAnswers(plus2AmericanAnswer.length);
                americanAnswers[numOfAmericanAnswers-1]=new AmericanAnswer( Ans1, correct);
                americanAnswers[numOfAmericanAnswers-2]=new AmericanAnswer( Ans2, notCorrect);
            }
            else if(counterTrueFalse(this.getAnswerArray())==3){
                boolean notCorrect=false;
                boolean correct= true;
                String Ans1= "All the answers are false";
                String Ans2= "There are more than 1 right answers";
                americanAnswers=plus2AmericanAnswer;
                this.setNumOfAmericanAnswers(plus2AmericanAnswer.length);
                americanAnswers[numOfAmericanAnswers-1]=new AmericanAnswer( Ans1,notCorrect );
                americanAnswers[numOfAmericanAnswers-2]=new AmericanAnswer( Ans2, correct);
            }
            else{
                for(int i=0;i<numOfAmericanAnswers;i++){
                    plus2AmericanAnswer[i].setCorrectness(((AmericanAnswer)americanAnswers[i]).getCorrectness());
                }
                String Ans1= "All the answers are false";
                String Ans2= "There are more than 1 right answers";
                americanAnswers=plus2AmericanAnswer;
                this.setNumOfAmericanAnswers(plus2AmericanAnswer.length);
                americanAnswers[numOfAmericanAnswers-1]=new AmericanAnswer( Ans1,false );
                americanAnswers[numOfAmericanAnswers-2]=new AmericanAnswer( Ans2, false);
            }
            this.answerArrayList=this.turnArrayIntoSet((AmericanAnswer[]) americanAnswers);


    }


    //this function check if there are more than 1 true answer and adds "More than 1 true answer"
    //  הפונקצמיה בודקת את מערך התשובות לשאלה האמריקאית ומחלקת למצבים הבאים
    // ומטפלת לפי כל סיטואציה
    //יותר מתשובה אחת נכונה,כל התשובות נכונות,אף תשובה אינה נכונה,תושבה אחת נכונה ותשובה שנייה לא נכונה(לא מטפלת),יש רק תשובה אחת נכונה(לא מטפלת)
//    הפונקציה מחזירה מערך תשובות לאחר שטיפלה בו
    public boolean checkAnswerArrays(Set<AmericanAnswer> americanAnswers) {

        //tC=1 and fC=1
        if (this.counterTrueFalse(americanAnswers) == 1) {
            return true;
        }

        //all answers are false
        //adds an "All answers are false" answer
        if (this.counterTrueFalse(americanAnswers) == 2) {
            try {
                this.answerArrayList.add(new AmericanAnswer("All answers are false", true));
                numOfAmericanAnswers++;

                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }

        }

        //tC>1  more than 1 true answer
        //turns all true answers into true and adds "More than answer is true" Answer
        Object [] americanAnswersArr= answerArrayList.toArray();
        if (this.counterTrueFalse(americanAnswers) == 3) {
            //loop that turns all true answers into false answers
            for (int i = 0; i < numOfAmericanAnswers; i++) {
                ((AmericanAnswer)americanAnswersArr[i]).setCorrectness(false);
            }
            //loop that turns array into set
            answerArrayList.removeAll();
            numOfAmericanAnswers=0;
            for(int i=0;i<americanAnswersArr.length;i++){
                answerArrayList.add((AmericanAnswer)americanAnswersArr[i]);
                numOfAmericanAnswers++;
            }

            this.answerArrayList.add(new AmericanAnswer("All answers are true", true));
            numOfAmericanAnswers++;

        }
        return true;
    }


    //הפונקציה סופרת את את מספר התשובות הנכונות ולא נכונות ומחזירה אינדקס
    public int counterTrueFalse(Set<AmericanAnswer> americanAnswers) {
        //function that check how many true and false there are
        //check how many false or true answers there are for the question
        Object[] americanAnswer=answerArrayList.toArray();

        int trueCounter = 0, falseCounter = 0;
        for (int i = 0; i < americanAnswers.size(); i++) {
            if (((AmericanAnswer)americanAnswer[i]).getCorrectness()) {
                trueCounter++;
            } else {
                falseCounter++;
            }
        }
        //if tC=1 and fC=1 you can't delete any answer(only 2 answers for the question)
        if (trueCounter == 1 && falseCounter == 1) {
            return 1;
        }
        //if tC=0 and fC>0 if true answer deleted than add another answer (All answers are false)
        if ((trueCounter == 0) && (falseCounter > 0)) {
            return 2;
        }
        //if tC>1 (there is more than 1 right answer)
        if ((trueCounter > 1)) {
            return 3;
        }
        if(trueCounter==1&&falseCounter>0){
            return 4;
        }
        return 0;
    }


    public int getNumOfAmericanAnswers() {
        return numOfAmericanAnswers;
    }

    public void setNumOfAmericanAnswers(int numOfAmericanAnswers) {
        this.numOfAmericanAnswers = numOfAmericanAnswers;
    }

    @Override
    public String toString() {
        Object[] americanAnswer=answerArrayList.toArray();
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString() + "the answers are: \n");
        for (int i = 0; i < numOfAmericanAnswers; i++) {
            sb.append((i + 1) + ")" + americanAnswer[i].toString() + "\n");
        }
        sb.append("\n");
        return sb.toString();
    }



    public String printAnswersOnly() {
        Object[] americanAnswer=answerArrayList.toArray();

        StringBuffer sb = new StringBuffer();
        sb.append("The answers are:\n");
        for (int i = 0; i < numOfAmericanAnswers; i++) {
            sb.append((i + 1) + ")" + americanAnswer[i].toString() + "\n");
        }
        return sb.toString();

    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AmericanQuestions)) {
            return false;
        }
        if (!(super.equals(other))) {
            return false;
        }
        return true;
    }

    public Set<AmericanAnswer> turnArrayIntoSet(AmericanAnswer[] americanAnswers){
        setNumOfAmericanAnswers(0);
        Set<AmericanAnswer> newAmericanAnswers=new Set<>();
        for(int i=0;i<americanAnswers.length;i++){
            newAmericanAnswers.add(americanAnswers[i]);
            numOfAmericanAnswers++;
        }
        return this.answerArrayList=newAmericanAnswers;
    }

    public Set<AmericanAnswer> getAnswerArray() {
        return answerArrayList;
    }

    public Vector<String> getAnswerStringVector(){
        Vector<String> answerStringVector=new Vector<>();
        for(int i=0;i<this.getNumOfAmericanAnswers();i++){
            answerStringVector.add(this.getAnswerArray().get(i).toString());
        }
        return answerStringVector;
    }

}

