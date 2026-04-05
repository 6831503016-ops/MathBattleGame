public class Leaderboard {
    private Node head;

    // add player to list
    public void add(Player p) {
        Node newNode = new Node(p);
        newNode.next = head;
        head = newNode;
    }

    // recursive display
    public void display() {
        displayRecursive(head);
    }

    private void displayRecursive(Node current) {
        if (current == null) return;

        System.out.println(current.data.name + " : " + current.data.getScore());
        displayRecursive(current.next);
    }
}