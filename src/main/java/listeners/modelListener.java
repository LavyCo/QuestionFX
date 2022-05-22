package listeners;

public interface modelListener {


    void addOpenQuestionMsg(String updateUserMessage);

    String returnChosenQuestion(int id);
    int returnAnswersSizeFromModel(int id);
    String changeOpenQuestionAnswerInModel(String newAnswerText,int id);

}
