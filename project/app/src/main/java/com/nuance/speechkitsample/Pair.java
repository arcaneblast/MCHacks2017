package com.nuance.speechkitsample;

public class Pair implements  Comparable<Pair>{

    private final Double left;
    private final String right;

    public Pair(Double left, String right) {
        this.left = left;
        this.right = right;
    }

    public Double getLeft() { return left; }
    public String getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

    @Override
    public int compareTo(Pair another) {
        double d =(this.left - another.left);

        if(d > 0 )
            return -1;
        if( d < 0)
            return 1;
        return 0;
    }
}