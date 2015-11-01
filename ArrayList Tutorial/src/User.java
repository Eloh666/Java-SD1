public class User {

  public static void main(String argv[])
  {
    Country chad = new Country("Chad", "Africa",
                  1284000,98000000L, 15000000000L);
    System.out.println(chad.region);
    System.out.println(chad.area);
    chad.area = chad.area+100;
  }
}
