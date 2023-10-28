import java.util.HashMap;
import java.util.Map;

class StudentSubjectGradeService {
    private Map<String, Map<String, Integer>> studentSubjectGradeLookup;

    StudentSubjectGradeService() {
        studentSubjectGradeLookup = new HashMap<>();
    }

    void addStudent(String studentName) throws Exception {
        if (studentName == null) {
            throw new Exception("Имя ученика не указано");
        }

        if (studentSubjectGradeLookup.containsKey(studentName)) {
            throw new Exception("Ученик уже существует: " + studentName);
        }

        studentSubjectGradeLookup.put(studentName, new HashMap<>());
    }

    void deleteStudent(String studentName) throws Exception {
        if (!studentSubjectGradeLookup.containsKey(studentName)) {
            throw new Exception("Ученик не существует: " + studentName);
        }

        studentSubjectGradeLookup.remove(studentName);
    }

    void upsertSubjectGrade(String studentName, String subjectName, Integer grade) throws Exception {
        if (!studentSubjectGradeLookup.containsKey(studentName)) {
            throw new Exception("Ученик не существует: " + studentName);
        }

        if (subjectName == null) {
            throw new Exception("Название предмета не указан");
        }

        if (grade == null) {
            throw new Exception("Оценка не указана");
        }

        studentSubjectGradeLookup.get(studentName).put(subjectName, grade);
    }

    Map<String, Map<String, Integer>> getStudentSubjectGradeLookup() {
        return Map.copyOf(studentSubjectGradeLookup);
    }

    Map<String, Integer> getSubjectGradeLookup(String studentName) throws Exception {
        if (!studentSubjectGradeLookup.containsKey(studentName)) {
            throw new Exception("Ученик не существует: " + studentName);
        }

        return Map.copyOf(studentSubjectGradeLookup.get(studentName));
    }

    public void setStudentSubjectGradeLookup(Map<String, Map<String, Integer>> studentSubjectGradeLookup) {
        this.studentSubjectGradeLookup = studentSubjectGradeLookup;
    }
}
