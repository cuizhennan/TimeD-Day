package mkcoding.services.timed.commons;

/**
 * Created by mx on 16/12/23.
 */
public class TimedExcepion extends RuntimeException {

    private long id;

    public TimedExcepion(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
