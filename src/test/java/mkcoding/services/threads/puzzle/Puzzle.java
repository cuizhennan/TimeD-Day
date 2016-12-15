package mkcoding.services.threads.puzzle;

import java.util.Set;

/**
 * Created by mx on 16/12/1.
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
