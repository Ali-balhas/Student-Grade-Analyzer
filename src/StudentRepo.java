import java.util.List;
import java.util.Map;

public class StudentRepo {

    public static final List<Student> students = List.of(
            new Student("Ali", Map.of(SubjectRepo.subjects.get(2), 100, SubjectRepo.subjects.getFirst(), 90)),
            new Student("Alaa", Map.of(SubjectRepo.subjects.get(2), 85, SubjectRepo.subjects.getFirst(), 75)),
            new Student("Nour", Map.of(SubjectRepo.subjects.get(2), 70, SubjectRepo.subjects.getFirst(), 50))
    );
}
