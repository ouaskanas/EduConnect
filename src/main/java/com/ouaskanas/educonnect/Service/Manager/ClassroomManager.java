package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Classroom;
import com.ouaskanas.educonnect.Dto.ClassroomDto;

import java.util.List;

public interface ClassroomManager {
    public Classroom getClassroom(long id);
    public List<Classroom> getAllClassrooms();
    public Classroom addClassroom(ClassroomDto classroom);
    public Classroom addStudentToclassroom(long classroom_id, long student_id);
    public Classroom deleteStudentfromClassroom(long classroom_id, long student_id);
    public void deleteClassroom(long id);
}
