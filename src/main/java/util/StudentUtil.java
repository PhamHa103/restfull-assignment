package util;

import entity.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static util.DateUtil.*;

public class StudentUtil {
    public static String validateInputData(Student student) {
        String fullName = student.getFullName();
        if (fullName == null || fullName.trim().equals("")) {
            return "Chưa nhập tên sinh viên!";
        }
        if (fullName.length() > 50) {
            return "Tên sinh viên không được vượt quá 50 ký tự!";
        }
        Date birthday = student.getBirthday();
        if (birthday == null || birthday.equals("")) {
            return "Ngày tháng năm sinh sai định dạng!";
        }
        long ageByTotalYears = calculateAgeByTotalYears(convertTypeDateToLocalDate(birthday));
        if (ageByTotalYears>120){
            return "Độ tuổi quá tuổi của con người(120 tuổi)!";
        }
        long ageGetTotalDays = calculateAgeByTotalDays(convertTypeDateToLocalDate(birthday));
        if (ageGetTotalDays<0 ){
            return "Chọn ngày tháng năm sinh nhỏ hơn ngày hiện tại!";
        }
        String major = student.getMajor();
        if (major == null || major.trim().equals("")) {
            return "Chưa nhập tên khoa!";
        }
        String hometown = student.getHometown();
        if (hometown == null || hometown.trim().equals("")) {
            return "Chưa nhập quê quán!";
        }
        String gender = student.getGender();
        if (gender == null || gender.trim().equals("")) {
            return "Chưa nhập giới tính!";
        }
        Float averageMark = student.getAverageMark();
        if (averageMark == null) {
            return "Chưa nhập điểm trung bình";
        }
        if (averageMark < 0 || averageMark > 10) {
            return "Điểm trung bình là số thập phân 2 chữ số, trong đoạn [0; 10]";
        }
        return null;
    }

    public static Boolean validateMarkStringFormat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (Exception e) {}
        return false;
    }
}