package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Ex1.Monom;

class Monom_testing {
	//String[] good_Form = {"0", "-x", "5","x","2.5x^3", "-3.9x^4", "-5x^8", "9x"};
	Monom t = new Monom("-3.9x^4");

	@BeforeAll
	static void beforeAll() {
		System.out.println("*** Test Monom ****" );
	}

	@Test
	void testMul() {
		Monom multy = new Monom ("2.5X^3");
		Monom ans = new Monom ("-9.75x^7");
		multy.multipy(t);
		if (multy.equals(ans))
			System.out.println("Multiply of Monom sucess:" + multy.toString());
		else
			fail("the mul function dosent secss");
	}


	@Test
	void testAdd() {
		Monom add1 = new Monom ("-4x^4");
		Monom ans = new Monom ("-7.9x^4");
		add1.add(t);
		if (add1.equals(ans))
			System.out.println("Add function secss: " + add1);
		else
			fail("the add function dosent secss");
	}

	@Test
	void testDiv() {
		Monom deriva = new Monom ("-x");
		Monom ans=new Monom("-1");
		if (deriva.derivative().equals(ans))
			System.out.println( " the derivative function secss and is: " + deriva.derivative().toString());
		else
			fail("the div function dosent secss");
	}
	@Test
	void testF() {
		Monom f1 =  new Monom ("-5x^8"); 
		double e= f1.f(3);
		if (e== -32805)
			System.out.println("The function f secss and the value of the Monom at a point 3: " + e);
		else
			fail("the F function dosent secss");

	}

	@Test
	void testZero() {
		Monom Zero = new Monom ("0");
		boolean b = Zero.isZero();
		System.out.println("the Monom is zero?: " + b);
	}

	@Test
	void testEquals() {
		Monom monom1 = new Monom("-1.9999999999x");
		Monom monom2 = new Monom("-2x");
		System.out.println("check if equals , if the diffrent is eps or less?   "+monom1.equals(monom2));
		System.out.println( "Equal Monom1 and t? " + monom1.equals(t));
	}

	@AfterAll
	static void afterAll() {
		System.out.println("*** End of test Monom ****" );
	}



}


