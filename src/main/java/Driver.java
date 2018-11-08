import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Driver
 */
public class Driver {
  private static Map<Integer, List<BabyName>> yearToBnMap = new HashMap<>();

  public static void main(String[] args) throws Exception {

    File[] htmls = new File("src/main/resources/").listFiles();
    File outputFile = new File("src/main/resources/output.txt");
    Driver driver = new Driver();

    for(File html : htmls) {
      int fileYear = driver.getYearFromFileName(html);
      yearToBnMap.put(fileYear, driver.readBabyNameList(html));
    }

    if (!outputFile.exists()){
      outputFile.createNewFile();
    }

    PrintWriter pr = new PrintWriter(outputFile);


    for (Map.Entry<Integer, List<BabyName>> yearToBn : yearToBnMap.entrySet()) {
      int year = yearToBn.getKey();
      pr.print(year);
      
    }



    // 1. Load HTMLs one by one
    //   1. Check file path and load
    //   2. Name extraction
    //   3. Store names into variable

    // 2. Output to another file
    //   1. Check file path and load
    //   2. Write
  }

  private int getYearFromFileName(File html){
    return 0;
  }

  private List<BabyName> readBabyNameList(File html){

    try {
      BufferedReader br = new BufferedReader(new FileReader(html))
    }catch (IOException ioE) {

    }

    return null;
  }

}
