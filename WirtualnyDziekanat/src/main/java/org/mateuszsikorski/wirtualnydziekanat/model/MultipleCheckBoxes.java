package org.mateuszsikorski.wirtualnydziekanat.model;

import java.util.ArrayList;
import java.util.List;

public class MultipleCheckBoxes {

	private boolean field0 = false;
	private boolean field1 = false;
	private boolean field2 = false;
	private boolean field3 = false;
	private boolean field4 = false;
	private boolean field5 = false;
	private boolean field6 = false;
	private boolean field7 = false;
	private boolean field8 = false;
	private boolean field9 = false;
	private boolean field10 = false;
	private boolean field11 = false;
	private boolean field12 = false;
	private boolean field13 = false;
	private boolean field14 = false;
	private boolean field15 = false;
	private boolean field16 = false;
	private boolean field17 = false;
	private boolean field18 = false;
	private boolean field19 = false;
	
	// Okropnie rozwiazany problem z brakiem mozliwosci iterowania dwoch list
	// relacyjnie powiazanych ze soba i ktorych wskazniki sa potrzebne 
	// w tym samym momencie podczas renderowania tabeli w pliku 
	// /view/admin/student-group-update
	
	
	
	public List<Integer> processCheckBoxes() {
		
		List<Integer> tempList = new ArrayList<>();
		
		if(isField0())
			tempList.add(0);
		if(isField1())
			tempList.add(1);
		if(isField2())
			tempList.add(2);
		if(isField3())
			tempList.add(3);
		if(isField4())
			tempList.add(4);
		if(isField5())
			tempList.add(5);
		if(isField6())
			tempList.add(6);
		if(isField7())
			tempList.add(7);
		if(isField8())
			tempList.add(8);
		if(isField9())
			tempList.add(9);
		if(isField10())
			tempList.add(10);
		if(isField11())
			tempList.add(11);
		if(isField12())
			tempList.add(12);
		if(isField13())
			tempList.add(13);
		if(isField14())
			tempList.add(14);
		if(isField15())
			tempList.add(15);
		if(isField16())
			tempList.add(16);
		if(isField17())
			tempList.add(17);
		if(isField18())
			tempList.add(18);
		if(isField19())
			tempList.add(19);
	
		
		return tempList;
	}
	
	public MultipleCheckBoxes() {}
	
	public boolean isField0() {
		return field0;
	}
	public void setField0(boolean field0) {
		this.field0 = field0;
	}
	public boolean isField1() {
		return field1;
	}
	public void setField1(boolean field1) {
		this.field1 = field1;
	}
	public boolean isField2() {
		return field2;
	}
	public void setField2(boolean field2) {
		this.field2 = field2;
	}
	public boolean isField3() {
		return field3;
	}
	public void setField3(boolean field3) {
		this.field3 = field3;
	}
	public boolean isField4() {
		return field4;
	}
	public void setField4(boolean field4) {
		this.field4 = field4;
	}
	public boolean isField5() {
		return field5;
	}
	public void setField5(boolean field5) {
		this.field5 = field5;
	}
	public boolean isField6() {
		return field6;
	}
	public void setField6(boolean field6) {
		this.field6 = field6;
	}
	public boolean isField7() {
		return field7;
	}
	public void setField7(boolean field7) {
		this.field7 = field7;
	}
	public boolean isField8() {
		return field8;
	}
	public void setField8(boolean field8) {
		this.field8 = field8;
	}
	public boolean isField9() {
		return field9;
	}
	public void setField9(boolean field9) {
		this.field9 = field9;
	}
	public boolean isField10() {
		return field10;
	}
	public void setField10(boolean field10) {
		this.field10 = field10;
	}
	public boolean isField11() {
		return field11;
	}
	public void setField11(boolean field11) {
		this.field11 = field11;
	}
	public boolean isField12() {
		return field12;
	}
	public void setField12(boolean field12) {
		this.field12 = field12;
	}
	public boolean isField13() {
		return field13;
	}
	public void setField13(boolean field13) {
		this.field13 = field13;
	}
	public boolean isField14() {
		return field14;
	}
	public void setField14(boolean field14) {
		this.field14 = field14;
	}
	public boolean isField15() {
		return field15;
	}
	public void setField15(boolean field15) {
		this.field15 = field15;
	}
	public boolean isField16() {
		return field16;
	}
	public void setField16(boolean field16) {
		this.field16 = field16;
	}
	public boolean isField17() {
		return field17;
	}
	public void setField17(boolean field17) {
		this.field17 = field17;
	}
	public boolean isField18() {
		return field18;
	}
	public void setField18(boolean field18) {
		this.field18 = field18;
	}
	public boolean isField19() {
		return field19;
	}
	public void setField19(boolean field19) {
		this.field19 = field19;
	}
}
