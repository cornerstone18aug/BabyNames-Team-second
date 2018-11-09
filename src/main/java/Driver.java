import enums.Gender;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Driver
 */
public class Driver {

  private static final Pattern TAG_PATTERN =
      Pattern.compile("<\\s*td[^>]*>(.*?)<\\s*/\\s*td><\\s*td[^>]*>(.*?)<\\s*/\\s*td><\\s*td[^>]*>(.*?)<\\s*/\\s*td>");
  private static final Map<Integer, List<BabyName>> YEAR_TO_BN_MAP = new HashMap<>();

  public static void main(String[] args) {

    File[] htmls = new File("src/main/resources/input").listFiles();
    Driver driver = new Driver();

    if (htmls == null) {
      throw new IllegalStateException();
    }
    Arrays.stream(htmls).forEach((File html) ->
      YEAR_TO_BN_MAP.put(driver.getYearFromFileName(html), driver.readBabyNameList(html))
    );

    Path path = Path.of("src/main/resources/output");
    if (!Files.exists(path)) {
      try{
        Files.createDirectories(path);
      } catch (IOException ioE) {
        throw new RuntimeException(ioE);
      }
    }

    if (args.length != 0 && "summaryfile".equals(args[0])) {
      for (Map.Entry<Integer, List<BabyName>> yearToBn : YEAR_TO_BN_MAP.entrySet()) {
        int year = yearToBn.getKey();
        String fileName = String.format("src/main/resources/output/%s.html.summary", year);

        try (PrintWriter pr = new PrintWriter(new File(fileName))) {
          pr.println(year);

          yearToBn.getValue().forEach(bn -> {
            pr.print(bn.getName());
            pr.println(" " + bn.getRank());
          });

        } catch (IOException ioE) {
          throw new RuntimeException(ioE);
        }

      }
    } else {
      PrintStream ps = new PrintStream(System.out);

      for (Map.Entry<Integer, List<BabyName>> yearToBn : YEAR_TO_BN_MAP.entrySet()) {
        int year = yearToBn.getKey();
        ps.println(year);

        yearToBn.getValue().forEach(bn -> {
          ps.print(bn.getName());
          ps.println(" " + bn.getRank());
        });

      }

    }

  }

  private int getYearFromFileName(File html) {
    return Integer.parseInt(html.getName().replaceAll("[a-z.]", ""));
  }
  
  private List<BabyName> readBabyNameList(File html) {
    List<BabyName> resultList;
    try {
      resultList = Files.lines(Paths.get(html.toURI()))
          .map(line -> {
            List<BabyName> list = new ArrayList<>();
            Matcher matcher = TAG_PATTERN.matcher(line);

            if (!matcher.find() || matcher.groupCount() != 3) {
              return null;
            }

            int rank = Integer.parseInt(matcher.group(1));
            list.add(
                new BabyName(
                    matcher.group(2),
                    Gender.MALE,
                    rank)
            );
            list.add(
                new BabyName(
                    matcher.group(3),
                    Gender.FEMALE,
                    rank)
            );

            return list;
          })
          .filter(Objects::nonNull)
          .collect(ArrayList::new, List::addAll, List::addAll);
    } catch (IOException ioE) {
      throw new RuntimeException("It couldn't happen");
    }

    // Sort
    resultList.sort(Comparator.comparing(BabyName::getName));

    return resultList;
  }

}
