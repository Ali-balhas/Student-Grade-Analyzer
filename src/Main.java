import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ali", Map.of("Programming", 100, "Calculus", 90)),
                new Student("Alaa", Map.of("Programming", 85, "Calculus", 75)), // fixed typo
                new Student("Nour", Map.of("Programming", 70, "Calculus", 50))
        );

        // 1. Passed all subjects
        List<Student> allPassed = students.stream()
                .filter(s -> s.getGrades().values().stream().allMatch(g -> g >= 60))
                .toList();
        System.out.println("Passed all subjects: " + allPassed.stream().map(Student::getName).toList());

        // 2. Top performers per subject
        Map<String, Student> topPerformers = students.get(0).getGrades().keySet()
                .stream()
                .collect(Collectors.toMap(
                        subject -> subject,
                        subject -> students.stream()
                                .max(Comparator.comparingInt(s -> s.getGrades().get(subject)))
                                .orElse(null)
                ));
        System.out.println("Top performers per subject: " + topPerformers.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey ,
                        e->e.getValue().getName()
                ))
        );

        // 3. Average per student
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

        // 4. Group into categories
        Map<String, List<Student>> categories = avgPerStudent.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> {
                            double avg = e.getValue();
                            if (avg >= 85) return "Excellent";
                            else if (avg >= 60) return "Average";
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
}
