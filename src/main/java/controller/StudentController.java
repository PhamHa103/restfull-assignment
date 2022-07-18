package controller;

import entity.Student;
import service.StudentService;
import util.StudentUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/students")
public class StudentController {

    private StudentService studentService = new StudentService();

    @GET
    @Path("/?id={id}&fullName={fullName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAll(@PathParam("id") int id, @QueryParam("fullName") String fullName){
        return studentService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getOneById(@PathParam("id") int id){
        return studentService.getOneById(id);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNewStudent(Student student) {
        String validateResult = StudentUtil.validateInputData(student);
        if (validateResult == null) {
           return studentService.insert(student) ? "Thêm mới thành công" : "Thêm mới thất bại";
        }
        return validateResult + "\n Thêm mới thất bại";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteOneStudent(@PathParam("id") int id){
        Student student1 = getOneById(id);
        if (student1 == null){
            return "ID không có trong hệ thống. Xóa thông tin sinh viên thất bại";
        }
        else{
            return studentService.removeStudent(id) ? "Xóa thành công" : "Xóa thất bại";
        }
    }

}

