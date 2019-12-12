package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Ex1.Functions_GUI;
import Ex1.Range;

class GUI_testing {
	

	@Test
	void test() throws IOException {

		Functions_GUI FuncG = new Functions_GUI();

		FuncG.initFromFile("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read\\guiExample.txt");
		FuncG.saveToFile("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read\\newGui.txt");
		Range x = new Range(-20,20);
		Range y = new Range(-20,20);
		FuncG.drawFunctions(1000,1000,x,y,200);
		FuncG.drawFunctions("C:\\Users\\meita\\eclipse-workspace\\Ex1\\text_for_read.json");
	
		//fail("Not yet implemented");
	}

}
