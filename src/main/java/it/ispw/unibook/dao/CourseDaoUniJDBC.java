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
    public List<CourseEntity> getAllCourses() {
        return null;
    }

    @Override
    public List<CourseEntity> getProfessorCourses(int accountCode) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professoreCode=?");
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

}
