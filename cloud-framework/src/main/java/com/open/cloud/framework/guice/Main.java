package com.open.cloud.framework.guice;

public class Main {
	public static void main(String[] args) {
		DogEgg dogEgg = InjectorFactory.getInjector().getInstance(DogEgg.class);
		dogEgg.work();
	}
}


