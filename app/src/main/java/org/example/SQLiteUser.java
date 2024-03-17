import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class SQLiteUser {
    private String url;

    public SQLiteUser(String dbName) {
        this.url = "jdbc:sqlite:" + dbName;
    }

    // 连接到数据库
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    // 创建用户表
    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " name text NOT NULL,\n"
                + " age integer\n"
                + ");";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 插入新用户
    public void insertUser(String name, int age) {
        String sql = "INSERT INTO users(name, age) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 查询所有用户
    public void selectAllUsers() {
        String sql = "SELECT id, name, age FROM users";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}