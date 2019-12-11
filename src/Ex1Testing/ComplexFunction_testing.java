package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunction_testing {

	String opS="Plus";
	String  s11= "Min(Plus(+x^2,3x),max(-2x^8, +6-4x^7))";
	String  s2= "plus(x^2, 3)";
	String  s3= "plus(x^2, 3)";
	String  s4= "plus(x^2, 3)";

	@BeforeAll
	static void beforeAll() {
		System.out.println("*** Test ComplexFunction ****" );
	}


	@Test
	void testBuildComplexFunction() 
	{
		Polynom p1 = new Polynom("-2X");
		Polynom p2 = new Polynom("4X");
		Monom m1 = new Monom("12x^2");
		ComplexFunction cfZero= new ComplexFunction(Monom.ZERO);
		ComplexFunction cf0= new ComplexFunction();
		function f0=new ComplexFunction();
		f0=cf0.initFromString("Mul(4X,12x^2)");

		String Sp="Divid(-2x,4X)";
		ComplexFunction cf1= new ComplexFunction();
		function f1=new ComplexFunction();
		f1=cf1.initFromString(Sp);

		ComplexFunction cf2= new ComplexFunction(Operation.Divid ,p1,p2);
		ComplexFunction cf3= new ComplexFunction("mul", p2, m1);
		ComplexFunction cf4= new ComplexFunction(Operation.Divid ,p1,p2);
		ComplexFunction cf5= new ComplexFunction("mul", cf3, cf4);
		ComplexFunction cf6= new ComplexFunction(cf4);
		ComplexFunction cf7= new ComplexFunction(p2);
		String sp5="Times(Times(+4.0x^1,+12.0x^2),Divid(-2.0x^1,+4.0x^1))";
		if (!cf4.equals(cf4))
			fail("building CF does not secss");

		if (!cf2.equals(cf4))
			fail("building CF does not secss");

		if(!cf2.equals(f1))
			fail("building CF does not secss");

		if(!(cf5.toString().equals(sp5)))
			fail("building CF does not secss");
		if(!(cf4.equals(cf6)))
			fail("building CF does not secss");
		if(!(cf7.equals(p2)))
			fail("building CF does not secss");
		if(!(cfZero.equals(Monom.ZERO)))
			fail("building CF with MONOMZERO does not secss");
	}

	@Test
	void testPlus() {
		function f1 = new Polynom("1x+3");
		function f2 = new Polynom("x^4");
		function f3 = new Monom("x^4");

		ComplexFunction cf4 = new ComplexFunction();
		function f4 = new ComplexFunction();
		f4=cf4.initFromString("mul(x^4+3,x^2)");

		double x=2;
		int ans1=21;
		int ans2=81;

		ComplexFunction cf1 = new ComplexFunction("Plus", f1, f2);
		if ((cf1.f(x))!=ans1)
			//System.out.println("test plus");
			fail("your F on the CF with the 2 polynoms you gave does not work");
		ComplexFunction cf2 = new ComplexFunction(opS, f1, f3);
		if ((cf2.f(x))!= ans1) 
			//System.out.println("test plus");
			fail("your F on the CF with polynom and monom you gave does not work");

		ComplexFunction cf3 = new ComplexFunction(opS, f1, f4);
		System.out.println("kkkkkkkkk"+cf3.f(x));
		if ((cf3.f(x))!= ans2) 
			fail("your F on the CF with polynom and CF you gave does not work");
	}


	@Test
	void testMul() {
		function f1 = new Polynom("x+3");
		function f2 = new Polynom("x^2");
		double ans=20;
		double x=2;
		ComplexFunction cf1 = new ComplexFunction("Mul", f1,f2);

		if ((cf1.f(x))!=ans) 
		{ 
			fail("your mul function on CF does not secss");
		}
	}

	@Test
	void testDiv() {
		function f1 = new Polynom("2x^3+6x");
		function f2 = new Monom("x");
		ComplexFunction cf1 = new ComplexFunction("Divid", f1, f2);
		double ans=14;
		double x=2;
		if ((cf1.f(x))!=ans) 
		{ 
			fail("your div function on CF does not secss");
		}
		function f3 = new Monom("0");
		ComplexFunction cf2= new ComplexFunction("Divid", f1, f3);
		try {
			cf2.f(x);
			fail("your div function on CF with MONOMZERO does not secss because you can not div with zero in f2");
		}
		catch (Exception e) 
		{
			System.out.println("your div function on CF with MONOMZERO in f2 does not secss and it good!!!!!");
		}
	}
	@Test
	void testMax() {
		function f1 = new Polynom("2x^3+6x");
		function f2 = new Monom("x");
		ComplexFunction cf1 = new ComplexFunction("Max", f1, f2);
		double ans=28;
		double x=2;
		if ((cf1.f(x))!=ans) 
		{ 
			fail("your max function on CF does not secss");
		}
	}

	@Test
	void testMin() {
		function f1 = new Polynom("2x^3+6x");
		function f2 = new Monom("x");
		ComplexFunction cf1 = new ComplexFunction("Min", f1, f2);
		double ans=2;
		double x=2;
		if ((cf1.f(x))!=ans) 
		{ 
			fail("your min function on CF does not secss");
		}
	}


	@Test
	void testComp() {
		double ans1=28;
		double ans2=76;
		double x=2;

		function f1 = new Polynom("2x^3+6x");
		function f2 = new Monom("x");

		function f3 = new ComplexFunction();
		ComplexFunction cf = new ComplexFunction();
		f3=cf.initFromString("mul(x^4+3,x^2)");

		ComplexFunction cf1 = new ComplexFunction("Comp", f1, f2);
		ComplexFunction cf2 = new ComplexFunction("Comp", f2, f3);
		System.out.println("ooooooooooooooooooooooooooo"+cf2.f(x));

		if ((cf1.f(x))!=ans1) 
		{ 
			fail("your comp function on CF does not secss");
		}

		if ((cf2.f(x))!=ans2) 
		{ 
			fail("your comp function on CF does not secss");
		}
	}


	@Test
	void testF() {
		Polynom p1 = new Polynom("2x+5");
		Polynom p2 = new Polynom("5");
		Polynom p3 = new Polynom("2x^2-6");

		double x=1;
		ComplexFunction cf1= new ComplexFunction(opS, p1, p2);
		ComplexFunction cf2= new ComplexFunction("divid", cf1, p3);
		ComplexFunction cf4= new ComplexFunction("min", cf1, p3);
		ComplexFunction cf5= new ComplexFunction("max", cf1, p3);
		ComplexFunction cf3= new ComplexFunction("times", cf1, p3);
		ComplexFunction cf6= new ComplexFunction("comp", cf1, p3);


		if (cf1.f(x)!=12)  
		{
			fail("your plus function on CF does not secss");
		}
		if (cf2.f(x)!=-3) 
		{
			fail("your div function on CF does not secss");
		}

		if (cf4.f(x)!=-4) 
		{
			fail("your min function on CF does not secss");
		} 

		if (cf5.f(x)!= 12)  
		{
			fail("your max function on CF does not secss");
		} 
		if (cf3.f(x)!=-48)  
		{
			fail("your times function on CF does not secss");
		} 
		if (cf6.f(x)!=2) 
		{
			fail("your comp function on CF does not secss");
		} 
	}


	@Test
	void testToString() {
		String sp = "2x^3+2x-8";
		String sm = "3x^4";
		Polynom p1 = new Polynom(sp);
		Monom m1 = new Monom(sm);
		ComplexFunction cfZero= new ComplexFunction(Monom.ZERO);
		ComplexFunction ansCF =new ComplexFunction();
		function fans=new ComplexFunction();
		fans=ansCF.initFromString("min(2x^3+2x-8,3x^4)");
		System.out.println("cfans.toString() "+  fans.toString());

		ComplexFunction ansCF2 =new ComplexFunction();
		function fans2=new ComplexFunction();
		fans2=ansCF2.initFromString("plus(2x^3+2x-8,comp(min(2x^3+2x-8,3x^4),divid(2x^3+2x-8,3x^4)))");
		System.out.println("cfans2.toString() "+  fans2.toString());

		ComplexFunction cf1 = new ComplexFunction("min", p1, m1);
		ComplexFunction cf2 = new ComplexFunction("divid", p1, m1);
		ComplexFunction cf3 = new ComplexFunction("comp", cf1, cf2);

		ComplexFunction cf4  = new ComplexFunction("Plus", p1, cf3);
	
		if (!(cfZero.toString().equals(Monom.ZERO.toString())))
		{
			fail("your toString function on CF does not secss");
		}
		if (!(cf1.equals(fans)))
		{
			fail("your toString function on CF does not secss");
		}

		if (!(cf4.equals(fans2)))
		{
			fail("your toString function on CF does not secss");
		}
	}


	@Test
	void testInitFromString() {
		String s1 = "3x+5-x^2";
		String s2 = "plus(3x+5-x^2, 7)";

		ComplexFunction cf1 =new ComplexFunction();
		function f1=new ComplexFunction();
		f1=cf1.initFromString(s1);

		ComplexFunction cf2 =new ComplexFunction();
		function f2=new ComplexFunction();
		f2=cf2.initFromString(s2);


		String ans1="-1.0x^2+3.0x^1+5.0x^0";
		String ans2="Plus(-1.0x^2+3.0x^1+5.0x^0,+7.0x^0)";

		if (!ans1.equals(f1.toString()))
			fail("your initFromString function on CF does not secss");


		if (!ans2.equals(f2.toString()))
			fail("your initFromString function on CF does not secss");
	}

	@Test
	void testCopy() {
		double x=2;
		function f1  = new Polynom("5-x^4");
		function f2 = new Monom("x^2-7");
		function fZero  = new Polynom("0");

		ComplexFunction cf = new ComplexFunction("Comp", f1, f2);
		ComplexFunction cfZero = new ComplexFunction(fZero);
		ComplexFunction cfCopy = (ComplexFunction)cf.copy();
		ComplexFunction cfCopy2 = (ComplexFunction)cfZero.copy();

		if (cf.f(x)!=cfCopy.f(x))
			fail("your copy function on CF does not secss");
		if (cfZero.f(x)!=cfCopy2.f(x))
			fail("your copy function on CF zero does not secss");

	}

	@Test
	void testEqualsWithObj() {
		function f1  = new Polynom("5-x^4");
		function f2 = new Polynom("2x^7+x^2-7");
		ComplexFunction cf3 =new ComplexFunction();
		function f3=new ComplexFunction();
		f3=cf3.initFromString("2x^7+x^2-7");
		ComplexFunction cfzero =new ComplexFunction(Monom.ZERO);
		ComplexFunction cf1 = new ComplexFunction("Mul", f1, f2);
		ComplexFunction cf2 = new ComplexFunction("Mul",f1,f3);

		if (!(cf1.equals(cf2))) 
		{ 
			fail("your equals function on CF does not secss");
			}
		if (!(cfzero.equals(Monom.ZERO))) 
		{ 
			fail("your equals function on CF does not secss");
			}
		
	}




}
