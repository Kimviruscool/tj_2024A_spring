package example.day07.todo;

import java.sql.*;
import java.util.ArrayList;

public class todoDAO {
    private static todoDAO todoDAO = new todoDAO();
    // 전역 객체 생성
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private todoDAO(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "1234");
    }
    catch(Exception e){System.out.println(e);}
    }

    public static todoDAO getInstance(){
        return todoDAO;
    }

    //get
    public boolean toDoGet(String key){
        try{
        String sql = "insert into todolist (todo) values (?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1,key);
        int count = ps.executeUpdate();
        if(count == 1){return true;}
        }
        catch (Exception e){System.out.println(e);} return false;
    }

    //post
    public ArrayList<todoDto> toDoPost(){
        ArrayList<todoDto> list = new ArrayList<>();

        try{
        String sql = "select * from todolist";
        ps = conn.prepareStatement(sql);
        rs= ps.executeQuery();
        while (rs.next()){
            String todo = rs.getString("todo");
            todoDto todoDto = new todoDto();
            todoDto.setToDo(todo);
            list.add(todoDto);
        }
        }
        catch (Exception e){System.out.println(e);} return list;
    }

}
