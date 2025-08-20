import java.util.*;

public class Application {


    public static void main(String[] args) {
        List<Student> students = StudentRepo.students;


        GradeAnalyzer.getPassedStudents(students);


        GradeAnalyzer.topPerformerPerSubject(students);


        Map<Student, Double> avgPerStudent = GradeAnalyzer.avgPerStudents(students);


        GradeAnalyzer.studentCategories(avgPerStudent);
    }


}
