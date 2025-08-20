import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeAnalyzer {
    public static final int PASSING_GRADE = 60;
    public static final int OUTSTANDING_GRADE = 85;

    static void studentCategories(Map<Student, Double> avgPerStudent) {
        Map<String, List<Student>> categories = avgPerStudent.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> {
                            double avg = e.getValue();
                            if (avg >= OUTSTANDING_GRADE) return "Excellent";
                            else if (avg >= PASSING_GRADE) return "Average";
                            else return "Poor";
                        },
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));
        System.out.println("Categories: " + categories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e->e.getValue().stream()
                                .map(Student::getName).toList()
                )));
    }

    static Map<Student, Double> avgPerStudents(List<Student> students) {
        Map<Student, Double> avgPerStudent = students.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        s -> s.getGrades().values().stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0.0)
                ));
        System.out.println("Average per student: " + avgPerStudent.entrySet().stream()
                .collect(Collectors.toMap(
                        e->e.getKey().getName(),
                        Map.Entry::getValue
                ))
        );
        return avgPerStudent;
    }

    static void topPerformerPerSubject(List<Student> students) {
        Map<Subject, Student> topPerformers = SubjectRepo.subjects
                .stream()
                .collect(Collectors.toMap(
                        subject -> subject,
                        subject -> students.stream()
                                .filter(s -> s.getGrades().containsKey(subject))
                                .max(Comparator.comparingInt(s -> s.getGrades().get(subject)))
                                .orElse(new Student("N/A", Map.of()))
                ));
        topPerformers.forEach((subject, student) ->
                System.out.println(subject.getName() + " -> " + student.getName()));

    }

    static void getPassedStudents(List<Student> students) {
        List<Student> allPassed = students.stream()
                .filter(s -> s.getGrades().values().stream().allMatch(g -> g >= PASSING_GRADE))
                .toList();
        System.out.println("Passed all subjects: " + allPassed.stream().map(Student::getName).toList());
    }
}
