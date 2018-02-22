package cc.openhome;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import javax.naming.*;

public class FileService {
    private DataSource dataSource;
    
    public FileService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/demo");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public File getFile(File file) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT filename, bytes FROM t_files WHERE id=?")) {

            statement.setLong(1, file.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                file = new File();
                file.setFilename(result.getString(1));
                file.setBytes(result.getBytes(2));
            }
            return file;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public List<File> getFileList() {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT id, filename, savedTime FROM t_files")) {
            
            ResultSet result = statement.executeQuery();
            List<File> fileList = new ArrayList<>();
            while (result.next()) {
                File file = new File();
                file.setId(result.getLong(1));
                file.setFilename(result.getString(2));
                file.setSavedTime(result.getTimestamp(3).getTime());
                fileList.add(file);
            }
            return fileList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void save(File file) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO t_files(filename, savedTime, bytes) VALUES(?, ?, ?)")) {

            statement.setString(1, file.getFilename());
            statement.setTimestamp(2, new Timestamp(file.getSavedTime()));
            statement.setBytes(3, file.getBytes());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void delete(File file) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM t_files WHERE id=?")) {

            statement.setLong(1, file.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
}
