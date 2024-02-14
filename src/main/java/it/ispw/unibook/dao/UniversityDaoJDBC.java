package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.utils.ConnectionUniJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityDaoJDBC implements UniversityDao {

    // Unica istanza di DAO universitario che sfrutta JDBC
    private static UniversityDaoJDBC instance = null;

    // Connessione al database universitario
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private UniversityDaoJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO di login che sfrutta JDBC
     * @return DAO di login che utilizza JDBC
     */
    public static UniversityDaoJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new UniversityDaoJDBC();
        return instance;
    }

    @Override
    public CourseEntity retrieveCourseByCode(int code) throws CourseNotFoundException {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course WHERE code=? ORDER BY startYear DESC, endYear DESC, name")){
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, code);
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                // Sfrutta la funzione ausiliare per creare l'entità a partire dal Resul Set
                return createEntityFromViewCourseResultSet(res);
            } else {
                // Se non ci sono risultati viene sollevata l'eccezione
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

        // Lista dei corsi da restituire
        List<CourseEntity> coursesList = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_professor_course WHERE professorEmail=? ORDER BY courseStartYear DESC, courseEndYear DESC, courseName")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setString(1, professor.getEmail());
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                do {
                    // Sfrutta la funzione ausiliare per creare l'entità a partire dal Resul Set
                    CourseEntity course = createEntityFromViewProfessorCourseResultSet(res);
                    // L'entità creata viene aggiunta alla lista
                    coursesList.add(course);
                } while(res.next());    // Si scorre il Result Set finché non ci sono piú record
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Viene ritornata la lista
        return coursesList;

    }

    @Override
    public List<CourseEntity> retrieveCourses() {

        // Lista dei corsi da restituire
        List<CourseEntity> coursesList = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_course ORDER BY startYear DESC, endYear DESC, name")) {
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                do {
                    // Sfrutta la funzione ausiliare per creare l'entità a partire dal Resul Set
                    CourseEntity course = createEntityFromViewCourseResultSet(res);
                    // L'entità creata viene aggiunta alla lista
                    coursesList.add(course);
                } while(res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Viene ritornata la lista
        return coursesList;

    }

    /**
     * Funzione ausiliare per creare un'entità a partire da un result set della vista del database
     * @param res Result set contenente le informazioni per l'entità
     * @return Entità con i dati presenti nel result set
     * @throws SQLException Viene sollevata se ci sono errori durante la lettura del result set
     */
    private CourseEntity createEntityFromViewCourseResultSet(ResultSet res) throws SQLException {
        return createEntityFromResultSet(res, new String[] {"code", "name", "startYear", "endYear"});
    }

    /**
     * Funzione ausiliare per creare un'entità a partire da un result set della tabella del database
     * @param res Result set contenente le informazioni per l'entità
     * @return Entità con i dati presenti nel result set
     * @throws SQLException Viene sollevata se ci sono errori durante la lettura del result set
     */
    private CourseEntity createEntityFromViewProfessorCourseResultSet(ResultSet res) throws SQLException {
        return createEntityFromResultSet(res, new String[] {"courseCode", "courseName", "courseStartYear", "courseEndYear"});
    }

    /**
     * Funzione ausiliare per creare un'entità a partire da un Result Set e le labels per accedere ai dati
     * @param res Result set contenente le informazioni per l'entità
     * @param labels Label per accedere ai dati all'interno del Result Set
     * @return Entità con i dati presenti nel result set
     * @throws SQLException Viene sollevata se ci sono errori durante la lettura del result set
     */
    private CourseEntity createEntityFromResultSet(ResultSet res, String[] labels) throws SQLException {
        return new CourseEntity(
                res.getInt(labels[0]),
                res.getString(labels[1]),
                res.getInt(labels[2]),
                res.getInt(labels[4])
        );
    }

}
