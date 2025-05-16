package DAO.impl;

import DAO.TodoItems;
import model.Person;
import model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemsImpl implements TodoItems {

    private Connection connection;

    public TodoItemsImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Todo create(Todo todo) {
        String sql = "INSERT INTO todo_item (title, description, deadLine, done, assignee_id) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todo.getDeadLine()));
            preparedStatement.setBoolean(4, todo.getDone());
            if (todo.getAssignee_id() == null) {
                preparedStatement.setNull(5, todo.getAssignee_id());
            }else {
                preparedStatement.setInt(5, todo.getAssignee_id());
            }

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedTodoId = resultSet.getInt(1);
                    System.out.println("generatedTodoId = " + generatedTodoId);
                    todo.setTodo_id(generatedTodoId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving Todo");
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public Collection<Todo> findAll() {
        Collection<Todo> todoCollection = new ArrayList<>();
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item";
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            todoCollection = returnCollection(rs);
        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Todo findById(int id) {
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item WHERE todo_id = ?";
        try (PreparedStatement findById = connection.prepareStatement(sql)){
            findById.setInt(1, id);
            ResultSet rs = findById.executeQuery();
            while (rs.next()) {
                Integer assignee_id = rs.getInt("assignee_id");
                if (rs.wasNull())
                    assignee_id = null;
                Todo todo = new Todo(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadLine").toLocalDate(),
                        rs.getBoolean("done"),
                        assignee_id
                );
                return todo;
            }
        } catch (SQLException e) {
            System.out.println("Error by finding by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean done) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item WHERE done = ?";
        try (PreparedStatement findByDoneStatus = connection.prepareStatement(sql)) {
            findByDoneStatus.setBoolean(1, done);
            ResultSet rs = findByDoneStatus.executeQuery();
            todoCollection = returnCollection(rs);
        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(Integer id) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item WHERE assignee_id = ?";
        try (PreparedStatement findByAssigneeId = connection.prepareStatement(sql)) {
            findByAssigneeId.setInt(1, id);
            ResultSet rs = findByAssigneeId.executeQuery();
            todoCollection = returnCollection(rs);
        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> todoCollection = new ArrayList<>();
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item WHERE assignee_id = ?";
        try (PreparedStatement findByAssigneeId = connection.prepareStatement(sql)) {
            findByAssigneeId.setInt(1, person.getPerson_id());
            ResultSet rs = findByAssigneeId.executeQuery();
            todoCollection = returnCollection(rs);
        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        Collection<Todo> todoCollection = new ArrayList<>();
        String sql = "SELECT todo_id, title, description, deadLine, done, assignee_id FROM todo_item WHERE assignee_id IS NULL";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            todoCollection = returnCollection(rs);
        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return todoCollection;
    }

    @Override
    public Todo update(Todo todo) {
        String sql = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?";
        try (PreparedStatement updateTodo = connection.prepareStatement(sql)) {
            updateTodo.setString(1, todo.getTitle());
            updateTodo.setString(2, todo.getDescription());
            updateTodo.setDate(3, Date.valueOf(todo.getDeadLine()));
            updateTodo.setBoolean(4, todo.getDone());
            if (todo.getAssignee_id() != null) {
                updateTodo.setInt(5, todo.getAssignee_id());
            }else {
                updateTodo.setNull(5, java.sql.Types.INTEGER);
            }
            updateTodo.setInt(6, todo.getTodo_id());
            int rowsAffected = updateTodo.executeUpdate();
            if (rowsAffected > 0) {
                return findById(todo.getTodo_id()); //Returns updated to-do
            }
        }catch (SQLException e) {
            System.out.println("Error by updating Todo");
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM todo WHERE todo_id = ?";
        try {
            PreparedStatement deleteTodo = connection.prepareStatement(sql);
            deleteTodo.setInt(1, id);
            int rowsAffected = deleteTodo.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error by deleting person");
            e.printStackTrace();
        }
        return false;
    }

    Collection<Todo> returnCollection(ResultSet rs) throws SQLException{
        Collection<Todo> todoCollection = new ArrayList<>();
        while (rs.next()) {
                Integer assignee_id = rs.getInt("assignee_id");
                if (rs.wasNull())
                    assignee_id = null;
                Todo todo = new Todo(
                        rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadLine").toLocalDate(),
                        rs.getBoolean("done"),
                        assignee_id
                );
                todoCollection.add(todo);
            }
        return todoCollection;
    }
}
