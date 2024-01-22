package it.ispw.unibook.dao;

import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.ConnectionUniJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoUniJDBC implements CourseDao{

    private Connection connection = null;

    public CourseDaoUniJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    @Override
    public CourseEntity retrieveCourseByCode(int courseCode) {
        CourseEntity course = null;
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code=? ORDER BY startYear DESC, endYear DESC, name");
            stm.setInt(1, courseCode);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                course = new CourseEntity(
                    res.getInt("code"),
                    res.getString("name"),
                    res.getInt("startYear"),
                    res.getInt("endYear")
                );
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return course;
    }

    @Override
    public List<CourseEntity> retrieveCoursesByProfessor(int accountCode) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professoreCode=? ORDER BY courseStartYear DESC, courseEndYear DESC, courseName");
            stm.setInt(1, accountCode);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    CourseEntity course = new CourseEntity(
                        res.getInt("courseCode"),
                        res.getString("courseName"),
                        res.getInt("courseStartYear"),
                        res.getInt("courseEndYear")
                    );
                    coursesList.add(course);
                } while(res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return coursesList;

    }

    @Override
    public List<CourseEntity> retrieveCourses() {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course ORDER BY startYear DESC, endYear DESC, name");
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    CourseEntity course = new CourseEntity(
                        res.getInt("code"),
                        res.getString("name"),
                        res.getInt("startYear"),
                        res.getInt("endYear")
                    );
                    coursesList.add(course);
                } while(res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return coursesList;

    }

}