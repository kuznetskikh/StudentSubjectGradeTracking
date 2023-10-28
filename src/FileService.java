import java.io.*;

class FileService {
    Object readFile(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new Exception("Файл не существует: " + fileName);
        }

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            return objectInputStream.readObject();
        }
    }

    void writeFile(String fileName, Object data) throws Exception {
        File file = new File(fileName);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        }
    }
}
