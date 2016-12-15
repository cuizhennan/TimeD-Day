package mkcoding.services.threads.puzzle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mx on 16/12/1.
 */
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    public Node(M move, P pos, Node<P, M> prev) {
        this.move = move;
        this.pos = pos;
        this.prev = prev;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) {
            solution.add(0, n.move);
        }

        return solution;
    }
}
