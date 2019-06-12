package com.cmdi.model;

public class Tuple2<T,S> {
	private T t;
	private S s;
	@Override
	public String toString() {
		return "Tuple2 [t=" + t + ", s=" + s + "]";
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public S getS() {
		return s;
	}
	public void setS(S s) {
		this.s = s;
	}
	public Tuple2(T t, S s) {
		super();
		this.t = t;
		this.s = s;
	}
	public Tuple2() {
		super();
		// TODO Auto-generated constructor stub
	}
}
