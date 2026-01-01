/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Student
{
    private String name;
    private int matriculationNumber;
    private String studyProgram;
    private int semester;
    private double grade;
    
    Student(String name, int matriculationNumber, String studyProgram, 
            int semester, double grade) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
        this.studyProgram = studyProgram;
        this.semester = semester;
        this.grade = grade;
    }
    
    public int getMatriculationNumber() {
        return matriculationNumber;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public String toString() {
        return name + " (" + matriculationNumber + "), " + studyProgram + 
               ", Semester: " + semester + ", Note: " + grade;
    }
}