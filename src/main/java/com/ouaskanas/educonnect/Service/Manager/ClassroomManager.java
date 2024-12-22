package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.ClassroomDto;

import java.util.List;

public interface ClassroomManager {
    public Classroom getClassroom(int id);
    public List<Classroom> getAllClassrooms();
    public Classroom addClassroom(ClassroomDto classroom);
    public Classroom addStudentToclassroom(int classroom_id, int student_id);
    public Classroom deleteStudentfromClassroom(int classroom_id, int student_id);
    public void deleteClassroom(int id);
    public List<User> GetClassroomStudents(int classroom_id);
    public User GetClassroomTeacher(int classroom_id);
}
