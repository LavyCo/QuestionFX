package listeners;

public interface modelListener {


    void addOpenQuestionMsg(String updateUserMessage);

    String returnChosenQuestion(int id);
    int returnAnswersSizeFromModel(int id);
    String changeOpenQuestionAnswerInModel(String newAnswerText,int id);
    int sendNumOfAnswersFromModel(int id);
    String changeAmericanQuestionAnswerInModel(int id,String answerText,boolean correctness,int numOfAnswer);

}
