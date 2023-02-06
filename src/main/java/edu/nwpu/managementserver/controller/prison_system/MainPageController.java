package edu.nwpu.managementserver.controller.prison_system;

import edu.nwpu.managementserver.mapper.AccessRecordMapper;
import edu.nwpu.managementserver.mapper.PrisonModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:05
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.controller.prison_system.MainPageController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class MainPageController {
    @Autowired
    private PrisonModelMapper prisonModelMapper;
    @Autowired
    private AccessRecordMapper accessRecordMapper;


}
