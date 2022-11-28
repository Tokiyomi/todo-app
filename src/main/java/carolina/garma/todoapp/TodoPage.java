package carolina.garma.todoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoPage<T> {
    /*
    TodoPage class that provides the elements that a single page view contains
    as well as the method to generate pages
    */
    private Integer pageNumber; // Requested page view
    private static Integer todosPerPage = 10; // Max todos per page view
    private Integer totalTodos; // Total number of todos
    private List<T> items; // List of todo items for requested page

    private Integer totalPages; // Total number of pages available

    public TodoPage(Integer pageNumber, Integer totalTodos, List<T> items) {
        this.pageNumber = pageNumber;
        this.todosPerPage = getTodosPerPage();
        this.totalTodos = totalTodos;
        this.items = items;
        this.totalPages = getTotalPages();
    }

    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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
        /*
        Method that generates a page view after receiving the list of all current todos
        It returns a list object that contains current page number, total todos found,
        total pages, todos per page and the current todo items list (max 10 per page)
        */
        int skipCount = (pageNumber - 1) * todosPerPage;

        List<Todo> todoPage = todos
                .stream()
                .skip(skipCount)
                .limit(todosPerPage)
                .collect(Collectors.toList());

        TodoPage<Todo> page = new TodoPage<>(pageNumber, todos.size(), todoPage);

        Integer totalPages = todos.size()/todosPerPage;
        if (totalPages==0) {
            totalPages = 1;
        } else if (todos.size()%todosPerPage>0) {
            totalPages += 1;
        }
        page.setTotalPages(totalPages);

        return page;
    }
}
