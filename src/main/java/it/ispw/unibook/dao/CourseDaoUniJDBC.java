package it.ispw.unibook.dao;

import it.ispw.unibook.controller.graphics.cli.Printer;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.ConnectionUniJDBC;

import javax.swing.text.html.ListView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoUniJDBC implements CourseDao{

    private Connection connection = null;

    public CourseDaoUniJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    @Override
    public ArrayList<CourseEntity> getAllCourses() {
        return null;
    }

    @Override
    public ArrayList<CourseEntity> getProfessorCourses(AccountEntity professorAccount) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        int code = professorAccount.getCode();
        ArrayList<CourseEntity> coursesList = new ArrayList<>();

        // Cerco i corsi
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professoreCode=?");
            stm.setInt(1, code);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    CourseEntity course = new CourseEntity(
                        res.getInt("courseCode"),
                        res.getString("courseName")
                    );
                    coursesList.add(course);
                } while(res.next());
            }

            res.close();

        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return coursesList;

    }

}
