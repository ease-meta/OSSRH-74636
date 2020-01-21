package com.open.cloud.common.spi.java.guice;

public class Main {
	public static void main(String[] args) {
		DogEgg dogEgg = InjectorFactory.getInjector().getInstance(DogEgg.class);
		dogEgg.work();
	}
}


