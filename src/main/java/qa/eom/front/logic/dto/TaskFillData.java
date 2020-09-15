package qa.eom.front.logic.dto;


public class TaskFillData {


    public String taskTheme;
    public int parallelNumber;
    public String learnCourse;
    public String lessonTheme;
    public String dedacticUnit;

    public TaskFillData(String taskTheme, int parallelNumber, String learnCourse, String lessonTheme, String dedacticUnit) {
        this.taskTheme = taskTheme;
        this.parallelNumber = parallelNumber;
        this.learnCourse = learnCourse;
        this.lessonTheme = lessonTheme;
        this.dedacticUnit = dedacticUnit;
    }

}
