import java.io.*;
import java.lang.Math;
class psosv
{
   
   
public static void main (String[] args)
{  
  double y[];
  int m=0;
  double yd;
  y= new double[8];
  double pbest[],gbest;
  pbest= new double[8];
  
  double w=0.75,c1=1.75,c2=2.0;
  double r[][];
  r= new double[900][2];
  
  /*generating random numbers */
 // System.out.println("Random numbers:");
  for(int i=0;i<900;i++)
  {
      for(int j=0;j<2;j++)
      {
          r[i][j]=Math.random();
          r[i][j]=Math.round(r[i][j]*10000)/10000.0;
          //System.out.print(r[i][j]+"     ");
      }
      //System.out.println();
  }
  
  double lb=0.5,ub=25.5;
  int a;
 
  /*initial iteration*/
  double x[],xt[];
  x= new double[8];
  xt= new double[8];
  double v[];
  v= new double[100];
  /* initial v will be 0 */
  for(int b=0;b<8;b++)
  {
      v[b]=0.0;
  }    
  for(a=0;a<8;a++)
  {
      xt[a]=(lb+(r[a][0]*(ub-lb)))+v[a];
      xt[a]=Math.round(xt[a]*10000)/10000.0;
      
      x[a]=xt[a];
      
      y[a]=((0.2*Math.pow(xt[a],2))+450/xt[a]);
      y[a]=Math.round(y[a]*10000)/10000.0;
      pbest[a]=xt[a];
      
      System.out.println("Particle "+ (a+1) +"]\t\t X: "+xt[a]+"\t\t Y: "+ y[a]+"\t\t pbest: "+pbest[a]  );
	  
  }
  yd=y[0];
  for(int b=1;b<8;b++)
  {
      //System.out.println("Yd: " +yd+ " y[b]: "+y[b] );  
      if(yd> y[b])
      {
         yd=y[b];
         m=b;
      }
  }
  
  
  gbest=pbest[m];
  
 
  System.out.println("Minimum of Y: "+yd+"\t\t Value of gbest: "+gbest );
  
  for(int b=0;b<8;b++)
  {
      
      v[b]=(w*v[b])+(c1*r[a][0]*(pbest[b]-xt[b]))+(c2*r[a][1]*(gbest-xt[b]));
      v[b]=Math.round(v[b]*10000)/10000.0;
      a=a+1;
      System.out.println("v["+(b+1)+"]: "+v[b]);
	  
  }
  
  System.out.println("New values of xt:");
 
 
  for(int b=0;b<8;b++)
  {
      xt[b] = v[b]+xt[b];
      xt[b] = (xt[b]<0.5)?0.5:xt[b];
	  xt[b] = (xt[b]>25.5)?25.5:xt[b];
	
      System.out.println("xt["+(b+1)+"]: "+xt[b]);
	  
  }
  
  double yold[]=new double[8];
  for(int b=0;b<8;b++)
  {
      yold[b]=y[b];
  }

  double ydup;
  ydup=yd; 
  /*further iteratives */
  for(int c=0;c<40;c++) //<1000
  {
      System.out.println("Iteration "+(c+1));
      System.out.println("new values of Y:");
      for(int b=0;b<8;b++)
      {
          y[b]=((0.2*Math.pow(xt[b],2))+450/xt[b]);
          y[b]=Math.round(y[b]*10000)/10000.0;
          System.out.println(y[b]);
      } 
      for(int b=0;b<8;b++)
      {
          if (yold[b]>y[b])
          {
              pbest[b]=xt[b];
          }
          else
          {
              y[b]=yold[b];
			  pbest[b]=x[b];
          }
      }	
	  
      for(int b=0;b<8;b++)
      {
		  System.out.println("Particle "+ (b+1) +"]\t\t X: "+xt[b]+"\t\t New Y: "+ y[b]+"\t\t pbest: "+pbest[b] );
	  }
      System.out.println();	  
      yd=y[0];
      for(int b=1;b<8;b++)
      {
         if (yd > y[b])
           {
              yd=y[b];
              m=b;
           }
      }
	  gbest=pbest[m];
      System.out.println("Minimum of Y for iteration "+(c+1)+": "+yd+"\t\t Value of gbest: "+gbest );
	  System.out.println();
	  for(int b=0;b<8;b++)
      {
          v[b]=(w*v[b])+(c1*r[a][0]*(pbest[b]-xt[b]))+(c2*r[a][1]*(gbest-xt[b]));
          v[b]=Math.round(v[b]*10000)/10000.0;
          a=a+1;
		  System.out.println("v["+(b+1)+"]: "+v[b]);
		  x[b]=xt[b];
          yold[b]=y[b];
      }
	  System.out.println();
	  System.out.println("New values of xt:");
      for(int b=0;b<8;b++)
      {
          xt[b] = v[b]+xt[b];
          xt[b] = (xt[b]<0.5)?0.5:xt[b];
	      xt[b] = (xt[b]>25.5)?25.5:xt[b];
          System.out.println("xt["+(b+1)+"]: "+xt[b]);
	  }
	  System.out.println();
	  System.out.println();
	  
  }
  System.out.println("Minimization of the given function Y is "+yd);  
	  
  }
  }