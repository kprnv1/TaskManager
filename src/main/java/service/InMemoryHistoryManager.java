package service;

import model.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> history = new LinkedList<>();
    private final int COUNT_ELEMENTS_LIST_HISTORY = 9;

    @Override
    public void addInHistory(Task task) {
        int index = 0;
        if (history.size() > COUNT_ELEMENTS_LIST_HISTORY) {
            history.remove(index);
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
