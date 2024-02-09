package it.ispw.unibook.dao;

import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.ConnectionUniJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityDaoJDBC implements UniversityDao {

    private Connection connection = null;

    public UniversityDaoJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    @Override
    public CourseEntity retrieveCourseByCode(int code) throws CourseNotFoundException {
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code=? ORDER BY startYear DESC, endYear DESC, name")){
            stm.setInt(1, code);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                return createEntityFromViewCourseResultSet(res);
            } else {
                throw new CourseNotFoundException();
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return null;
    }

    @Override
    public List<CourseEntity> retrieveCoursesByProfessor(AccountEntity professor) {

        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professoreCode=? ORDER BY courseStartYear DESC, courseEndYear DESC, courseName")) {
            stm.setInt(1, professor.getCode());
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    CourseEntity course = createEntityFromViewProfessorCourseResultSet(res);
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
    public CourseEntity retrieveCourseBySellableBook(SellableBookEntity sellableBook, AccountEntity seller) throws CourseNotFoundException {

        CourseEntity course = null;

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code = (SELECT course FROM sellable_book WHERE code=? AND seller=?) LIMIT 1")) {
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, seller.getCode());
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                course = createEntityFromViewCourseResultSet(res);
            } else {
                throw new CourseNotFoundException();
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return course;

    }

    @Override
    public List<CourseEntity> retrieveCourses() {

        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course ORDER BY startYear DESC, endYear DESC, name")) {
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    CourseEntity course = createEntityFromViewCourseResultSet(res);
                    coursesList.add(course);
                } while(res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return coursesList;

    }

    private CourseEntity createEntityFromViewCourseResultSet(ResultSet res) throws SQLException {
        return createEntityFromResultSet(res, new ArrayList<>(Arrays.asList("code", "name", "startYear", "endYear" )));
    }
    private CourseEntity createEntityFromViewProfessorCourseResultSet(ResultSet res) throws SQLException {
        return createEntityFromResultSet(res, new ArrayList<>(Arrays.asList("courseCode", "courseName", "courseStartYear", "courseEndYear" )));
    }

    private CourseEntity createEntityFromResultSet(ResultSet res, List<String> labels) throws SQLException {
        return new CourseEntity(
                res.getInt(labels.get(0)),
                res.getString(labels.get(1)),
                res.getInt(labels.get(2)),
                res.getInt(labels.get(3))
        );
    }

}
