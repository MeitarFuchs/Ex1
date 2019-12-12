
package Ex1;

import java.util.Comparator;
import java.util.function.DoubleToLongFunction;

import org.w3c.dom.traversal.TreeWalker;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final Monom One1 = new Monom(+1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator(); // ×”×—×•×–×”
	public static Comparator<Monom> getComp() {return _Comp;}
	/**
	 * constructor who get the power and the coefficient
	 * @param a the coefficient of the monom
	 * @param b the power of the monom
	 */
	public Monom(double a, int b){
		this.set_coefficient(a); // ×ž×§×“×�
		this.set_power(b); // jzev
	}
	/**
	 * constructor who get a monom
	 * @param ot the monom she get to build the class monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());    //×”×¢×ª×§×” ×¢×ž×•×§×” ×©×œ ×ž×•× ×•×� ×œ×ž×•× ×•×�
	}
	/**
	 * update the coefficient of the class
	 * @return the coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * update the power of the class
	 * @return the power
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method check if the monom is the zero monom
	 * @return return true if the monom is the monom zero and false if it isn't
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************

	/**
	 * this method check if the string she get is a monom or not
	 * @param s the string she need to check
	 * @return yes if it's a form of monom and not if isn't
	 */
	public boolean IsMonom(String s)
	{
		int i=0;
		boolean flagPoint=false;

		if (s.length()==1)
		{	
			if (s.charAt(0)=='0')
				return true;
		}

		if (i<s.length()&&(s.charAt(i)=='+'|| s.charAt(i)=='-'))
		{
			i++;
		}

		if (i<s.length())
		{
			if((s.charAt(i)=='x') ||( s.charAt(i)=='X'))
			{
				i++;
			}

			else 
			{ //× ×¨×�×” ×™×© ×œ×š ×ž×§×“×�
				while (i<s.length() && (s.charAt(i)!= 'x' && s.charAt(i)!='X'))
				{ 
					if ((s.charAt(i)>57 || s.charAt(i)<48) && s.charAt(i)!='.')
					{ 
						return false;
					}

					else 
					{
						if (s.charAt(i)=='.')
						{ 
							if (flagPoint==false)
							{
								flagPoint=true;
								i++;
								if (i<s.length() && (s.charAt(i)>57 || s.charAt(i)<48))
									return false;
							}
							else 
								if (flagPoint)
								{
									return false;
								}
						}

						else 
						{
							if (s.charAt(i)<=57 || s.charAt(i)>=48)
							{
								i++;
							}
						}
					}
				}
			}

			if (i<s.length() && ((s.charAt(i)=='x')|| (s.charAt(i)=='X')))
			{	
				i++;
			}

			if (i<s.length()) 
			{
				if (s.charAt(i)!='^') 
				{
					return false;
				}	
				else 
					i++;
			}


			if (i<s.length())
			{
				//newPow="";
				for (int j=i; j<s.length(); j++)
				{
					if (s.charAt(i)<= 57 && s.charAt(i)>=48) 
					{
						//newPow+=s.charAt(i);
					}
					else 
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * this method check if the string is a monom (with Ismonom) if it is she  build it and if not she throw an exception 
	 * @param s the string she need to check
	 */
	public Monom(String s) 
	{
		int i=0;
		String newCof="+";
		String newPow="";

		boolean theNumIsNegtiv=false;
		if (!IsMonom(s))
		{
			throw new RuntimeException("not monom");
		}
		else {

			if (i<s.length()&&(s.charAt(i)=='+'|| s.charAt(i)=='-'))
			{
				if (s.charAt(i)=='-')
				{
					theNumIsNegtiv=true;
				}
				i++;
			}

			if (i<s.length())
			{
				if((s.charAt(i)=='x') ||( s.charAt(i)=='X'))
				{
					newCof+='1';
					newPow+='1';
					i++;
				}
				else 
				{ // ×ž×§×“×�
					while (i<s.length() && (s.charAt(i)!= 'x' && s.charAt(i)!='X'))
					{ 
						newCof+=s.charAt(i);
						i++;

					}

				}
			}


			if (i<s.length() && ((s.charAt(i)=='x')|| (s.charAt(i)=='X')))
			{	
				newPow="1";
				i++;
			}

			if (i<s.length()) 
			{
				i++;
			}


			if (i<s.length())
			{
				newPow="";
				for (int j=i; j<s.length(); j++)
				{
					newPow+=s.charAt(i);
				}
			}

			if (!newCof.equals("+1"))
			{
				if (theNumIsNegtiv)
					this.set_coefficient((Double.parseDouble(newCof))*(-1));
				else
					this.set_coefficient((Double.parseDouble(newCof)));
			}
			else
			{
				if (theNumIsNegtiv)
					this.set_coefficient(-1);
				else
					this.set_coefficient(1);
			}

			if (newPow.equals(""))
			{
				this.set_power(0);
			}
			else
			{
				this.set_power(Integer.parseInt(newPow));
			}
		}
	}

	/**
	 * check if this monom and  monom m1 is equal
	 * @param m1 the monon who check if is equals to this monom
	 * @return true if it equals and false if not
	 */
	public boolean equals(Monom m1)
	{
		double myCof=this.get_coefficient();
		double m1Cof=m1.get_coefficient();

		if (this.get_power()!=m1.get_power())
			return false;
		if (this.get_coefficient()==0 && m1.get_coefficient()==0)
			return true;

		if ((Math.abs(myCof-m1Cof))>Monom.EPSILON)
			return false;

		return true;
	}

	/**
	 * add monon m to this monom
	 * @param m the monom who has added to this monom
	 */
	public void add(Monom m) {

		if (this.get_power()==m.get_power())
			this.set_coefficient(this.get_coefficient()+ m.get_coefficient());
		else
		{
			throw new RuntimeException("the pows don't equal");
		}

	}   
	/**
	 * multiply monon d to this monom
	 * @param d the monom who multuply with this monom
	 */
	public void multipy(Monom d) { //×”×›×¤×œ×ª ×ž×•× ×•×ž×™×�

		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());

	}

	/**
	 * this method do the derivative of this monom
	 * @return the monom after the derivative
	 */
	public Monom derivative() { // × ×’×–×¨×ª
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);// ×—×¨×ª ×ª×—×–×™×¨ ×ž×•× ×•×� ×—×“×© 
	}

	/**
	 * put the value she get in x and calculating the value of the monom with it
	 * @param x the value she get
	 * @return the value of the monom with the x she get
	 */
	public double f(double x) { //×ž×¦×™×‘ x ×‘×¤×•× ×§×™×¦×”
		double ans=0;
		int p = this.get_power();
		double cof=this.get_coefficient();
		ans = (cof)*(Math.pow(x, p));
		return ans;
	} 

	/**
	 * print this monon
	 */
	public String toString()
	{
		String ans = "";

		if (isZero())//this.get_coefficient()==0
			return "+0";

		if (this.get_coefficient()>0)
			ans+='+';

		ans+=this.get_coefficient();
		ans+='x';
		ans+='^';
		ans+=this.get_power();	

		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************

	/**
	 * change this coefficient
	 * @param a the new coefficient
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}


	/**
	 * change this power
	 * @param p the new power
	 */

	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	/**
	 * the function get a string and if it is a monom she build it. 
	 * @param s  the string that the method check
	 * @return the function who was build with the string 
	 */
	@Override
	public function initFromString(String s) {
		function m = new Monom(s);
		return m;
	}

	/**
	 *  copy this monon to a new function
	 * @return the new function who build with this monom 
	 */
	@Override
	public function copy() {
		Monom m = new Monom(this.get_coefficient(),this.get_power());
		return m;
	}
	/**
	 * check if the object is equal to this monom
	 * @param obj the object we get to check if it equal to this monom
	 * @return true if it is equal and false if not
	 */
	public boolean equals(Object obj) 
	{
		String nameObj= obj.getClass().getName();
		if (!nameObj.equals("Monom"))
		{
			return false;
		}
		else
		{
			Monom m=new Monom (obj.toString());
			if ((m.get_coefficient()==this.get_coefficient()) && (m.get_power()==this.get_power()))
			{
				return true;
			}
		}
		return true;
	}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private static Monom getNewMinus1Monom() {return new Monom(MINUS1);}
	
	
	private double _coefficient; 
	private int _power;

}
