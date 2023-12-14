package Util;

import java.util.Comparator;

public class MapNode implements Comparable<MapNode> {

    private final String value;
    private MapNode left;
    private MapNode right;

    public MapNode(String value) {
        this.value = value;
    }

    public void setLeft(MapNode left) {
        this.left = left;
    }

    public void setRight(MapNode right) {
        this.right = right;
    }

    public MapNode getLeft() {
        return left;
    }

    public MapNode getRight() {
        return right;
    }

    public String getValue() {
        return value;
    }

    public boolean isStartingNode() {
        return value.endsWith("A");
    }

    public boolean isEndingNode(boolean strict) {
        if (strict) {
            return value.equals("ZZZ");
        }
        return value.endsWith("Z");
    }

    @Override
    public int compareTo(MapNode o) {
        // sort by node values (useful for finding AAA to start)
        return Comparator.comparing(MapNode::getValue).compare(this, o);
    }
}
