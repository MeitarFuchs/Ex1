package Ex1;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;
/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
	1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
	2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
	3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
	4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
	5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
	6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
public class gui_boaz_test {
	public static void main(String[] a) 
	{
		Polynom p = new Polynom("x");
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try 
		{
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);
		System.out.println("secss json");

	}
	private functions _data=null;
	//		@BeforeAll
	//		static void setUpBeforeClass() throws Exception {
	//		}

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	//@Test
	void testFunctions_GUI() {
		
	}

	@Test
	void testInitFromFile() 
	{
		try{
			Functions_GUI FuncG = new Functions_GUI();
			FuncG.initFromFile("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read\\guiExample.txt");
		}
		catch(Exception e) {
			fail();
		}


	}

	@Test
	void testSaveToFile() {
		try {	
			Functions_GUI FuncGS = new Functions_GUI();
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
			FuncGS.saveToFile("salome.txt");
		}
		catch (Exception e) {
			fail();
		}

	}

	//@Test
	void testDrawFunctions() {
		//_data.drawFunctions();
		//	fail("Not yet implemented");
	}

	//@Test
	//		void testDrawFunctionsIntIntRangeRangeInt() {
	//			_data.drawFunctions("GUI_params.txt");
	//			//fail("Not yet implemented");
	//		}
	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1+2.4x^2 -x^4";
		String s2 = "5+2x-3.3x + 0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());

		System.out.println("cf.toString():\n"+cf.toString());

		String s = cf.toString();
		System.out.println("s"+s);
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}
}
