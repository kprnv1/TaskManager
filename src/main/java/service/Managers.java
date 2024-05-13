package service;

public class Managers {
    public static TaskManager getDefaultTaskManagers(){
        return new InMemoryTaskManager(new InMemoryHisoryManager());
    }

     public static HistoryManager getDefaultHistory(){
        return new InMemoryHisoryManager();
    }
}
