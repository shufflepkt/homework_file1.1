import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String root = "/Users/yuryshakhmatov/Games";
        StringBuilder log = new StringBuilder();

        String[] games = {"src", "res", "savegames", "temp"};
        String[] src = {"main", "test"};
        String[] main = {"Main.java", "Utils.java"};
        String[] res = {"drawables", "vectors", "icons"};
        String[] temp = {"temp.txt"};

        log = createDirsAndFiles(root, games, log, Type.DIRECTORY);
        log = createDirsAndFiles(root + "/" + games[0], src, log, Type.DIRECTORY);
        log = createDirsAndFiles(root + "/" + games[0] + "/" + src[0], main, log, Type.FILE);
        log = createDirsAndFiles(root + "/" + games[1], res, log, Type.DIRECTORY);
        log = createDirsAndFiles(root + "/" + games[3], temp, log, Type.FILE);

        try (FileWriter writer = new FileWriter(root + "/" + games[3] + "/" + temp[0])) {
            writer.write(String.valueOf(log));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static StringBuilder createDirsAndFiles(String path, String[] dirs, StringBuilder log, Type type) {
        for (String dir : dirs) {
            StringBuilder fullPath = new StringBuilder().append(path + "/" + dir);
            File directory = new File(String.valueOf(fullPath));

            if (type == Type.DIRECTORY) {
                if (directory.mkdir()) {
                    log.append("- Directory \"" + directory.getName() + "\" has been successfully created in the directory " + path + "\n");
                } else {
                    log.append("- Directory \"" + directory.getName() + "\" wasn't created in the directory " + path + "\n");
                }
            } else {
                try {
                    if (directory.createNewFile()) {
                        log.append("* File \"" + directory.getName() + "\" has been successfully created in the directory " + path + "\n");
                    }
                } catch (IOException e) {
                    log.append("* File \"" + directory.getName() + "\" wasn't created in the directory " + path + "\n");
                    System.out.println(e.getMessage());
                }
            }
        }

        return log;
    }
}