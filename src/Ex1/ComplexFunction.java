package Ex1;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation op=Operation.None;
	/**
	 * a empty constructor for complexFunction
	 */
	public ComplexFunction()
	{
		this.left=null;
		this.right=null;
	}

	public ComplexFunction(Polynom p)
	{
		this.setLeft(p);
		this.right=null;
		this.op=Operation.None;
	}
	/**
	 * constructor who get Function 
	 * @param f the function she get for the constructor
	 */
	public ComplexFunction(function f)
	{
		if (f instanceof ComplexFunction)
		{
			this.op=((ComplexFunction) f).op;
			this.left=((ComplexFunction) f).left;
			this.right=((ComplexFunction) f).right();
		}
	}
	/**
	 * constructor who get two function and one string 
	 * @param s the operation of the complexFunction
	 * @param f1 one of  the function for the complexFunction
	 * @param f2 the second function for the complex function
	 */
	public ComplexFunction(String s,function f1, function f2)
	{	
		this.op=checkingOp(s);

		this.left=f1;
		this.right=f2;
	}
	/**
	 * constructor who get two function and one Operetion 
	 * @param op the operation of the complexFunction
	 * @param f1 one of  the function for the complexFunction
	 * @param f2 the second function for the complex function
	 */
	public ComplexFunction(Operation op ,function f1,function f2)
	{
		this.left= f1;
		this.right= f2;
		this.op=op;

		if (f1==null)
		{
			this.left=null;
		}		
		if (f2==null) 
		{ 

			if (op==Operation.Plus||op==Operation.Min||op==Operation.Max||op==Operation.Comp)
				this.right=Monom.ZERO;
			if (op==Operation.Times||op==Operation.Divid)
				this.right=Monom.One1;
			if (op==Operation.None)
				this.right=null;

		}
	}

	/** 
	 * this function help find the Operation (as string) from the string she get and return it to initFromString function
	 * @param s the string she get to find the complexFunction operation
	 * @return the operation(as string) she find
	 */
	private String findOp(String s)
	{
		int i=0;
		String st="";
		while (i<s.length() && s.charAt(i)!= '(')
		{
			st+=s.charAt(i);
			i++;
		}

		return st;
	}
	/**
	 *  help find the left side of the "tree"(ComplexFunction),from the string she get and return it to initFromString function
	 * @param s the string she get to find the left side of the complexFunction 
	 * @param i the index she need for starting to look for the left side
	 * @return
	 */
	private String leftSide(String s , int i)
	{
		int countOpen=0;
		int countPsik=0;
		boolean out=false;
		String st="";
		if (s.charAt(i-1)=='(')
			countOpen++;

		while(i<s.length() && !out)
		{
			if (s.charAt(i)==',')
				countPsik++;
			if (s.charAt(i)=='(')
				countOpen++;

			if (countPsik==countOpen && s.charAt(i) == ',')
				out=true;
			if (!out)
				st+=s.charAt(i);
			i++;
		}
		return st;
	}
	/**
	 * help find the right side of the "tree"(ComplexFunction)),from the string she get and return it to initFromString function
	 * @param s the string she get to find the right side of the complexFunction 
	 * @param i the index she need for starting to look for the right side
	 * @return
	 */
	private String rightSide(String s)
	{
		int i=0;
		int countOpen=0;
		int countPsik=0;
		boolean out=false;
		String st="";

		while(i<s.length() && !out)
		{
			if (s.charAt(i)==',')
				countPsik++;
			if (s.charAt(i)=='(')
				countOpen++;
			if (countPsik==countOpen && s.charAt(i) == ',')
				out=true;

			i++;
		}

		while(i<s.length()-1)
		{
			st+=s.charAt(i);
			i++;
		}
		return st;
	}
	/**
	 * the function get a string and if it is a complexFubction she build it. 
	 * the frame of the building: the operation is the head/father and the function is the children (right and left)
	 * if there is no operation (in this case : the string is a Monon or polynom) the operation will be None 
	 * if there is no operation and just one function (in this case : the string is a Monon or polynom) the operation will be None and the f will be in the left. 
	 * this function use IsCF func and Polynom\Monom initFromString func.
	 * @param s the string she get to build the complexFunction
	 * @return the function she build 
	 */
	@Override
	public function initFromString(String s) {
		s=s.replace(" ", "");
		s=s.toLowerCase();
		int i=0;
		boolean closeOpen=false;
		boolean plusMinus=false;

		String beLeft="";
		String beRight="";
		function f=new ComplexFunction();

		if (s.charAt(0)=='+' || s.charAt(0)=='-')
		{	
			i++;
		}

		while( i<s.length() && closeOpen==false ) 
		{
			if ( s.charAt(i)== '(' || s.charAt(i)== ')')
			{	
				closeOpen=true;
			}
			else
			{
				if ( s.charAt(i)== '+' ||s.charAt(i)== '-')
				{	
					plusMinus=true;
				}
			}

			i++;
		}

		if (closeOpen) // maybe CF
		{
			function func=null;
			if (IsCF(s))
			{
				beLeft= leftSide(s,i--);
				beRight= rightSide(s);

				if ((beLeft.charAt(0) == '+' || beLeft.charAt(0) == '-'||beLeft.charAt(0) <= '9' && beLeft.charAt(0) >= '0') || 
						beLeft.charAt(0) == 'x'|| beLeft.charAt(0) == 'X')
					this.left = new Polynom(beLeft);

				else 
					this.left = initFromString(beLeft);

				if ((beRight.charAt(0) == '+' || beRight.charAt(0) == '-'||beRight.charAt(0) <= '9' && beRight.charAt(0) >= '0') || 
						beRight.charAt(0) == 'x'|| beRight.charAt(0) == 'X')
					this.right = new Polynom(beRight);

				else 
					this.right = initFromString(beRight);

				complex_function func1= new ComplexFunction(findOp(s), left ,right );
				return func1;
			}
			else
			{
				func= null;
			}	
			return func;
		}

		if (!closeOpen && plusMinus) // maybe P
		{
			Polynom p= new Polynom(s);
			function func2= new ComplexFunction(p);
			return func2;
		}
		if (!closeOpen && !plusMinus) // maybe M
		{
			Monom m= new Monom(s);
			function func3= new ComplexFunction(m);
			return func3;			
		}
		return f;
	}
	/**
	 * this function help the initfromstring function to check if the string she get is in the frame:checking if "s" is looks like :   "op(   ,    )".
	 * @param s the string get and check if it is legal complexFunction
	 * @return true if it is a legal complexFunction and false if it's not
	 */
	private boolean IsCF(String s)
	{
		boolean ans=true;
		int i=0;
		String tempOp="";
		int countOpen=0;
		int countPsik=0;

		while (i<s.length()&& (s.charAt(i)!= '(')) 
		{
			tempOp+=s.charAt(i);
			i++;
		}

		if (checkingOp(tempOp)==Operation.Error)
		{
			ans=false;
			return ans;
		}

		if (s.charAt(i)!='(')
		{
			ans=false;
			return ans;
		}

		while (i<s.length() && (s.charAt(i)!= ',') && (countOpen-countPsik)!=1)
		{
			if (s.charAt(i)==',')
				countPsik++;
			if (s.charAt(i)=='(')
				countOpen++;
			i++;
		}

		if (i==s.length())
		{
			ans=false;
			return ans;
		}
		if (s.charAt(s.length()-1)!= ')')
		{	
			ans=false;
			return ans;
		}
		return ans;
	}
	/**
	 * get a string and return which operation it is (from the enum), if the string it's not one of the legal operation she return the ERROR operation
	 * @param tempO the string she check which operation is
	 * @return the operation 
	 */
	private Operation checkingOp(String tempO) {
		tempO=tempO.toLowerCase();
		switch(tempO) {
		case "":
			return Operation.None;	
		case "none":
			return Operation.None;			
		case "plus":
			return Operation.Plus;
		case "min":
			return Operation.Min;
		case "max":
			return Operation.Max;
		case "divid":
			return Operation.Divid;
		case "div":
			return Operation.Divid;
		case "comp":
			return Operation.Comp;
		case "times":
			return Operation.Times;
		case "mul":
			return Operation.Times;
		default:
			return Operation.Error;
		}
	}

	/**
	 * update the left function of the this to function she get
	 * @param left the function she get
	 */
	public void setLeft(function left) {
		this.left = left;
	}
	/**
	 * update the right function of the this to function she get
	 * @param right the function she get
	 */
	public void setRight(function right) {
		this.right = right;
	}

	/**
	 *  update the operation of the this to the Operation she get
	 * @param op the operation she get
	 */
	public void setOp(Operation op) {
		this.op = op;
	}
	/**
	 * Calculate the value of the Complex_Function with the double number(x) she get.
	 * @param x the double value she get
	 * @return the value of the function with the x she get
	 */
	@Override
	public double f(double x) {
		double sum=0;
		Polynom p=new Polynom();
		if (this.right==null) // have just left side(and NONE)
		{
			p=new Polynom(this.left.toString());
			return p.f(x);
		}

		else // have left and right 
		{
			switch (this.getOp()) 
			{
			case Plus :
				return sum+( this.left.f(x) + this.right.f(x));
			case Times :
				return sum +(this.left.f(x) * this.right.f(x));
			case Comp :
				return  sum + (this.left.f(this.right.f(x)));
			case Divid :
				return sum + ( this.left.f(x) / this.right.f(x));
			case Min :
				if (this.left.f(x)<this.right.f(x))
					return sum +(this.left.f(x));
				else
					return sum +(this.right.f(x));
			case Max :
				if (this.left.f(x)>this.right.f(x))
					return sum +(this.left.f(x));
				else
					return sum+( this.right.f(x));
			case None :
				return sum +(this.left.f(x));
			case Error :
				System.out.println("you have ERROR operation");
				break;
			default:
				System.out.println("you have ilegal operation");
				break;
			}
		}
		return 0;
	}
	/**
	 *update the complexFunction:
	 * this update to be the left ,the op update to be plus and f1 update to be the right
	 * @param f1 the Function she get
	 */
	@Override
	public void plus(function f1) {

		if (this.right!=null)
		{
			this.left=this.copy();
			this.op=Operation.Plus;
			this.right=f1;	
		}
		else
		{
			this.op=Operation.Plus;
			this.right=f1;

		}

	}
	/**
	 *update the complexFunction:
	 * this update to be the left ,the op update to be times\mul and f1 update to be the right
	 * @param f1 the Function she get
	 */
	@Override
	public void mul(function f1) 
	{
		if (this.right==null)
		{
			this.op=Operation.Times;
			this.right=f1;
		}
		else
		{
			this.left=this.copy();
			this.op=Operation.Times;
			this.right=f1;

		}
	}

	/**
	 *update the complexFunction:
	 * this update to be the left ,the op update to be divid and f1 update to be the right
	 * @param f1 the Function she get
	 */
	@Override
	public void div(function f1)
	{
		if (this.right==null)
		{
			this.op=Operation.Divid;
			this.right=f1;
		}
		else
		{
			this.left=this.copy();
			this.op=Operation.Divid;
			this.right=f1;

		}

	}
	/** take the maximum between the complex_function of this and f1 
	 * 
	 * @param f1 the function which will be compared with this complex_function.
	 */
	@Override
	public void max(function f1) {
		if (this.right==null)
		{
			this.op=Operation.Max;
			this.right=f1;
		}
		else
		{
			this.left=this.copy();
			this.op=Operation.Max;
			this.right=f1;
		}

	}

	/** take the minimun between the complex_function of this and f1 
	 * 
	 * @param f1 the function which will be compared with this complex_function.
	 */
	@Override
	public void min(function f1) {
		if (this.right==null)
		{
			this.op=Operation.Min;
			this.right=f1;
		}
		else
		{
			this.left=this.copy();
			this.op=Operation.Min;
			this.right=f1;
		}

	}

	/**
	 * This method wrap this with the f1 function she get : this.f(f1(x))
	 * @param f1 the function she get
	 */
	@Override
	public void comp(function f1) {
		if (this.right==null)
		{
			this.op=Operation.Comp;
			this.right=f1;
		}
		else
		{
			this.left=this.copy();
			this.op=Operation.Comp;
			this.right=f1;
		}
	}

	/** returns the left side of the complex function 
	 * @return a function representing the left side of this complex function
	 */
	@Override
	public function left() {
		return this.left;
	}

	/** returns the right side of the complex function.
	 * @return a function representing the left side of this complex function
	 */
	@Override
	public function right() {
		if (this.right==null)
			return null;
		else
			return this.right;
	}

	/**
	 * returns the complex_function's oparation: plus, mul, div, max, min, comp
	 * @return the operation
	 */
	@Override
	public Operation getOp() {
		return this.op;
	}
	/**
	 * print this complex_function 
	 */
	@Override
	public String toString()
	{		
		String ans="";

		if(right == null) //its a polynom/monom
		{
			return this.left.toString();
		}
		else
		{
			ans+=this.op + "(" + this.left.toString() + "," + this.right.toString() + ")";
		}

		return ans;
	}
	/**
	 * create a new complex_function and copy this complex_function to the new complex_function 
	 */
	@Override
	public function copy() {
		function theNewF= new ComplexFunction(this.op.toString(), this.left, this.right);
		return theNewF;

	}
	/*
	 * this func take the object and check what kind of object it is, she get just the type of object : Polynom, Monom, ComplexFunction  
	 *first of all she check if this looke like the object she get (by to string) and if not she check if F(x) is equal between (-100,100) 
	 * @return if looks the same or its equals for the all range the func return true else it return false. 
	 */
	public boolean equals(Object obj) 
	{
		if (this.toString().equals(obj.toString()))
			return true;

		double fcf=0;
		double fobj=0;
		fcf=this.f(2);
		ComplexFunction cf = (ComplexFunction)obj;
		for (int i=-10; i<10; i++) 
		{ 
			fcf=this.f(i);
			fobj=cf.f(i);
			if (Math.abs(fcf- fobj )> Monom.EPSILON) 
			{
				return false; 
			}
		}
		return true;
	}
}

