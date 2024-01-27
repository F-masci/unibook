package it.ispw.unibook.dao;

import it.ispw.unibook.entity.SellableBookEntity;
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
    public CourseEntity retrieveCourseByCode(int code) {
        CourseEntity course = null;
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code=? ORDER BY startYear DESC, endYear DESC, name")){
            stm.setInt(1, code);
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
    public List<CourseEntity> retrieveCoursesByProfessor(AccountEntity professor) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professoreCode=? ORDER BY courseStartYear DESC, courseEndYear DESC, courseName")) {
            stm.setInt(1, professor.getCode());
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
    public CourseEntity retrieveCourseBySellableBook(SellableBookEntity sellableBook, AccountEntity seller) {

        CourseEntity course = null;

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code = (SELECT course FROM sellable_book WHERE code=? AND seller=?) LIMIT 1")) {
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, seller.getCode());
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
    public List<CourseEntity> retrieveCourses() {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course ORDER BY startYear DESC, endYear DESC, name")) {
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
