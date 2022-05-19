package Data;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class PlayerData
{
    Connection connection;
    Statement statement;
    //名字列表
    List<String> playersName;
    //分数列表
    List<Integer> playerScore;
    String currentname = "Unknown Player";
    String password = "";

    public PlayerData(String name, String password) {
        //连接数据库
        currentname = name;
        this.password = password;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data/player.db");//获取链接
            System.out.println("Opened database successfully");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        createTable();//创建表单存放数据
        getPlayerInformation(5);
    }

    //创建数据表
    boolean createTable() {
        try {
            String sql = "CREATE TABLE Players " + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name           TEXT    NOT NULL, " + " password       CHAR(20)     NOT NULL, "
                    + " score          INT)";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    //获取玩家数据
    void getPlayerInformation(int num) {
        playersName = new ArrayList<String>();
        playerScore = new ArrayList<Integer>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT name,score  FROM Players ORDER BY score DESC LIMIT 4");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("NAME") + resultSet.getInt("SCORE"));
                playersName.add(resultSet.getString("NAME"));
                playerScore.add(resultSet.getInt("SCORE"));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    boolean savedata(int score) {
        System.out.println(currentname + password);
        try {
            statement.executeQuery("INSERT INTO Players (name, password, score) VALUES ('" + this.currentname + "', '"
                    + this.password + "', '" + score + "')");
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean signin(String name, String password) {
        try {
            ResultSet resultSet = statement.executeQuery("select password from Players where name = '" + name + "'");
            if(resultSet.next()){
                if(!(""+resultSet.getString("password")).equals(password)){
                    System.out.println(resultSet.getString("password") + " " + password);
                    return false;
                }
            }
        } catch (SQLException e) {
            // TODO 修改下面的检测函数
            e.printStackTrace();
        }
        this.currentname = name;
        this.password = password;
        return true;
    }

    public List<String> getPlayersName()
    {
        return playersName;
    }
    public List<Integer> getPlayerScore()
    {
        return playerScore;
    }
}

class Player {
    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
    String name;
    int score;
}

