import enums.Gender;

public class BabyName {

  private String name;
  private Gender gender;
  private int rank;

  public BabyName(String name, Gender gender, int rank){
    this.name = name;
    this.gender = gender;
    this.rank = rank;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }




}

