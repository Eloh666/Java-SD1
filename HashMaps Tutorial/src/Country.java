class Country{
  public String name;
  public String region;
  public int area;
  public long pop;
  public long gdp;
  public Country(String name,String region, int area, long pop, long gdp){
    this.name=name;
    this.region=region;
    this.area = area;
    this.pop=pop;
    this.gdp=gdp;
  }
  public String toString(){
    return "(name:'"+name+"',region:'"+region+"',area:"+area+"sqkm,pop:"+pop+",gdp:"+gdp+")";
  }
}
