package com.open.cloud.base.java.polypeptide;

class  SubClass extends SuperClass{
		@Override
		public void eat() {
			//super.eat();
			System.out.println("sub.eat");
		}

		public void sleep(){
			System.out.println("sub.sleep");
		}
	}