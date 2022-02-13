/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.post.Post;
import model.post.PostCategory;
import model.post.PostFile;

/**
 *
 * @author ducky
 */
public class PostDAO extends DBContext {
    // <editor-fold defaultstate="collapsed" desc="Select SQL. Click on the + sign on the left to edit the code.">

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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPosts;
    }

    public ArrayList<Post> getPostsList(String title, String category, String author, String status, int pageSize, int pageIndex) {
        ArrayList<Post> resPosts = new ArrayList<>();
        String sql = 
                "select * from\n"
                + "	(select row_number() over (order by post_time_created DESC ) as stt,\n"
                + "    p.post_id,\n"
                + "    p.post_title,\n"
                + "    p.post_author,\n"
                + "    p.post_time_created,\n"
                + "    p.post_status,\n"
                + "    c.category_name\n"
                + "FROM post as p\n"
                + "LEFT JOIN post_category AS pc ON p.post_id = pc.post_id\n"
                + "LEFT JOIN category AS c ON pc.category_id = c.category_id\n";
        title = "post_title LIKE ('%" + title + "%')";
        category = "category_name LIKE ('%" + category + "%')";
        author = "post_author LIKE ('%" + author + "%')";
        status = "post_status LIKE ('%" + status + "%')";
        if (!title.equals("") || !category.equals("") || !author.equals("") || !status.equals("")) {
            sql += "WHERE ";
            sql += title + " AND ";
            sql += category + " AND ";
            sql += author + " AND ";
            sql += status;
        }
        sql += "        ) as t\n"
        + " where  t.stt >= (? - 1) * ? AND t.stt <= ? * ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            int lastId = -1;
            while (rs.next()) {
                if (lastId == rs.getInt("post_id")) {
                    continue;
                }
                Post post = getPostWithId(rs.getInt("post_id"));
                lastId = rs.getInt("post_id");
                resPosts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPosts;
    }

    public Post getPostWithId(int id) {
        Post resPost = new Post();
        String sqlGetPostInfo = "SELECT \n"
                + "    p.post_id,\n"
                + "    p.post_thumbnail,\n"
                + "    p.post_title,\n"
                + "    p.post_content,\n"
                + "    p.post_author,\n"
                + "    p.post_time_created,\n"
                + "    p.post_time_modified,\n"
                + "    p.post_isFeaturing,\n"
                + "    p.post_status,\n"
                + "    p.post_brief\n"
                + "FROM post as p\n"
                + "WHERE p.post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlGetPostInfo);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            ArrayList<PostCategory> postCategories = new ArrayList<>();
            while (rs.next()) {
                resPost.setId(id);
                resPost.setThumbnail(rs.getBlob("post_thumbnail"));
                resPost.setTitle(rs.getString("post_title"));
                resPost.setContent(rs.getString("post_content"));
                resPost.setDateCreated(rs.getDate("post_time_created"));
                resPost.setDateModified(rs.getDate("post_time_modified"));
                resPost.setFeaturing(rs.getBoolean("post_isFeaturing"));
                resPost.setStatus(rs.getString("post_status"));
                resPost.setAuthor(rs.getString("post_author"));
                resPost.setBrief(rs.getString("post_brief"));

                resPost.setPostFiles(getPostFileListWithId(resPost.getId()));
            }
            resPost.setCategories(getPostCategories(resPost.getId() + "", ""));
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                PostFile tmpPostFile = getPostFileWithId(postFileId);
                resPostFileList.add(tmpPostFile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return resPostFile;
    }

    public ArrayList<PostCategory> getPostCategories(String postId, String categoryName) {
        ArrayList<PostCategory> categories = new ArrayList<>();

        String sql = "select pc.post_id,\n"
                + "	c.category_id,\n"
                + "    c.category_name,\n"
                + "    c.category_value\n"
                + "from post_category as pc\n"
                + "right join category  as c on pc.category_id = c.category_id\n";
        if (postId.isEmpty()) {
            sql = "select \n"
                    + "	c.category_id,\n"
                    + "    c.category_name,\n"
                    + "    c.category_value\n"
                    + "from category as c\n";
        }

        if (!postId.isEmpty() || !categoryName.isEmpty()) {
            postId = "('%" + postId + "%')";
            categoryName = "('%" + categoryName + "%')";
            sql += "WHERE post_id LIKE " + postId + " AND " + "category_name LIKE " + categoryName;
        } else if (!categoryName.isEmpty()) {
            categoryName = "('%" + categoryName + "%')";
            sql += "WHERE category_name LIKE " + categoryName;
        }

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PostCategory pc = new PostCategory();
                pc.setId(rs.getInt("category_id"));
                pc.setName(rs.getString("category_name"));
                pc.setValue(rs.getString("category_value"));

                categories.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }
    
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Insert post SQL. Click on the + sign on the left to edit the code.">
//======================== INSERT SQL ==============================
    public String insertPost(Post inputPost, InputStream isThumbnail) {
        String sql = "INSERT INTO `quiz_practice_db`.`post`\n"
                + "(`post_thumbnail`,\n"
                + "`post_title`,\n"
                + "`post_content`,\n"
                + "`post_author`,\n"
                + "`post_time_created`,\n"
                + "`post_time_modified`,\n"
                + "`post_isFeaturing`,\n"
                + "`post_status`,\n"
                + "`post_brief`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";
        String id = "";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBinaryStream(1, isThumbnail);
            stm.setString(2, inputPost.getTitle());
            stm.setString(3, inputPost.getContent());
            stm.setString(4, inputPost.getAuthor());
            stm.setDate(5, inputPost.getDateCreated());
            stm.setDate(6, inputPost.getDateModified());
            stm.setBoolean(7, false);
            stm.setString(8, inputPost.getStatus());
            stm.setString(9, inputPost.getBrief());
            stm.executeUpdate();
            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement idSTM = connection.prepareStatement(sqlGetLastId);
            ResultSet idRS = idSTM.executeQuery();
            if (idRS.next()) {
                id = idRS.getString("id");
            }

            insertPostCategory(Integer.parseInt(id), inputPost.getCategories());
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public String insertPost(Post inputPost) {
        String sql = "INSERT INTO `quiz_practice_db`.`post`\n"
                + "("
                + "`post_title`,\n"
                + "`post_content`,\n"
                + "`post_author`,\n"
                + "`post_time_created`,\n"
                + "`post_time_modified`,\n"
                + "`post_isFeaturing`,\n"
                + "`post_status`,\n"
                + "`post_brief`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        String id = "";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, inputPost.getTitle());
            stm.setString(2, inputPost.getContent());
            stm.setString(3, inputPost.getAuthor());
            stm.setDate(4, inputPost.getDateCreated());
            stm.setDate(5, inputPost.getDateModified());
            stm.setBoolean(6, false);
            stm.setString(7, inputPost.getStatus());
            stm.setString(8, inputPost.getBrief());
            stm.executeUpdate();
            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement idSTM = connection.prepareStatement(sqlGetLastId);
            ResultSet idRS = idSTM.executeQuery();
            if (idRS.next()) {
                id = idRS.getString("id");
            }

            insertPostCategory(Integer.parseInt(id), inputPost.getCategories());
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public boolean insertPostCategory(int postId, ArrayList<PostCategory> categories) {
        String sql = "INSERT INTO `quiz_practice_db`.`post_category`\n"
                + "(`post_id`,\n"
                + "`category_id`)\n"
                + "VALUES\n"
                + "(?,?);";
        try {
            for (PostCategory pc : categories) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.setInt(2, pc.getId());
                stm.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean insertPostFile(int postId, String name, String type, InputStream isFile) {
        String sql = "INSERT INTO `quiz_practice_db`.`post_file`\n"
                + "(`file_name`,\n"
                + "`file_type`,\n"
                + "`file_blob`)\n"
                + "VALUES\n"
                + "(?,?,?);\n";
        String sqlInsertPostPostFile = "INSERT INTO `quiz_practice_db`.`post_has_file`\n"
                + "(`post_id`,\n"
                + "`file_id`)\n"
                + "VALUES\n"
                + "(?,?);";
        String id = "";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, type);
            stm.setBinaryStream(3, isFile);
            stm.executeUpdate();

            String sqlGetLastId = "select LAST_INSERT_ID() as id";
            PreparedStatement idSTM = connection.prepareStatement(sqlGetLastId);
            ResultSet idRS = idSTM.executeQuery();
            if (idRS.next()) {
                id = idRS.getString("id");
            }

            PreparedStatement stm2 = connection.prepareStatement(sqlInsertPostPostFile);
            stm2.setInt(1, postId);
            stm2.setInt(2, Integer.parseInt(id));
            stm2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update post SQL. Click on the + sign on the left to edit the code.">
    public String updatetPost(Post inputPost, InputStream isThumbnail) {
        String sql = "UPDATE `quiz_practice_db`.`post`\n"
                + "SET\n"
                + "`post_thumbnail` = ?,\n"
                + "`post_title` = ?,\n"
                + "`post_content` = ?,\n"
                + "`post_author` = ?,\n"
                + "`post_time_created` = ?,\n"
                + "`post_time_modified` = ?,\n"
                + "`post_isFeaturing` = ?,\n"
                + "`post_status` = ?,\n"
                + "`post_brief` = ?\n"
                + "WHERE `post_id` = ?;";
        String id = "";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBinaryStream(1, isThumbnail);
            stm.setString(2, inputPost.getTitle());
            stm.setString(3, inputPost.getContent());
            stm.setString(4, inputPost.getAuthor());
            stm.setDate(5, inputPost.getDateCreated());
            stm.setDate(6, inputPost.getDateModified());
            stm.setBoolean(7, false);
            stm.setString(8, inputPost.getStatus());
            stm.setString(9, inputPost.getBrief());
            stm.setInt(10, inputPost.getId());
            stm.executeUpdate();

            updatePostCategory(inputPost.getId(), inputPost.getCategories());
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public String updatetPost(Post inputPost) {
        String sql = "UPDATE `quiz_practice_db`.`post`\n"
                + "SET\n"
                + "`post_title` = ?,\n"
                + "`post_content` = ?,\n"
                + "`post_author` = ?,\n"
                + "`post_time_created` = ?,\n"
                + "`post_time_modified` = ?,\n"
                + "`post_isFeaturing` = ?,\n"
                + "`post_status` = ?,\n"
                + "`post_brief` = ?\n"
                + "WHERE `post_id` = ?;";
        String id = "";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, inputPost.getTitle());
            stm.setString(2, inputPost.getContent());
            stm.setString(3, inputPost.getAuthor());
            stm.setDate(4, inputPost.getDateCreated());
            stm.setDate(5, inputPost.getDateModified());
            stm.setBoolean(6, false);
            stm.setString(7, inputPost.getStatus());
            stm.setString(8, inputPost.getBrief());
            stm.setInt(9, inputPost.getId());
            stm.executeUpdate();

            updatePostCategory(inputPost.getId(), inputPost.getCategories());
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public boolean updatePostCategory(int postId, ArrayList<PostCategory> categories) {
        deletePostPostCategory(postId);

        insertPostCategory(postId, categories);
        return true;
    }

    public boolean updatePostThumbnail(int postId, InputStream is) {
        String sql = "UPDATE `quiz_practice_db`.`post`\n"
                + "SET\n"
                + "`post_thumbnail` = ?\n"
                + "WHERE post_id = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBinaryStream(1, is);
            stm.setInt(2, postId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
//</editor-fold>

    public boolean deletePostPostCategory(int postId) {
        String sql = "DELETE FROM `quiz_practice_db`.`post_category`\n"
                + "WHERE post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
//    public static void main(String[] args) {
//        PostDAO pDAO = new PostDAO();
//        for (Post p : pDAO.getPostsList("m", "", "admin", "")){
//            System.out.println(p.getId());
//        }
//    }
}
