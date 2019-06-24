package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.Staff;

public interface PDALoginService {
    /**
     * 查询员工信息
     * @param userName
     * @return
     */
    Staff selectStaff(String userName);
}
