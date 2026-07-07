package com.mycompany.main.model;

// Utils
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author guilh
 */
public class Discipline {
   private String name;
   private int semester;
   private String time;
   private Teacher teacher;
   private List<Student> studentList;
   
    // Constructor
    public Discipline() {
        this.studentList = new ArrayList<>();
    }
    public Discipline(String name, int semester, String time, Teacher teacher, List<Student> studentList) {
        this.name = name;
        this.semester = semester;
        this.time = time;
        this.teacher = teacher;
        this.studentList = studentList;
    }
    public void copy(Discipline other) {
       this.name = other.getName();
       this.semester = other.getSemester();
       this.time = other.getTime();
       this.teacher = other.getTeacher();
       this.studentList = new ArrayList<>(other.getStudentList()); 
    }
   
    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome da disciplina: ").append(name);
        sb.append(" | Semestre: ").append(semester);
        sb.append(" | Horário: ").append(time).append("\n");
        sb.append("-> ").append(teacher != null ? teacher.toString() : "Sem professor cadastrado").append("\n");
        sb.append("-> Alunos Matriculados: ").append(studentList.size()).append("\n");
        for (Student s : studentList) {
            sb.append("   • ").append(s.toString()).append("\n");
        }
        return sb.toString();
    }
    
    // Getters and Setters
    public String getName() {
         return name;
     }
    public int getSemester() {
         return semester;
     }
    public String getTime() {
         return time;
     }
    public Teacher getTeacher() {
         return teacher;
     }
    public List<Student> getStudentList() {
         return studentList;
     }

    public void setName(String name) {
         this.name = name;
     }
    public void setSemester(int semester) {
         this.semester = semester;
     }
    public void setTime(String time) {
         this.time = time;
     }
    public void setTeacher(Teacher teacher) {
         this.teacher = teacher;
     }
    public void setStudentList(List<Student> studentList) {
         this.studentList = studentList;
     }
}
