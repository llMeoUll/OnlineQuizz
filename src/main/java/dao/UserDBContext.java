package dao;

import entity.*;


import com.lambdaworks.crypto.SCryptUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext extends DBContext<User> {

    /**
     * Get User by Uid
     *
     * @param entity entity has only id to get user from database (this is entityDTO)
     * @return
     */
    public User getUserById(User entity) {
        User user = null;
        String query = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`password`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user` WHERE uid = ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, entity.getId());
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("uid"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setGivenName(resultSet.getString("given_name"));
                    user.setFamilyName(resultSet.getString("family_name"));
                    user.setPicture(resultSet.getString("avartar"));
                    user.setCreatedAt(resultSet.getTimestamp("created_at"));
                    user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Get full infor of user by email after logging
     *
     * @param entity entity has only id to get user from database (this is entityDTO)
     * @return
     */
    public User getUserByEmail(User entity) {
        User user = null;
        String query = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`password`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user` WHERE email = ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, entity.getEmail());
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("uid"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setGivenName(resultSet.getString("given_name"));
                    user.setFamilyName(resultSet.getString("family_name"));
                    user.setPicture(resultSet.getString("avartar"));
                    user.setCreatedAt(resultSet.getTimestamp("created_at"));
                    user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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

    public User getByUsernameAndPassword(User entity) {
        try {
            String sqlGetAdmin = "SELECT `user`.email, `user`.uid, `user`.username FROM online_quizz.user\n" +
                    "WHERE `user`.username = ? AND `user`.`password` = ?;";
            PreparedStatement stmGetAdmin = connection.prepareStatement(sqlGetAdmin);
            stmGetAdmin.setString(1, entity.getUsername());
            stmGetAdmin.setString(2, entity.getPassword());
            ResultSet rs = stmGetAdmin.executeQuery();
            while (rs.next()) {
                User user = new User();
                String username = rs.getString("username");
                int uid = Integer.parseInt(rs.getString("uid"));
                user.setUsername(username);
                user.setId(uid);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            while (rs.next()) {
                User user = new User();
                int id = Integer.parseInt(rs.getString("uid"));
                String email = rs.getString("email");
                String username = rs.getString("username");
                String given_name = rs.getString("given_name");
                String family_name = rs.getString("family_name");
                String avatar = rs.getString("avartar");
                Timestamp created_at = rs.getTimestamp("created_at");
                Timestamp updated_at = rs.getTimestamp("updated_at");
                user.setId(id);
                user.setEmail(email);
                user.setUsername(username);
                user.setGivenName(given_name);
                user.setFamilyName(family_name);
                user.setPicture(avatar);
                user.setCreatedAt(created_at);
                user.setUpdatedAt(updated_at);
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
            stmInsert.setString(4, entity.getGivenName());
            stmInsert.setString(5, entity.getFamilyName());
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
                    stmInsertRole.setInt(1, getRoleId("User"));
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

    public void insertGoogleUser(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`user`\n" +
                    "(\n" +
                    "`email`,\n" +
                    "`given_name`,\n" +
                    "`family_name`,\n" +
                    "`is_verify`,\n" +
                    "`avartar`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getEmail());
            stm.setString(2, entity.getGivenName());
            stm.setString(3, entity.getFamilyName());
            stm.setBoolean(4, entity.isVerified());
            stm.setString(5, entity.getPicture());
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
                    stmInsertRole.setInt(1, getRoleId("User"));
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
        try {
            String sqlUpdateUser = "UPDATE `online_quizz`.`user`\n" +
                    "SET\n" +
                    "`email` = ?,\n" +
                    "`username` = ?,\n" +
                    "`given_name` = ?,\n" +
                    "`family_name` = ?,\n" +
                    "`updated_at` = ?\n" +
                    "WHERE `uid` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setString(1, entity.getEmail());
            stmUpdateUser.setString(2, entity.getUsername());
            stmUpdateUser.setString(3, entity.getGivenName());
            stmUpdateUser.setString(4, entity.getFamilyName());
            stmUpdateUser.setTimestamp(5, entity.getUpdatedAt());
            stmUpdateUser.setInt(6, entity.getId());
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User entity) {

        String sqlDeleteUser = "DELETE FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`uid` = ?;";
        PreparedStatement stmDeleteUser = null;
        try {
            stmDeleteUser = connection.prepareStatement(sqlDeleteUser);
            stmDeleteUser.setInt(1, entity.getId());
            stmDeleteUser.executeUpdate();
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
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
                comment.setSet(set);
                comment.setUser(entity);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("unlikes"));
                ArrayList<Comment> replyComments = new ArrayList<>();
                Comment replyComment = new Comment();
                replyComment.setCommentId(rs.getInt("reply_id"));
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
            while (rs.next()) {
                StarRate starRate = new StarRate();
                starRate.setUser(entity);
                starRate.setRate(Integer.parseInt(rs.getString("rate")));
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
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
            while (rs.next()) {
                Test doneTest = new Test();
                doneTest.setTestId(rs.getInt("test_id"));
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
            while (rs.next()) {
                Set ownedSet = new Set();
                ownedSet.setSId(rs.getInt("sid"));
                ownedSet.setSName(rs.getString("sname"));
                ownedSet.setDescription(rs.getString("description"));
                ownedSet.setPrivate(rs.getBoolean("is_private"));
                ownedSet.setUser(entity);
                ownedSets.add(ownedSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownedSets;
    }

    public ArrayList<SelfTest> getSelfTests(User entity) {
        ArrayList<SelfTest> selfTests = new ArrayList<>();
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
            while (rs.next()) {
                SelfTest selfTest = new SelfTest();
                selfTest.setUser(entity);
                selfTest.setSelfTestId(rs.getInt("self-test_id"));
                selfTest.setNumbOfQues(rs.getInt("num_of_ques"));
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
            while (rs.next()) {
                Room ownedRoom = new Room();
                ownedRoom.setRoomId(rs.getInt("room_id"));
                ownedRoom.setUser(entity);
                ownedRoom.setRoomName(rs.getString("room_name"));
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
                joinedRoom.setRoomId(rs.getInt("room_id"));
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
            while (rs.next()) {
                Role role = new Role();
                role.setRId(rs.getInt("rid"));
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
                r.setRId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));

                Feature f = new Feature();
                f.setFId(rs.getInt("fid"));
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

    // if email is existed, return false
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

    // if username is existed, return false
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

    // get role id by role name
    private int getRoleId(String roleName) {
        String sqlGetIdUserRole = "SELECT `role`.`rid`\n" +
                "FROM `online_quizz`.`role`\n" +
                "WHERE `role`.`name` = ?";

        try {
            PreparedStatement stmGetIdUserRole = connection.prepareStatement(sqlGetIdUserRole);
            stmGetIdUserRole.setString(1, roleName);
            ResultSet rs = stmGetIdUserRole.executeQuery();
            while (rs.next()) {
                return rs.getInt("rid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public User getUserById(int userId) {
        String sqlGetUser = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avartar`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`uid` = ?";
        try {
            PreparedStatement stmGetUserById = connection.prepareStatement(sqlGetUser);
            stmGetUserById.setInt(1, userId);
            ResultSet rs = stmGetUserById.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(userId);
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setGivenName(rs.getString("given_name"));
                user.setFamilyName(rs.getString("family_name"));
                user.setPicture(rs.getString("avartar"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updatePassword(String email, String generatedSecuredPasswordHash) {
        String sqlUpdatePassword = "UPDATE `online_quizz`.`user`\n" +
                "SET\n" +
                "`password` = ?,\n" +
                "`updated_at` = current_timestamp()\n" +
                "WHERE `email` = ?;";
        try {
            PreparedStatement stmUpdatePassword = connection.prepareStatement(sqlUpdatePassword);
            stmUpdatePassword.setString(1, generatedSecuredPasswordHash);
            stmUpdatePassword.setString(2, email);
            stmUpdatePassword.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getUsersByEmail(String email) {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetUsersByEmail = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`email` LIKE ? OR `user`.`email` LIKE ? OR `user`.`email` LIKE ?";
        try {
            PreparedStatement stmGetUsersByEmail = connection.prepareStatement(sqlGetUsersByEmail);
            stmGetUsersByEmail.setString(1, "%" + email);
            stmGetUsersByEmail.setString(2, "%" + email + "%");
            stmGetUsersByEmail.setString(3, email + "%");
            ResultSet rs = stmGetUsersByEmail.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("uid"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setGivenName(rs.getString("given_name"));
                user.setFamilyName(rs.getString("family_name"));
                user.setPicture(rs.getString("avartar"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public ArrayList<User> getNewUserInWeek() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetNewUserInWeek = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE WEEK(created_at) = WEEK(NOW()) AND DAYOFWEEK(created_at) <= DAYOFWEEK(NOW());";
        try {
            PreparedStatement stmGetNewUserInWeek = connection.prepareStatement(sqlGetNewUserInWeek);
            ResultSet rs = stmGetNewUserInWeek.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFamilyName(rs.getString("family_name"));
                user.setGivenName(rs.getString("given_name"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
    // searchUserByName
    public ArrayList<User> searchUsersByName(String username) {
        ArrayList<User> users = new ArrayList<>();
        String sqlSearchUsersByName = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`username` LIKE ?";
        try {
            PreparedStatement stmSearchUsersByName = connection.prepareStatement(sqlSearchUsersByName);
            stmSearchUsersByName.setString(1, "%" + username + "%");
            ResultSet rs = stmSearchUsersByName.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("uid"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setGivenName(rs.getString("given_name"));
                user.setFamilyName(rs.getString("family_name"));
                user.setPicture(rs.getString("avartar"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public static void main(String[] args) {
        UserDBContext u = new UserDBContext();
        List<User> list = u.getUsersByEmail("vinhtthe176288@fpt.edu.vn");
//        List<Category> listC = dao.getAllCategory();

        for (User o : list) {
            System.out.println(o);
        }
    }
}
