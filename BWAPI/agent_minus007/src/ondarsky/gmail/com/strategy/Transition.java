package ondarsky.gmail.com.strategy;

import ondarsky.gmail.com.strategy.intf.Node;

public class Transition {

    private int pathValue = 0;

    private Step head;
    private Step tail;

    public Transition(int nodeID) {
    	this(NodeHolder.findNodeByID(nodeID));
    }

    public Transition(Node node) {
    	head = new Step(node);
    	tail = head;
    }

    public Transition extendPath(int nodeID) {
    	return extendPath(NodeHolder.findNodeByID(nodeID));
    }

    public Transition extendPath(Node node) {
        tail = tail.add(node);
        pathValue += node.getTraversalCost();
        return this;
    }

    public int getPathValue() {
        return pathValue;
    }

    public Step getPath() {
        return head;
    }

    public Step getHead() {
		return head;
	}

	public Step getTail() {
		return tail;
	}

	@Override
    public String toString() {
    	StringBuilder sb = new StringBuilder()
    			.append(getPathValue()).append(":");

    	sb.append(getHead() + ">" + getTail());
//    	Step p = getPath();
//    	do {
//    		Node next = p.getCurrent();
//    		sb.append(">").append(next.toString());
//    		p = p.getNext();
//    	} while (p != null);
    	return sb.toString();
    }

    /**
     * Inner class for capturing the flow of Nodes
     *
     */
    public class Step {
    	Node current;
    	Step next;

		public Step(Node curr) {
			current = curr;
		}

		public Step add(Node curr) {
			if (current.equals(curr)) {
				return this;
			}
			next = new Step(curr);
			return next;
		}

		public Node getCurrent() {
			return current;
		}

		public Step getNext() {
			return next;
		}

		@Override
		public String toString() {
			return current.toString();
		}
    }
}
