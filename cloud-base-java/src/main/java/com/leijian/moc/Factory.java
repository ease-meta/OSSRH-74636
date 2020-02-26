package com.leijian.moc;

import absfactory.IFactory;

public class Factory implements IFactory {

	@Override
	public SubClass1 createSubClass1() {
		return new SubClass1();
	}

	@Override
	public SubClass2 createSubClass2() {
		return new SubClass2();
	}
}
