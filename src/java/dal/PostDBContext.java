/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Post;
import model.PostFile;

/**
 *
 * @author ducky
 */
public class PostDBContext extends DBContext {

    public ArrayList<Post> getPostsList() {
        ArrayList<Post> resPosts = new ArrayList<>();
        String sqlGetPostInfo = "SELECT `post`.`post_id`,"
                + "`post`.`post_time_created`\n"
                + "FROM `quiz_practice_db`.`post`\n"
                + "ORDER BY post_time_created DESC;";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlGetPostInfo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post post = getPostWithId(rs.getInt("post_id"));
                resPosts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPosts;
    }

    public ArrayList<Post> getPostsList(String title, String category, String author, String status) {
        ArrayList<Post> resPosts = new ArrayList<>();
        String sql = "SELECT `post`.`post_id`,\n"
                + "    `post`.`post_title`,\n"
                + "    `post`.`post_category`,\n"
                + "    `post`.`post_author`,\n"
                + "    `post`.`post_time_created`,\n"
                + "    `post`.`post_status`\n"
                + "FROM `quiz_practice_db`.`post`\n";
        title = "post_title LIKE ('%" + title + "%')";
        category = "post_category LIKE ('%" + category + "%')";
        author = "post_author LIKE ('%" + author + "%')";
        status = "post_status LIKE ('%" + status + "%')";
        if (!title.equals("") || !category.equals("") || !author.equals("") || !status.equals("")) {
            sql += "WHERE ";
            sql += title + " AND ";
            sql += category + " AND ";
            sql += author + " AND ";
            sql += status;
        }
        sql += "\nORDER BY post_time_created DESC;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post post = getPostWithId(rs.getInt("post_id"));
                resPosts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPosts;
    }

    public Post getPostWithId(int id) {
        Post resPost = new Post();
        String sqlGetPostInfo = "SELECT `post`.`post_id`,\n"
                + "    `post`.`post_thumbnail`,\n"
                + "    `post`.`post_title`,\n"
                + "    `post`.`post_content`,\n"
                + "    `post`.`post_category`,\n"
                + "    `post`.`post_author`,\n"
                + "    `post`.`post_time_created`,\n"
                + "    `post`.`post_time_modified`,\n"
                + "    `post`.`post_isFeaturing`,\n"
                + "    `post`.`post_status`\n"
                + "FROM `quiz_practice_db`.`post`\n"
                + "WHERE post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlGetPostInfo);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                resPost.setId(id);
                resPost.setThumbnail(rs.getBlob("post_thumbnail"));
                resPost.setTitle(rs.getString("post_title"));
                resPost.setContent(rs.getString("post_content"));
                resPost.setCategory(rs.getString("post_category"));
                resPost.setDateCreated(rs.getDate("post_time_created"));
                resPost.setDateModified(rs.getDate("post_time_modified"));
                resPost.setFeaturing(rs.getBoolean("post_isFeaturing"));
                resPost.setStatus(rs.getString("post_status"));
                resPost.setAuthor(rs.getString("post_author"));

                resPost.setPostFiles(getPostFileListWithId(resPost.getId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPost;
    }

    public ArrayList<PostFile> getPostFileListWithId(int postId) {
        ArrayList<PostFile> resPostFileList = new ArrayList<>();
        String sql = "SELECT `post_has_file`.`post_id`,\n"
                + "    `post_has_file`.`file_id`\n"
                + "FROM `quiz_practice_db`.`post_has_file`\n"
                + "WHERE post_id = ?;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int postFileId = rs.getInt("file_id");
                PostFile tmpPostFile = getPostFileWithId(postId);
                resPostFileList.add(tmpPostFile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return resPostFileList;
    }

    public PostFile getPostFileWithId(int postFileId) {
        PostFile resPostFile = new PostFile();

        String sql = "SELECT `post_file`.`file_id`,\n"
                + "    `post_file`.`file_name`,\n"
                + "    `post_file`.`file_type`,\n"
                + "    `post_file`.`file_blob`\n"
                + "FROM `quiz_practice_db`.`post_file`\n"
                + "WHERE file_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postFileId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                resPostFile.setId(rs.getInt("file_id"));
                resPostFile.setName(rs.getString("file_name"));
                resPostFile.setType(rs.getString("file_type"));
                resPostFile.setFileBlob(rs.getBlob("file_blob"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return resPostFile;
    }
    
//    public static void main(String[] args) {
//        PostDAO pDAO = new PostDAO();
//        for (Post p : pDAO.getPostsList("m", "", "admin", "")){
//            System.out.println(p.getId());
//        }
//    }
}
