package com.situ.recurrence;

import org.junit.Test;

public class Recurrence {
	public String stingReverse(String string) {
		if(string.length()>1)
			return stingReverse(string.substring(1))+string.charAt(0);
		return string;
	}
	
	@Test
	public void name() {
		System.out.println(stingReverse("adasdasd"));
	}
}
