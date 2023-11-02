package Tp3;
import java.io.Serializable;

	public class Operation implements Serializable {
	 
	    int nb1;
	    int nb2;
	    String op;

	    public double getRes() {
	        return res;
	    }

	    public void setRes(double res) {
	        this.res = res;
	    }

	    double res =0;
	    public Operation(int nb1, int nb2, String op) {
	        this.nb1 = nb1;
	        this.nb2 = nb2;
	        this.op = op;
	    }
	    public int getNb1() {
	        return nb1;
	    }

	    public void setNb1(int nb1) {
	        this.nb1 = nb1;
	    }

	    public int getNb2() {
	        return nb2;
	    }

	    public void setNb2(int nb2) {
	        this.nb2 = nb2;
	    }

	    public String getOp() {
	        return op;
	    }

	    public void setOp(String op) {
	        this.op = op;
	    }
	}


