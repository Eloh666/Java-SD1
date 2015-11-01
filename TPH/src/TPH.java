
public class TPH {

	public static void main(String[] args) 
	{

		int startValue = 40755;
		double endValue = 40755*100;
		int beginT = 285;
		int beginP = 165;
		int beginH = 143;
		double T = 0;
		double P = 0;
		double H = 0;
		Byte[] tList = new Byte[(int) endValue];
		Byte[] pList = new Byte[(int) endValue];
		Byte[] hList = new Byte[(int) endValue];
		boolean flag = true;
		
		for(int i = 0; i<endValue; i++)
		{
			tList[i] = 0;
			hList[i] = 0;
			pList[i] = 0;
		}
		
		for(int i = 0; i<endValue ; i++)
		{


			T = (beginT*(beginT + 1)/2);
			P = (beginP*(3 * beginP - 1) /2);
			H = beginH*(2 * beginH - 1);
					
			if(  T < endValue && T> 0)
			{
				tList[(int) T] = 1;
			}
			
			if( P < endValue && P> 0 )
			{
				pList[(int) P] = 1;
			}
			
			if(H  < endValue && H> 0)
			{
				hList[(int) H] = 1;
			}
			if(P == 68265)
			{
				System.out.println("\n"+T+"    "+P+"     "+H+"     ");
				System.out.println(beginT+"    "+beginP+"     "+beginH+"     ");
			}
			beginT++;
			beginP++;
			beginH++;
		}
		
		
		for(int i = 40755+1; i < endValue && flag; i++)
		{
				if(tList[i] == 1)
					if(pList[i] == 1)
						if(hList[i] == 1)
						{
							flag = false;
							System.out.println("------->  "+i);
						}
		}
		
		
		
		if(flag == true)
		System.out.println("Nada");
		System.out.println("Done");

	}

}
