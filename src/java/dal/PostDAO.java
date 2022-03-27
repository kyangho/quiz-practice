/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                + "FROM `post`\n"
                + "ORDER BY post_time_created DESC;";
        Connection connection = getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement(sqlGetPostInfo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post post = getPostWithId(rs.getInt("post_id"));
                resPosts.add(post);
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPosts;
    }

    public ArrayList<Post> getPostsList(String title, String category, String author, 
            String status, Boolean isFeature, int pageSize, int pageIndex) {
        ArrayList<Post> resPosts = new ArrayList<>();
        Connection connection = getConnection();

        String sql
                = "select * from\n"
                + "(select row_number() over (order by post_time_modified DESC ) as stt,\n"
                + "    p.post_id,\n"
                + "p.post_title,\n"
                + "p.post_author,\n"
                + "p.post_time_modified,\n"
                + "p.post_status,\n"
                + "p.post_isFeaturing,\n"
                + "group_concat(c.setting_name) as \"setting_name\" \n"
                + "FROM post as p\n"
                + "LEFT JOIN post_category AS pc ON p.post_id = pc.post_id\n"
                + "LEFT JOIN setting AS c ON pc.setting_id = c.setting_id\n";
        title = "post_title LIKE ('%" + title + "%')";
        category = "setting_name LIKE ('%" + category + "%')";
        author = "post_author LIKE ('%" + author + "%')";
        status = "post_status LIKE ('%" + status + "%')";
        String featureSQL = "";
        if (isFeature == null) {
            featureSQL = "";
        } else if (isFeature == true) {
            featureSQL = "1";
        } else if (isFeature == false) {
            featureSQL = "0";
        }
        if (!title.equals("") || !category.equals("") || !author.equals("") || !status.equals("")) {
            sql += "WHERE setting_type = 'Post Category' AND ";
            sql += title + " AND ";
            sql += category + " AND ";
            sql += author + " AND ";
            if (isFeature != null) {
                sql += "post_isFeaturing = " + featureSQL + " AND ";
            }
            sql += status;
        }
        sql += "\ngroup by p.post_id";

        sql += "        ) as t\n"
                + " where  t.stt >= (? - 1) * ? + 1 AND t.stt <= ? * ?;";
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPosts;
    }

    public ArrayList<Post> getPostsListSortByFeature(String title, String category, String author, 
            String status, Boolean isFeature, int pageSize, int pageIndex) {
        ArrayList<Post> resPosts = new ArrayList<>();
        Connection connection = getConnection();

        String sql
                = "select * from\n"
                + "	(select row_number() over (order by post_time_modified DESC ) as stt,\n"
                + "    p.post_id,\n"
                + "p.post_title,\n"
                + "p.post_author,\n"
                + "p.post_time_modified,\n"
                + "p.post_status,\n"
                + "p.post_isFeaturing,\n"
                + "group_concat(c.setting_name) as \"setting_name\" \n"
                + "FROM post as p\n"
                + "LEFT JOIN post_category AS pc ON p.post_id = pc.post_id\n"
                + "LEFT JOIN setting AS c ON pc.setting_id = c.setting_id\n";
        title = "post_title LIKE ('%" + title + "%')";
        category = "setting_name LIKE ('%" + category + "%')";
        author = "post_author LIKE ('%" + author + "%')";
        status = "post_status LIKE ('%" + status + "%')";
        String featureSQL = "";
        if (isFeature == null) {
            featureSQL = "";
        } else if (isFeature == true) {
            featureSQL = "1";
        } else if (isFeature == false) {
            featureSQL = "0";
        }
        if (!title.equals("") || !category.equals("") || !author.equals("") || !status.equals("")) {
            sql += "WHERE setting_type = 'Post Category' AND ";
            sql += title + " AND ";
            sql += category + " AND ";
            sql += author + " AND ";
            sql += "post_isFeaturing LIKE ('%" + featureSQL + "%') AND ";
            sql += status;
        }
        sql += "group by p.post_id";

        sql += "\nORDER BY post_isFeaturing DESC) as t\n"
                + " where  t.stt >= (? - 1) * ? + 1 AND t.stt <= ? * ?;";
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPosts;
    }

    public Post getPostWithId(int id) {
        Connection connection = getConnection();

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
                resPost.setDateCreated(new Date(rs.getTimestamp("post_time_created").getTime()));
                resPost.setDateModified(new Date(rs.getTimestamp("post_time_modified").getTime()));
                resPost.setIsFeature(rs.getBoolean("post_isFeaturing"));
                resPost.setStatus(rs.getString("post_status"));
                resPost.setAuthor(rs.getString("post_author"));
                resPost.setBrief(rs.getString("post_brief"));

                resPost.setPostFiles(getPostFileListWithId(resPost.getId()));
            }
            stm.close();
            resPost.setCategories(getPostCategories(resPost.getId() + "", ""));
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPost;
    }

    public ArrayList<PostFile> getPostFileListWithId(int postId) {
        Connection connection = getConnection();

        ArrayList<PostFile> resPostFileList = new ArrayList<>();
        String sql = "SELECT `post_has_file`.`post_id`,\n"
                + "    `post_has_file`.`file_id`\n"
                + "FROM `post_has_file`\n"
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPostFileList;
    }

    public PostFile getPostFileWithId(int postFileId) {
        Connection connection = getConnection();

        PostFile resPostFile = new PostFile();

        String sql = "SELECT `post_file`.`file_id`,\n"
                + "    `post_file`.`file_name`,\n"
                + "    `post_file`.`file_type`,\n"
                + "    `post_file`.`file_blob`\n"
                + "FROM `post_file`\n"
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPostFile;
    }

    public ArrayList<PostCategory> getPostCategories(String postId, String categoryName) {
        Connection connection = getConnection();

        ArrayList<PostCategory> categories = new ArrayList<>();

        String sql = "select pc.post_id,\n"
                + "	s.setting_id,\n"
                + "    s.setting_name,\n"
                + "    s.setting_value,\n"
                + "    s.setting_type\n"
                + "from post_category as pc\n"
                + "left join setting  as s on pc.setting_id = s.setting_id\n";
        if (postId == null || postId.isEmpty()) {
            sql = "select \n"
                    + "	s.setting_id,\n"
                    + "    s.setting_name,\n"
                    + "    s.setting_value,\n"
                    + "    s.setting_type\n"
                    + "from setting as s\n"
                    + "where setting_type LIKE ('Post Category')";
        }

        if (!postId.isEmpty() && categoryName.isEmpty()) {
            postId = "('%" + postId + "%')";
            sql += "WHERE post_id LIKE " + postId;
        } else if (!categoryName.isEmpty()) {
            categoryName = "('%" + categoryName + "%')";
            sql += "WHERE setting_name LIKE " + categoryName;
        }

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PostCategory pc = new PostCategory();
                pc.setId(rs.getInt("setting_id"));
                pc.setName(rs.getString("setting_name"));
                pc.setValue(rs.getString("setting_value"));

                categories.add(pc);
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categories;
    }

    /**
     * Sort with input is asc for ascending and desc for descending
     *
     * @param title
     * @param category
     * @param author
     * @param feature
     * @param status
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public ArrayList<Post> getPostsListSortBy(String title, String category, String author, String feature, String status, int pageSize, int pageIndex) {
        ArrayList<Post> resPosts = new ArrayList<>();
        Connection connection = getConnection();

        String sql
                = "select * from\n"
                + "	(select row_number() over ( ";
        if (title.compareTo("") != 0) {
            sql += "order by post_title " + title;
        }
        if (category.compareTo("") != 0) {
            sql += "order by setting_name " + category;
        }
        if (author.compareTo("") != 0) {
            sql += "order by post_author " + author;
        }
        if (feature.compareTo("") != 0) {
            sql += "order by post_isFeaturing " + feature;
        }
        if (status.compareTo("") != 0) {
            sql += "order by post_status " + status;
        }
        sql += " ) as stt,\n"
                + "    p.post_id,\n"
                + "p.post_title,\n"
                + "p.post_author,\n"
                + "p.post_time_created,\n"
                + "p.post_status,\n"
                + "p.post_isFeaturing,\n"
                + "group_concat(c.setting_name) as \"setting_name\" \n"
                + "FROM post as p\n"
                + "LEFT JOIN post_category AS pc ON p.post_id = pc.post_id\n"
                + "LEFT JOIN setting AS c ON pc.setting_id = c.setting_id AND c.setting_type = 'Post Category'\n";
        sql += "group by p.post_id\n";
        sql += "        ) as t\n"
                + " where  t.stt >= (? - 1) * ? + 1 AND t.stt <= ? * ?\n";

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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resPosts;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Insert post SQL. Click on the + sign on the left to edit the code.">
//======================== INSERT SQL ==============================
    public String insertPost(Post inputPost, InputStream isThumbnail) {
        Connection connection = getConnection();

        String sql = "INSERT INTO `post`\n"
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
            stm.setTimestamp(5, new Timestamp(inputPost.getDateCreated().getTime()));
            stm.setTimestamp(6, new Timestamp(inputPost.getDateModified().getTime()));
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
            connection.commit();
            insertPostCategory(Integer.parseInt(id), inputPost.getCategories());
            connection.setAutoCommit(true);
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public String insertPost(Post inputPost) {
        Connection connection = getConnection();

        String sql = "INSERT INTO `post`\n"
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
            stm.setTimestamp(4, new Timestamp(inputPost.getDateCreated().getTime()));
            stm.setTimestamp(5, new Timestamp(inputPost.getDateModified().getTime()));
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
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public boolean insertPostCategory(int postId, ArrayList<PostCategory> categories) {
        Connection connection = getConnection();

        String sql = "INSERT INTO `post_category`\n"
                + "(`post_id`,\n"
                + "`setting_id`)\n"
                + "VALUES\n"
                + "(?,?);";
        try {
            connection.setAutoCommit(false);
            for (PostCategory pc : categories) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.setInt(2, pc.getId());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public boolean insertPostFile(int postId, String name, String type, InputStream isFile) {
        Connection connection = getConnection();

        String sql = "INSERT INTO `post_file`\n"
                + "(`file_name`,\n"
                + "`file_type`,\n"
                + "`file_blob`)\n"
                + "VALUES\n"
                + "(?,?,?);\n";
        String sqlInsertPostPostFile = "INSERT INTO `post_has_file`\n"
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
            stm.close();
            stm2.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update post SQL. Click on the + sign on the left to edit the code.">
    public String updatetPost(Post inputPost, InputStream isThumbnail) {
        Connection connection = getConnection();

        String sql = "UPDATE `post`\n"
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
            stm.setTimestamp(5, new Timestamp(inputPost.getDateCreated().getTime()));
            stm.setTimestamp(6, new Timestamp(inputPost.getDateModified().getTime()));
            stm.setBoolean(7, false);
            stm.setString(8, inputPost.getStatus());
            stm.setString(9, inputPost.getBrief());
            stm.setInt(10, inputPost.getId());
            stm.executeUpdate();

            connection.setAutoCommit(true);
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public String updatetPost(Post inputPost) {
        Connection connection = getConnection();

        String sql = "UPDATE `post`\n"
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
            stm.setTimestamp(4, new Timestamp(inputPost.getDateCreated().getTime()));
            stm.setTimestamp(5, new Timestamp(inputPost.getDateModified().getTime()));
            stm.setBoolean(6, false);
            stm.setString(7, inputPost.getStatus());
            stm.setString(8, inputPost.getBrief());
            stm.setInt(9, inputPost.getId());
            stm.executeUpdate();
            connection.setAutoCommit(true);
            updatePostCategory(inputPost.getId(), inputPost.getCategories());
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public boolean updatePostCategory(int postId, ArrayList<PostCategory> categories) {
        deletePostPostCategory(postId);

        insertPostCategory(postId, categories);
        return true;
    }

    public boolean updatePostThumbnail(int postId, InputStream is) {
        Connection connection = getConnection();

        String sql = "UPDATE `post`\n"
                + "SET\n"
                + "`post_thumbnail` = ?\n"
                + "WHERE post_id = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBinaryStream(1, is);
            stm.setInt(2, postId);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    public boolean updatePostFile(int postId, String fileName, String fileType, InputStream fileContent) {
        Connection connection = getConnection();

        String sql = "UPDATE `post_has_file` as phf\n"
                + "LEFT JOIN post_file as pf on pf.file_id = phf.file_id\n"
                + "SET\n"
                + "pf.file_name = ?,\n"
                + "pf.file_type = ?,\n"
                + "pf.file_blob = ?\n"
                + "WHERE `post_id` = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fileName);
            stm.setString(2, fileType);
            stm.setBinaryStream(3, fileContent);
            stm.setInt(4, postId);

            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public boolean updateStatus(int postId, String status) {
        Connection connection = getConnection();

        String sql = "UPDATE `post`\n"
                + "SET\n"
                + "post_status = ?,\n"
                + "post_time_modified = ?\n"
                + "WHERE post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stm.setInt(3, postId);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public boolean updateFeature(int postId, boolean isFeature) {
        Connection connection = getConnection();

        String sql = "UPDATE `post`\n"
                + "SET\n"
                + "post_isFeaturing = ?,\n"
                + "post_time_modified = ?\n"
                + "WHERE post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isFeature);
            stm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stm.setInt(3, postId);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
//</editor-fold>

    public boolean deletePostPostCategory(int postId) {
        Connection connection = getConnection();
        String sql = "DELETE FROM `post_category`\n"
                + "WHERE post_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public int countTotalPost() {
        Connection connection = getConnection();

        String sql = "SELECT count(post_id) as total FROM quiz_practice_db.post;";
        int count = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

    public int countTotalPostWithCondition(String title, String category, String author, 
            String status, int pageSize, int pageIndex) {
        Connection connection = getConnection();

        String sql
                = "select *, max(stt) as postTotal from\n"
                + "	(select row_number() over (order by post_time_created DESC ) as stt,\n"
                + "    p.post_id,\n"
                + "p.post_title,\n"
                + "p.post_author,\n"
                + "p.post_time_created,\n"
                + "p.post_status,\n"
                + "group_concat(s.setting_name) as \"setting_name\" \n"
                + "FROM post as p\n"
                + "LEFT JOIN post_category AS pc ON p.post_id = pc.post_id\n"
                + "LEFT JOIN setting AS s ON pc.setting_id = s.setting_id\n";
        title = "post_title LIKE ('%" + title + "%')";
        category = "setting_name LIKE ('%" + category + "%')";
        author = "post_author LIKE ('%" + author + "%')";
        status = "post_status LIKE ('%" + status + "%')";
        if (!title.equals("") || !category.equals("") || !author.equals("") || !status.equals("")) {
            sql += "WHERE ";
            sql += title + " AND ";
            sql += category + " AND ";
            sql += author + " AND ";
            sql += status;
        }
        sql += "group by p.post_id";

        sql += "        ) as t\n";

        int count = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            int lastId = -1;
            while (rs.next()) {
                count = rs.getInt("postTotal");
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

}
