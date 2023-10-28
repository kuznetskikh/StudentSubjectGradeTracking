import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        StudentSubjectGradeService studentSubjectGradeService = new StudentSubjectGradeService();

        showManual();

        boolean listenCommand = true;
        while (listenCommand) {
            System.out.print("Введите команду: ");

            try {
                switch (scanner.nextLine()) {
                    case "1":
                        showManual();
                        break;

                    case "2":
                        loadStudentSubjectGradeLookupFromFile(scanner, fileService, studentSubjectGradeService);
                        break;

                    case "3":
                        saveStudentSubjectGradeLookupToFile(scanner, fileService, studentSubjectGradeService);
                        break;

                    case "4":
                        addStudent(scanner, studentSubjectGradeService);
                        break;

                    case "5":
                        deleteStudent(scanner, studentSubjectGradeService);
                        break;

                    case "6":
                        upsertSubjectGrade(scanner, studentSubjectGradeService);
                        break;

                    case "7":
                        showStudentSubjectGradeLookup(studentSubjectGradeService);
                        break;

                    case "8":
                        showSubjectGradeLookup(scanner, studentSubjectGradeService);
                        break;

                    case "9":
                        listenCommand = false;
                        break;

                    default:
                        System.out.println("неизвестная команда");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void showManual() {
        System.out.println("Показать справку - 1");
        System.out.println("Загрузить данные из файла - 2");
        System.out.println("Сохранить данные в файл - 3");
        System.out.println("Добавить ученика - 4");
        System.out.println("Удалить ученика - 5");
        System.out.println("Обновить оценку ученика - 6");
        System.out.println("Просмотреть оценки всех учеников - 7");
        System.out.println("Просмотреть оценки ученика - 8");
        System.out.println("Выход - 9");
    }

    private static void loadStudentSubjectGradeLookupFromFile(Scanner scanner, FileService fileService, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя файла: ");
        String fileName = scanner.nextLine();
        Map<String, Map<String, Integer>> studentSubjectGradeLookup = new HashMap<>((Map<String, Map<String, Integer>>)fileService.readFile(fileName));
        studentSubjectGradeService.setStudentSubjectGradeLookup(studentSubjectGradeLookup);
    }

    private static void saveStudentSubjectGradeLookupToFile(Scanner scanner, FileService fileService, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя файла: ");
        String fileName = scanner.nextLine();
        fileService.writeFile(fileName, studentSubjectGradeService.getStudentSubjectGradeLookup());
    }

    private static void addStudent(Scanner scanner, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя ученика: ");
        String studentName = scanner.nextLine();
        studentSubjectGradeService.addStudent(studentName);
    }

    private static void deleteStudent(Scanner scanner, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя ученика: ");
        String studentName = scanner.nextLine();
        studentSubjectGradeService.deleteStudent(studentName);
    }

    private static void upsertSubjectGrade(Scanner scanner, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя ученика: ");
        String studentName = scanner.nextLine();
        System.out.print("Название предмета: ");
        String subjectName = scanner.nextLine();
        System.out.print("Оценка: ");
        Integer grade = Integer.valueOf(scanner.nextLine());
        studentSubjectGradeService.upsertSubjectGrade(studentName, subjectName, grade);
    }

    private static void showStudentSubjectGradeLookup(StudentSubjectGradeService studentSubjectGradeService) {
        System.out.println(studentSubjectGradeService.getStudentSubjectGradeLookup());
    }

    private static void showSubjectGradeLookup(Scanner scanner, StudentSubjectGradeService studentSubjectGradeService) throws Exception {
        System.out.print("Имя ученика: ");
        String studentName = scanner.nextLine();
        System.out.println(studentSubjectGradeService.getSubjectGradeLookup(studentName));
    }
}
