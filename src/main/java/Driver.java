import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Driver
 */
public class Driver {
  private Map<Integer, List<BabyName>>

  public static void main(String[] args) {

    File[] htmls = new File("src/main/resources/").listFiles();
    File outputFile = new File("src/main/resources/output.txt");

    for(File html : htmls) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(html))
      }catch (IOException ioE) {

      }




    }



    // 1. Load HTMLs one by one
    //   1. Check file path and load
    //   2. Name extraction
    //   3. Store names into variable

    // 2. Output to another file
    //   1. Check file path and load
    //   2. Write
  }

}
