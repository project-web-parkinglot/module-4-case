package com.parkingcar.controller.employeeController;

import com.parkingcar.model.employee.Employee;
import com.parkingcar.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admins/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0",required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String searchName ,
                           @RequestParam(required = false) String sortProperty,
                           @RequestParam(required = false) String condition ,
                           Model model){
        if (sortProperty == null || sortProperty.isEmpty()) {
            sortProperty = "acc.status";
        }
        if (condition == null || condition.isEmpty()) {
            condition = "asc";
        }
        Sort sort = Sort.by(condition.equalsIgnoreCase("asc") ? Sort.Order.asc(sortProperty) : Sort.Order.desc(sortProperty));
        Pageable pageable = PageRequest.of(page, 2);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Employee> employeePage = employeeService.findALlEmployee(pageable,searchName,sortProperty,condition);
        model.addAttribute("list", employeePage);
        model.addAttribute("searchName", searchName);
        return "/employee/list";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id){
        employeeService.deleteById(id);
        return "redirect:/admins/employee";
    }
}
