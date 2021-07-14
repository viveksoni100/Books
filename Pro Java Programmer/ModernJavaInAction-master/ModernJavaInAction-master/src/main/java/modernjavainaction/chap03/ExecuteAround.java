package modernjavainaction.chap03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class ExecuteAround {

  private static final File FILE = new File("/home/viveksoni100/GIT_REPO/Books/Pro Java Programmer/ModernJavaInAction-master/ModernJavaInAction-master/src/main/java/modernjavainaction/chap03/data.txt");

  public static void main(String... args) throws IOException {
    // method we want to refactor to make more flexible
    String result = processFileLimited();
    System.out.println(result);

    System.out.println("---");

    String oneLine = processFile((BufferedReader b) -> b.readLine());
    System.out.println(oneLine);

    String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
    System.out.println(twoLines);
  }

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return p.process(br);
    }
  }

  @FunctionalInterface
  public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;

  }

  /*this is how we catch exception in lambda syntax*/
  Function<BufferedReader, String> f =
          (BufferedReader b) -> {
            try {
              return b.readLine();
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          };

}
