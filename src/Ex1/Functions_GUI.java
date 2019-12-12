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
		ComplexFunction cf=new ComplexFunction();

		String theLine;
		function f=null;
		BufferedReader reader= new BufferedReader(new FileReader(file));
		theLine= reader.readLine();
		while(theLine!=null) 
		{

			System.out.println("&&&&&&&&&&&&&&&&&");
			System.out.println(theLine);
			System.out.println("&&&&&&&&&&&&&&&&&");

			f=cf.initFromString(theLine);

			add(f);
			theLine= reader.readLine();

		}
		reader.close();


		//		catch (IOException e) {
		//			System.out.println("your initFromFile fail");
		//		}
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		if (isEmpty())
			throw new RuntimeException("ERR: your list is empty ");
		try 
		{
			Iterator<function> it = listF.iterator();
			StringBuilder StringB = new StringBuilder();
			PrintWriter printW = new PrintWriter(new File(file));
			while (it.hasNext()) {
				String st="\n";
				function func = it.next();
				StringB.append(func.toString()+st);
			}	
			printW.write(StringB.toString());
			printW.close();
		} 
		catch (FileNotFoundException e) 
		{
			throw new IOException("ERR: The file is not found");
		}
		catch (Exception e)
		{
			throw new IOException("ERR: The file is not writable");

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
		StdDraw.setPenRadius(0.005);

		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());

		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(+0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		int count=0;
		Iterator s=listF.iterator();
		StdDraw.setPenRadius(0.005);
		while(s.hasNext()) {
			//StdDraw.setFont(new Font("calibrity",Font.PLAIN,14));
			StdDraw.setPenColor(Colors[count]);
			function c=((function)s.next());
			System.out.println(c);
			double step=(Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			for(double i=rx.get_min();i<rx.get_max();i=i+step) {
				double y=c.f(i);
				double y2=c.f(i+step);
				StdDraw.line(i,y,i+step, y2);
			}
			count++;
			if(count==Colors.length-1)
				count=0;
				

		}




	}

	@Override
	public void drawFunctions(String jsonFile) 
	{
		Gson myGson = new Gson();

		try {
			FileReader FReader = new FileReader(jsonFile);
			jsonParam gs=  myGson.fromJson(FReader , jsonParam.class);
			Range r_x= new Range(gs.rangeX[0] , gs.rangeX[1]);
			Range r_y= new Range(gs.rangeY[0] , gs.rangeY[1]);
			drawFunctions(gs.width, gs.height, r_x, r_y, gs.res);
		}

		catch (FileNotFoundException e) 
		{
			System.out.println("the file dont found");
		}

		catch (IOException e) 
		{
			System.out.println("your drawFunctions fail");
		}

	}
	public class jsonParam
	{
		public int width;
		public int height;
		public double[] rangeX;
		public double[] rangeY;

		public int res;
	}

	public static void main(String[] args) throws IOException 
	{
		Functions_GUI FuncG = new Functions_GUI();
		Functions_GUI FuncGS = new Functions_GUI();

		FuncG.initFromFile("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read\\guiExample.txt");
		System.out.println("done");

		ComplexFunction cf=new ComplexFunction();
		function f0 = new ComplexFunction();
		f0=cf.initFromString("mul(plus(2x^3+6x,9),comp(x,2x))");
		function f1 = new Polynom("2x^3+6x");
		function f2 = new Monom("x");
		Polynom p1 = new Polynom("-2X");
		Polynom p2 = new Polynom("4X");
		Monom m1 = new Monom("12x^2");
		FuncGS.add(f0);
		FuncGS.add(f1);
		FuncGS.add(f2);
		FuncGS.add(p1);
		FuncGS.add(p2);
		FuncGS.add(m1);
		FuncGS.saveToFile("newtext.txt");

		System.out.println("done1");
		Range x = new Range(-20,20);
		Range y = new Range(-20,20);
		FuncGS.drawFunctions(1000,1000,x,y,200);
	//	FuncG.drawFunctions("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read.json");
	}
}
