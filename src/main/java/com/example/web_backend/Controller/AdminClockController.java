package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.Admin;
import com.example.web_backend.entity.AdminClock;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.mapper.AdminClockMapper;
import com.example.web_backend.mapper.AdminMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
class AdminClockInfo{
    private int id;
    private String name;
    private int clockInTimes;//打卡次数
    private int absentTimes;//缺勤次数
    private int leaveTimes;//请假次数
}
@RestController
public class AdminClockController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminClockMapper adminClockMapper;
    //获取某个员工某个时间段的打卡情况
    @GetMapping("/admin/getAdminClockByIdAndDateRange")
    public MessageEntity<AdminClockInfo> getAdminClockByIdAndDateRange(@RequestParam("startDate") String startDate,
                                                                        @RequestParam("endDate") String endDate,
                                                                       @RequestParam("adminId") int adminId) {

        Admin admin = adminMapper.selectById(adminId);
        if(admin==null){
            return MessageEntity.error(StateConstant.ADMIN_NOT_FOUND_CODE,StateConstant.ADMIN_NOT_FOUND_MSG);
        }
        if(startDate.compareTo(endDate)>0){//保证startDate小于endDate
            String temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        List<AdminClock> adminClocks = adminClockMapper.selectByIdAndDateRange(adminId, startDate, endDate);
        AdminClockInfo adminClockInfo = new AdminClockInfo();

        for(AdminClock adminClock:adminClocks){//统计出勤情况
            if(adminClock.getIsClock()==1){
                adminClockInfo.setClockInTimes(adminClockInfo.getClockInTimes()+1);
            }
            else if(adminClock.getIsClock()==0){
                adminClockInfo.setAbsentTimes(adminClockInfo.getAbsentTimes()+1);
            }
            else if(adminClock.getIsClock()==2){
                adminClockInfo.setLeaveTimes(adminClockInfo.getLeaveTimes()+1);
            }

        }
        adminClockInfo.setId(adminId);
        adminClockInfo.setName(admin.getName());
        return MessageEntity.success(adminClockInfo);
    }
    //查询某天的所有打卡记录
    @GetMapping("/admin/getAdminClockByDate")
    public MessageEntity<List<AdminClock>> getAdminClockByDate(@RequestParam("date") String date) {
        List<AdminClock> adminClocks = adminClockMapper.selectByDate(date);
        return MessageEntity.success(adminClocks);
    }
    //打卡
    @PostMapping("/admin/clockIn")
    public MessageEntity<String> clockIn(@RequestParam("adminId") int adminId,
                                         @RequestParam("isClock") int isClock) {
        Admin admin = adminMapper.selectById(adminId);
        if(admin==null){
            return MessageEntity.error(StateConstant.ADMIN_NOT_FOUND_CODE,StateConstant.ADMIN_NOT_FOUND_MSG);
        }
        AdminClock adminClock = new AdminClock();
            adminClock.setAdminId(adminId);
            adminClock.setDate(DateService.getTodayDate());
            adminClock.setIsClock(isClock);
            adminClockMapper.insert(adminClock);

        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
}
