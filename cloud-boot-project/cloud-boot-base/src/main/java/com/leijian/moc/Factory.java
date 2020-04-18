package com.leijian.moc;

import absfactory.IFactory;
import absfactory.IProduct1;
import absfactory.IProduct2;

public class Factory implements IFactory {

	@Override
	public SubClass1 createSubClass1() {
		return new SubClass1();
	}

	@Override
	public SubClass2 createSubClass2() {
		return new SubClass2();
	}

	@Override
	public IProduct1 createProduct1A() {
		return null;
	}

	@Override
	public IProduct1 createProduct1B() {
		return null;
	}

	@Override
	public IProduct2 createProduct2A() {
		return null;
	}

	@Override
	public IProduct2 createProduct2B() {
		return null;
	}
}
