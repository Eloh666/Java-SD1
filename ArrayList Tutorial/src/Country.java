class Country {
  public String name;
  public String region;
  public int area;
  public long pop;
  public long gdp;
  public Country(String name,String region,
                 int area, long pop, long gdp)
  {
   this.name = name;
   this.region = region;
   this.area = area;
   this.pop = pop;
   this.gdp = gdp;
  }
  public Country(String line)
  {
    String f[] = line.split("\t");
    name = f[0];
    region = f[1];
    area = Integer.parseInt(f[2]);
    pop = Long.parseLong(f[3]);
    gdp = Long.parseLong(f[4]); 
  }
  public String toString() { return "Country(" + name+
    ","+region+","+area+"sq km,"+pop+",$"+gdp+")"; }
}
