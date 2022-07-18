package controller;

import entity.Student;
import service.StudentService;
import util.DateUtil;
import util.StudentUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/students")
public class StudentController {

    private StudentService studentService = new StudentService();

    //API Chúc mừng sinh nhật sinh viên
    @GET
    @Path("/birthday")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Student> getListBirthdayDate(){
        int month = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        int day = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
        return studentService.getListBirthdayDate(month, day);
    }

    //API Liệt kê danh sách sinh viên theo tiêu chí (Tìm kiếm sinh viên)
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Student> getAll(@QueryParam("fullName") String fullName, @QueryParam("startDate") String startDate,
                                @QueryParam("endDate") String endDate, @QueryParam("gender") String gender,
                                @QueryParam("hometown") String hometown, @QueryParam("className") String className,
                                @QueryParam("major") String major, @QueryParam("averageMarkMin") String averageMarkMin,
                                @QueryParam("averageMarkMax") String averageMarkMax) {
        Date _startDate = null;
        List<Student> students = new ArrayList<>();
        if (startDate != null) {
            if (DateUtil.checkDateStringFormat(startDate)) {
                _startDate = DateUtil.convertStringToDate(startDate);
            } else {
                return students;
            }
        }
        Date _endDate = null;
        if (endDate != null) {
            if (DateUtil.checkDateStringFormat(endDate)) {
                _endDate = DateUtil.convertStringToDate(endDate);
            } else {
                return students;
            }
        }
        Float _averageMarkMin = null;
        Float _averageMarkMax = null;
        if (averageMarkMin != null) {
            if (StudentUtil.validateMarkStringFormat(averageMarkMin))
                _averageMarkMin = Float.parseFloat(averageMarkMin);
            else
                return students;
        }
        if (averageMarkMax != null) {
            if (StudentUtil.validateMarkStringFormat(averageMarkMax)) {
                _averageMarkMax = Float.parseFloat(averageMarkMax);
            }
            else {
                return students;
            }
        }
        students = studentService.getAll(fullName, _startDate, _endDate, gender, hometown, className, major, _averageMarkMin, _averageMarkMax);
        return students;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getOneById(@PathParam("id") int id) {
        return studentService.getOneById(id);
    }

    //API thêm mới sinh viên
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

    //API cập nhật thông tin sinh viên
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateStudent(Student student) {
        Student student1 = getOneById(student.getId());
        if (student1 == null) {
            return "ID không có trong hệ thống. Cập nhật thông tin sinh viên thất bại";
        }
        String validateResult = StudentUtil.validateInputData(student);
        if (validateResult == null) {
            return studentService.update(student) ? "Cập nhật thành công" : "Cập nhật thất bại";
        }
        return validateResult + "\n Cập nhật thất bại";
    }

    //API xóa thông tin sinh viên
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteOneStudent(@PathParam("id") int id) {
        String validateById = validateById(id);
        if (validateById == null) {
            return studentService.removeStudent(id) ? "Xóa thành công" : "Xóa thất bại";
        }
        return validateById;
    }

    public String validateById(int id) {
        Student student1 = getOneById(id);
        if (student1 == null) {
            return "ID không có trong hệ thống. Xóa thông tin sinh viên thất bại";
        }
        return null;
    }
}

