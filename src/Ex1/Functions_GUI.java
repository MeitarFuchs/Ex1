package Ex1;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public class Functions_GUI implements functions
{
	public static Color[] Colors = {Color.blue, Color.cyan, 
			Color.MAGENTA, Color.ORANGE,Color.red, Color.GREEN, Color.PINK}; 

	LinkedList<function> listF=new LinkedList<function>();

	public Functions_GUI()
	{
		this.listF= new LinkedList<function>();
	}

	@Override
	public boolean add(function f) {
		return this.listF.add(f);
	}

	@Override
	public boolean addAll(Collection<? extends function> af) {

		return this.listF.addAll(af);
	}

	@Override
	public void clear() {
		this.listF.clear();
	}

	@Override
	public boolean contains(Object obj) {
		return this.listF.contains(obj);
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		return this.listF.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.listF.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return this.listF.iterator();
	}

	@Override
	public boolean remove(Object obj) {

		return this.listF.remove(obj);
	}

	@Override
	public boolean removeAll(Collection<?> ra) {

		return this.listF.removeAll(ra);
	}

	@Override
	public boolean retainAll(Collection<?> ret) {

		return this.listF.retainAll(ret);
	}

	@Override
	public int size() {
		return this.listF.size();
	}

	@Override
	public Object[] toArray() {

		return listF.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return this.listF.toArray(a);
	}

	@Override
	public void initFromFile(String file) throws IOException
	{
		try{
			ComplexFunction cf=new ComplexFunction();
			String theLine;
			function f=null;
			BufferedReader Br= new BufferedReader(new FileReader(file));
			theLine= Br.readLine();
			while(theLine!=null) 
			{
				f=cf.initFromString(theLine);
				add(f);
				theLine= Br.readLine();
			}
			Br.close();
		}
		catch (IOException e) {
			System.out.println("your initFromFile fail");
		}
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		try {
			String line= "";
			String sn="\n";
			Iterator it=listF.iterator();
			
			while(it.hasNext()) 
			{
				function f=((function)it.next());
				line+=f.toString()+sn;
			}
			
			FileWriter Fw=new FileWriter(file);
			Fw.write(line);
			Fw.close();
		}
		catch(Exception e) {
			System.out.println("Error: cant be writen");
		}
	}


	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width,height);

		StdDraw.setPenRadius( 0.003 );
		StdDraw.setPenColor( Color.PINK );

		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());


		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}

		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.004);

		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		StdDraw.setPenRadius(0.006);
		
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.20, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(+0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		
		int jcolor=0;
		int res=resolution;
		Iterator s=listF.iterator();
		StdDraw.setPenRadius(0.005);
		
		while(s.hasNext()) 
		{
			StdDraw.setPenColor(Colors[jcolor]);
			function c=((function)s.next());
			System.out.println(c);
			
			double jump=(Math.abs(rx.get_min())+Math.abs(rx.get_max()))/res;
			
			for(double i=rx.get_min(); i<rx.get_max(); i+=jump)
			{
				double y1=c.f(i);
				double y2=c.f(i+jump);
				StdDraw.line(i,y1,i+jump, y2);
			}
			
			jcolor++;
			
			if(jcolor==Colors.length-1)
				jcolor=0;

		}
	}

	@Override
	public void drawFunctions(String jsonFile) 
	{
		Gson Gs = new Gson();

		try {
			FileReader readP= new FileReader(jsonFile);
			jsonParam pj=Gs.fromJson(readP,jsonParam.class);
			
			Range ry=new Range(pj.Range_Y[0],pj.Range_Y[1]);
			Range rx=new Range(pj.Range_X[0],pj.Range_X[1]);
			drawFunctions(pj.Width,pj.Height,rx,ry,pj.Resolution);	
		}
		
		catch(Exception e) 
		{
			System.out.println("Error: the file cant be reader");
		}

	}
	
	public class jsonParam
	{
		public int Width;
		public int Height;
		public double[] Range_X;
		public double[] Range_Y;
		public int Resolution;
	}

}
