package city.guide.db;

public class City {
  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String comment) {
    this.name = comment;
  }

  @Override
  public String toString() {
    return name;
  }
}
