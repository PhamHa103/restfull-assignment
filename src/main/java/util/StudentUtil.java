package util;

import entity.Student;

import java.util.Date;

import util.DateUtil;

public class StudentUtil {
    public static String validateInputData(Student student){
        String fullName = student.getFullName();
        if (fullName == null || fullName.equals("")) {
            return "Chưa nhập tên sinh viên!";
        }
        if (fullName.length() > 50) {
            return  "Tên sinh viên không được vượt quá 50 ký tự!";
        }
        Date birthday = student.getBirthday();
        if (birthday == null || birthday.equals("")){
            return "Chưa nhập ngày tháng năm sinh!";
        }
        Integer age = DateUtil.getYear(new Date()) - DateUtil.getYear(birthday);
        if (age < 0 || age > 120){
            return "Tuổi phải lớn hơn 0 và nhỏ hơn 120!";
        }
        String className = student.getClassName();
        if (className == null || className.equals("")){
            return "Chưa nhập tên lớp!";
        }
        String major = student.getMajor();
        if (major == null || major.equals("")){
            return "Chưa nhập tên khoa!";
        }
        String hometown = student.getHometown();
        if (hometown == null || hometown.equals("")){
            return "Chưa nhập quê quán!";
        }
        String gender = student.getGender();
        if (gender == null || gender.equals("")){
            return "Chưa nhập giới tính!";
        }
        Double averageMark = student.getAverageMark();
        if (averageMark == null){
            return "Chưa nhập điểm trung bình";
        }
        if (averageMark < 0 || averageMark > 10) {
            return "Điểm trung bình là số thập phân 2 chữ số, trong đoạn [0; 10]";
        }
        return null;
    }

}