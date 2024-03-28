package dao;

import entity.Feature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeatureDBContext extends DBContext{
    public ArrayList<Feature> list(int rid) {
        ArrayList<Feature> features = new ArrayList<>();
        try {
            String sql = "SELECT f.fid, f.fname, f.url FROM online_quizz.feature f\n" +
                    "inner join online_quizz.role_feature_mapping fm on fm.fid = f.fid\n" +
                    "where fm.rid = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature();
                feature.setFId(rs.getInt("fid"));
                feature.setFName(rs.getString("fname"));
                feature.setUrl(rs.getString("url"));
                features.add(feature);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return features;
    }

    @Override
    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
