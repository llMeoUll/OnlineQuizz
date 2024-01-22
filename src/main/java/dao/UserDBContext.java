package dao;

import com.lambdaworks.crypto.SCryptUtil;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import controller.user.authenticate.Register;
import entity.*;
import org.eclipse.tags.shaded.org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

public class UserDBContext extends DBContext<User>{
    @Override
    public User get(User entity) {
        try {
            String sql = "SELECT `user`.`uid`, `user`.`email`, `user`.`username`, `user`.`password`  \n" +
                    "FROM `user`\n" +
                    "where `email` = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getEmail());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(Integer.parseInt(rs.getString("uid")));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                if (SCryptUtil.check(entity.getPassword(), user.getPassword())) {
                    return user;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return null;

    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetListUser = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`;";
        try {
            PreparedStatement stmGetListUser = connection.prepareStatement(sqlGetListUser);
            ResultSet rs = stmGetListUser.executeQuery();
            while(rs.next()) {
                User user = new User();
                int id = Integer.parseInt(rs.getString("uid"));
                String email = rs.getString("email");
                String username = rs.getString("username");
                String given_name = rs.getString("given_name");
                String family_name = rs.getString("family_name");
                String avatar = rs.getString("avartar");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");
                user.setId(id);
                user.setEmail(email);
                user.setUsername(username);
                user.setGiven_name(given_name);
                user.setFamily_name(family_name);
                user.setPicture(avatar);
                user.setCreated_at(created_at);
                user.setUpdated_at(updated_at);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void insert(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`user`\n" +
                    "(\n" +
                    "`email`,\n" +
                    "`username`,\n" +
                    "`password`,\n" +
                    "`given_name`,\n" +
                    "`family_name`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stmInsert = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmInsert.setString(1, entity.getEmail());
            stmInsert.setString(2, entity.getUsername());
            stmInsert.setString(3, entity.getPassword());
            stmInsert.setString(4, entity.getGiven_name());
            stmInsert.setString(5, entity.getFamily_name());
            stmInsert.executeUpdate();

            try (ResultSet generatedKeys = stmInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, 2);
                    stmInsertRole.setInt(2, userId);
                    stmInsertRole.executeUpdate();
                }
            }
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void delete(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlDeleteUser = "DELETE FROM `online_quizz`.`user`\n" +
                    "WHERE `user`.`uid` = ?;";
            PreparedStatement stmDeleteUser = connection.prepareStatement(sqlDeleteUser);
            stmDeleteUser.setInt(1, entity.getId());
            stmDeleteUser.executeUpdate();

            String sqlDeleteDoneTest = "DELETE FROM `online_quizz`.`user_does_test`\n" +
                    "WHERE `user_does_test`.`uid` = ?;";
            PreparedStatement stmDeleteDoneTest = connection.prepareStatement(sqlDeleteDoneTest);
            stmDeleteDoneTest.setInt(1, entity.getId());
            stmDeleteDoneTest.executeUpdate();

            for(Test doneTest : entity.getDoneTest()) {
                String sqlDeleteUserAnswer = "DELETE FROM online_quizz.user_answer\n" +
                        "WHERE `user_answer`.`udt_id` IN (\n" +
                        "\tSELECT DISTINCT `user_does_test`.`udt_id` FROM `user_does_test`\n" +
                        "    WHERE `user_does_test`.`test_id` = ?\n" +
                        ")";
                PreparedStatement stmDeleteUserAnswer = connection.prepareStatement(sqlDeleteUserAnswer);
                stmDeleteUserAnswer.setInt(1, doneTest.getTest_id());
                stmDeleteUserAnswer.executeUpdate();
            }

            String sqlDeleteComments = "DELETE FROM `online_quizz`.`comment`\n" +
                    "WHERE `comment`.`uid` = ?;";
            PreparedStatement stmDeleteComments = connection.prepareStatement(sqlDeleteComments);
            stmDeleteComments.setInt(1, entity.getId());
            stmDeleteComments.executeUpdate();

            String sqlDeleteReplyComments = "DELETE FROM `online_quizz`.`comment`\n" +
                    "WHERE `comment`.`comment_id` IN (\n" +
                    "\tSELECT DISTINCT `comment`.`reply_id` FROM `online_quizz`.`comment`\n" +
                    "    WHERE `comment`.`uid` = ?\n" +
                    ");";
            PreparedStatement stmDeleteReplyComment = connection.prepareStatement(sqlDeleteReplyComments);
            stmDeleteReplyComment.setInt(1, entity.getId());
            stmDeleteReplyComment.executeUpdate();

            String sqlDeleteStarRate = "DELETE FROM `online_quizz`.`star_rate`\n" +
                    "WHERE `star_rate`.`uid` = ?;";
            PreparedStatement stmDeleteStarRate = connection.prepareStatement(sqlDeleteStarRate);
            stmDeleteStarRate.setInt(1, entity.getId());
            stmDeleteStarRate.executeUpdate();

            for (Set set : entity.getOwnedSets()) {
                String sqlDeleteSets = "";
                PreparedStatement stmDeleteSets = connection.prepareStatement(sqlDeleteSets);
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Comment> getComments(User entity) {
        ArrayList<Comment> comments = new ArrayList<>();
        String sqlGetComments = "SELECT `comment`.`comment_id`,\n" +
                "    `comment`.`sid`,\n" +
                "    `comment`.`uid`,\n" +
                "    `comment`.`content`,\n" +
                "    `comment`.`likes`,\n" +
                "    `comment`.`unlikes`,\n" +
                "    `comment`.`created_at`,\n" +
                "    `comment`.`updated_at`,\n" +
                "    `comment`.`reply_id`\n" +
                "FROM `online_quizz`.`comment`\n" +
                "WHERE `comment`.`uid` = ?;";
        try {
            PreparedStatement stmGetComments = connection.prepareStatement(sqlGetComments);
            stmGetComments.setInt(1, entity.getId());
            ResultSet rs = stmGetComments.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setComment_id(rs.getInt("comment_id"));
                Set set = new Set();
                set.setSid(rs.getInt("sid"));
                comment.setSet(set);
                comment.setUser(entity);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("unlikes"));
                ArrayList<Comment> replyComments = new ArrayList<>();
                Comment replyComment = new Comment();
                replyComment.setComment_id(rs.getInt("reply_id"));
                replyComments.add(replyComment);
                comment.setRepliedComments(replyComments);
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
    public ArrayList<StarRate> getRatedStars(User entity) {
        ArrayList<StarRate> ratedStars = new ArrayList<>();
        String sqlGetRatedStar = "SELECT `star_rate`.`uid`,\n" +
                "    `star_rate`.`sid`,\n" +
                "    `star_rate`.`rate`,\n" +
                "    `star_rate`.`created_at`,\n" +
                "    `star_rate`.`updated_at`\n" +
                "FROM `online_quizz`.`star_rate`\n" +
                "WHERE `star_rate`.`uid` = ?";
        try {
            PreparedStatement stmGetRatedStar = connection.prepareStatement(sqlGetRatedStar);
            stmGetRatedStar.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetRatedStar.executeQuery();
            while(rs.next()) {
                StarRate starRate = new StarRate();
                starRate.setUser(entity);
                starRate.setRate(Integer.parseInt(rs.getString("rate")));
                Set set = new Set();
                set.setSid(rs.getInt("sid"));
                starRate.setSet(set);
                ratedStars.add(starRate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratedStars;
    }

    public ArrayList<Test> getDoneTest(User entity) {
        ArrayList<Test> doneTests = new ArrayList<>();
        String sqlGetDoneTests = "SELECT `user_does_test`.`test_id`\n" +
                "FROM `online_quizz`.`user_does_test`\n" +
                "WHERE `user_does_test`.`uid` = ?";
        try {
            PreparedStatement stmGetDoneTests = connection.prepareStatement(sqlGetDoneTests);
            stmGetDoneTests.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetDoneTests.executeQuery();
            while(rs.next()) {
                Test doneTest = new Test();
                doneTest.setTest_id(rs.getInt("test_id"));
                doneTests.add(doneTest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doneTests;
    }
    public ArrayList<Set> getOwnedSet(User entity) {
        ArrayList<Set> ownedSets = new ArrayList<>();
        String sqlGetOwnedSet = "SELECT `set`.`sid`,\n" +
                "    `set`.`sname`,\n" +
                "    `set`.`description`,\n" +
                "    `set`.`is_private`,\n" +
                "    `set`.`uid`,\n" +
                "    `set`.`created_at`,\n" +
                "    `set`.`updated_at`\n" +
                "FROM `online_quizz`.`set`\n" +
                "WHERE `set`.`uid` = ?";
        try {
            PreparedStatement stmGetOwnedSet = connection.prepareStatement(sqlGetOwnedSet);
            stmGetOwnedSet.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetOwnedSet.executeQuery();
            while(rs.next()) {
                Set ownedSet = new Set();
                ownedSet.setSid(rs.getInt("sid"));
                ownedSet.setSname(rs.getString("sname"));
                ownedSet.setDescription(rs.getString("description"));
                ownedSet.set_private(rs.getBoolean("is_private"));
                ownedSet.setUser(entity);
                ownedSets.add(ownedSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownedSets;
    }

    public ArrayList<SelfTest> getSelfTests(User entity) {
        ArrayList<SelfTest> selfTests  = new ArrayList<>();
        String sqlGetSelfTests = "SELECT `self-test`.`self-test_id`,\n" +
                "    `self-test`.`uid`,\n" +
                "    `self-test`.`num_of_ques`,\n" +
                "    `self-test`.`created_at`\n" +
                "FROM `online_quizz`.`self-test`\n" +
                "WHERE `self-test`.`uid` = ?";
        try {
            PreparedStatement stmGetSelfTests = connection.prepareStatement(sqlGetSelfTests);
            stmGetSelfTests.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetSelfTests.executeQuery();
            while(rs.next()) {
                SelfTest selfTest = new SelfTest();
                selfTest.setUser(entity);
                selfTest.setSelf_test_id(rs.getInt("self-test_id"));
                selfTest.setNumb_of_ques(rs.getInt("num_of_ques"));
                selfTests.add(selfTest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selfTests;
    }

    public ArrayList<Room> getOwnedRoom(User entity) {
        ArrayList<Room> ownedRooms = new ArrayList<>();
        String sqlGetOwnedRooms = "SELECT `room`.`room_id`,\n" +
                "    `room`.`room_name`,\n" +
                "    `room`.`code`,\n" +
                "    `room`.`password`,\n" +
                "    `room`.`description`,\n" +
                "    `room`.`created_at`,\n" +
                "    `room`.`updated_at`\n" +
                "FROM `online_quizz`.`room`\n" +
                "WHERE `room`.`uid` = ?";
        try {
            PreparedStatement stmGetOwnedRooms = connection.prepareStatement(sqlGetOwnedRooms);
            stmGetOwnedRooms.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetOwnedRooms.executeQuery();
            while(rs.next()) {
                Room ownedRoom = new Room();
                ownedRoom.setRoom_id(rs.getInt("room_id"));
                ownedRoom.setUser(entity);
                ownedRoom.setRoom_name(rs.getString("room_name"));
                ownedRoom.setDescription(rs.getString("description"));
                ownedRoom.setCode(rs.getString("code"));
                ownedRoom.setPassword(rs.getString("password"));
                ownedRooms.add(ownedRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownedRooms;
    }

    public ArrayList<Room> getJoinedRooms(User entity) {
        ArrayList<Room> joinedRooms = new ArrayList<>();
        String sqlGetJoinedRooms = "SELECT `user_join_room`.`room_id`\n" +
                "FROM `online_quizz`.`user_join_room`\n" +
                "WHERE `user_join_room`.`uid` = ? ";
        try {
            PreparedStatement stmGetJoinedRooms = connection.prepareStatement(sqlGetJoinedRooms);
            stmGetJoinedRooms.setInt(1, entity.getId());
            ResultSet rs = stmGetJoinedRooms.executeQuery();
            while (rs.next()) {
                Room joinedRoom = new Room();
                joinedRoom.setRoom_id(rs.getInt("room_id"));
                joinedRooms.add(joinedRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return joinedRooms;
    }

    public ArrayList<Role> getRoles(User entity) {
        ArrayList<Role> roles = new ArrayList<>();
        String sqlGetRoles = "SELECT `role_user_mapping`.`rid`\n" +
                "FROM `online_quizz`.`role_user_mapping`\n" +
                "WHERE `role_user_mapping`.`uid` = ? ";
        try {
            PreparedStatement stmGetRoles = connection.prepareStatement(sqlGetRoles);
            stmGetRoles.setInt(1, entity.getId());
            ResultSet rs = stmGetRoles.executeQuery();
            while(rs.next()) {
                Role role = new Role();
                role.setRid(rs.getInt("rid"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
    public ArrayList<Role> getRolesAndFeatures(String username) throws ClassNotFoundException {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "select r.rid, r.name as rname, f.fid, f.url from `user` u\n" +
                    "inner join `role_user_mapping` ru on u.uid = ru.uid\n" +
                    "inner join `role` r on r.rid = ru.rid\n" +
                    "inner join `role_feature_mapping` rf on rf.rid = r.rid\n" +
                    "inner join `feature` f on f.fid = rf.fid\n" +
                    "where u.username = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRid(rs.getInt("rid"));
                r.setName(rs.getString("rname"));

                Feature f = new Feature();
                f.setFid(rs.getInt("fid"));
                f.setUrl(rs.getString("url"));

                r.getFeatures().add(f);
                roles.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public boolean checkEmail(String email) {
        String sql = "SELECT email FROM user\n" +
                "where email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean checkUsername(String userName) {
        String sql = "SELECT username FROM user\n" +
                "where username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void insertGoogleUser(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`user`\n" +
                    "(\n" +
                    "`email`,\n" +
                    "`given_name`,\n" +
                    "`family_name`,\n" +
                    "`avartar`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getEmail());
            stm.setString(2, entity.getGiven_name());
            stm.setString(3, entity.getFamily_name());
            stm.setString(4, entity.getPicture());
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, 2);
                    stmInsertRole.setInt(2, userId);
                    stmInsertRole.executeUpdate();
                }
            }
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
