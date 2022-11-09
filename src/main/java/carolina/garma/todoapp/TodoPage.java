package carolina.garma.todoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoPage<T> {
    private Integer pageNumber;
    //private static final Integer TODOS_PER_PAGE = 2;
    private static Integer todosPerPage = 10;
    private Integer totalTodos;
    private List<T> items;

    public TodoPage(Integer pageNumber, Integer totalTodos, List<T> items) {
        this.pageNumber = pageNumber;
        this.todosPerPage = getTodosPerPage();
        this.totalTodos = totalTodos;
        this.items = items;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTodosPerPage() {
        return todosPerPage;
    }

    public void setTodosPerPage(Integer todosPerPage) {
        this.todosPerPage = todosPerPage;
    }

    public Integer getTotalTodos() {
        return totalTodos;
    }

    public void setTotalTodos(Integer totalTodos) {
        this.totalTodos = totalTodos;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public static TodoPage<Todo> getPage(List<Todo> todos, int pageNumber) {
        int skipCount = (pageNumber - 1) * todosPerPage;

        List<Todo> todoPage = todos
                .stream()
                .skip(skipCount)
                .limit(todosPerPage)
                .collect(Collectors.toList());

        TodoPage<Todo> page = new TodoPage<>(pageNumber, todos.size(), todoPage);

        return page;
    }
}
