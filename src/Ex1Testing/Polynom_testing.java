package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class Polynom_testing {
	String[] MonomSt= {"0","-2","+x", "5","-1x^2", "6.5x^3"};

	@BeforeAll
	static void beforeAll() {
		System.out.println("*** Test Polynom ****" );
	}

	@Test
	void testAddPM() {
		Polynom p5 = new Polynom("6");
		Polynom ans = new Polynom("6-x^2+6.5x^3");
		Monom m1=new Monom(MonomSt[4]);
		Monom m2=new Monom(MonomSt[5]);
		p5.add(m1);
		p5.add(m2);
		if (p5.equals(ans))
			System.out.println("the function add monom to polynom sucess and p5 is:  "+p5.toString());
		else
			fail("the the function add monom to polynom doesnt sucess");
	}
	@Test
	void testAddPP() {
		Polynom addPo= new Polynom("2-x+2x^3");
		Polynom p1 = new Polynom("0+x");
		Polynom ans = new Polynom("2+2x^3");
		p1.add(addPo);
		if (p1.equals(ans))
			System.out.println("the poly after add poly 2-x+2x^3:  " + p1.toString());
		else
			fail("the the function add polynom to polynom doesnt sucess");
	}
	@Test
	void testSubPP() {
		
		Polynom subPo= new Polynom("6+2x^3");
		Polynom p1 = new Polynom("0+x");
		Polynom ans = new Polynom("-6+x-2x^3");
		Polynom ans2 = new Polynom("0");
		p1.substract(subPo);
		if (p1.equals(ans))
			System.out.println("The poly after substract:  " + p1.toString());
		else
			fail("the the function sub1 polynom to polynom doesnt sucess");
		
		p1.substract(p1);
		if (p1.equals(ans2))
			System.out.println("The poly after substract:  " + p1.toString());
		else
			fail("the the function sub2 polynom to polynom doesnt sucess");
	}

	@Test
	void testFP() {
		Polynom p1 = new Polynom("0+x-2x^4");
		double x=3;

		if (p1.f(x)==-159)
			System.out.println("The f(x) at the value of the x :   "+p1.f(x));
		else
			fail("the the function f to polynom doesnt sucess");
	}
	@Test
	void testMulPM() {
		Monom tempM = new Monom ("5x^3");
		Polynom p1 = new Polynom("8-x^2");
		Polynom ans = new Polynom("40x^3-5x^5");
		p1.multiply(tempM);
		if (p1.equals(ans))
			System.out.println("The polynom after we add tempMonom is :    " + p1.toString());
		else
			fail("the the function mul monom to polynom doesnt sucess");		
	}
	@Test
	void testMulPP() {
		Polynom mulPo = new Polynom("2+x+x^2");
		Polynom p1 = new Polynom("0+x-2x^4");
		Polynom ans = new Polynom("x^2+x^3-4x^4-2x^5-2x^6");
		p1.multiply(mulPo);
		if (p1.equals(ans))
			System.out.println("The poly after multiply in Polynom in 2+x+x^2: "  + p1.toString());
		else
			fail("the the function mul polynom and polynom doesnt sucess");
	}

	@Test
	void testCopyP() {
		Polynom p1 = new Polynom("0+x-2x^4");
		Polynom_able copyPo =new Polynom(); 
		copyPo=(Polynom)p1.copy();
		if (p1.equals(copyPo))
			System.out.println("The copy1 of the orginal our polynom   " + copyPo.toString());
		else
			fail("the the function copy polynom  doesnt sucess");
	}

	@Test
	void testIsZero() {
		Polynom p1 = new Polynom("0+x-2x^4");
		System.out.println("If the polymon is ZERO?:   " + p1.isZero());
	}

	@Test
	void testEqualsEpsM() {
		Monom m1 = new Monom("-1.9999999999");
		Monom m2 = new Monom("-2");
		System.out.println("the value of our eps:   "+Monom.EPSILON);
		System.out.println("check equals if the diffrents is eps ro less:   "+m1.equals(m2));
	}
	@Test
	void testDiv() {
		Polynom p = new Polynom("6+2.2x-4x^2");
		Polynom ans = new Polynom("2.2-8x");
		if ((p.derivative()).equals(ans))
			System.out.println("The function dertivite sucess:  " +(p.derivative().toString()));
		else
			fail("the the function div polynom  doesnt sucess");
	}

	/*@Test
		void testArea() {
			Polynom p = new Polynom("6+2.2x-4x^2");
			if (p.area(3, 6, Monom.EPSILON)==-204.3)
		System.out.println("the area:   " +p.area(-2,2,Monom.EPSILON));
			else
				fail("the the function area doesnt sucess");
		}
		@Test
		void testRoot() {
			Polynom p = new Polynom("6+2.2x-4x^2");
			if (p.root(-2,1,Monom.EPSILON)==6)
		System.out.println("the root:  "  +p.root(-2,1,Monom.EPSILON) );
			else
				fail("the the function root  doesnt sucess");
		}*/

	@AfterAll
	static void afterAll() {
		System.out.println("*** End of test Polynom ****" );
	}
}
