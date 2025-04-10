package sequenzers;

public class AppUserSequencer {

    private static int currentId;

    public static int nextId(){
        return ++currentId;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        sequenzers.AppUserSequencer.currentId = currentId;
    }
}
