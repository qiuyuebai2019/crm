package com.qust.web;

import com.qust.domain.AjaxRes;
import com.qust.domain.Customer;
import com.qust.domain.Email;
import com.qust.domain.Employee;
import com.qust.domain.PageListRes;
import com.qust.domain.QueryVo;
import com.qust.service.CustomerService;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月04日 14时22分
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer")
    public String customer(){
        return "views/customer.html";
    }

    @RequestMapping("/customerList")
    @ResponseBody
    public PageListRes customerList(QueryVo vo){
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(customerService.customerTotal());
        pageListRes.setRows(customerService.customerList(vo));
        return pageListRes;
    }

    @RequestMapping("/saveCustomer")
    @ResponseBody
    public AjaxRes saveCustomer(Customer customer){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            customerService.saveCustomer(customer);
            ajaxRes.setMes("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("保存失败");
        }
        return ajaxRes;
    }

    @RequestMapping("/updateCustomer")
    @ResponseBody
    public AjaxRes updateCustomer(Customer customer){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            customerService.updateCustomer(customer);
            ajaxRes.setMes("修改成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("修改失败");
        }
        return ajaxRes;
    }

    @RequestMapping("/distribution")
    @ResponseBody
    public AjaxRes distribution(Customer customer){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            customerService.distribution(customer);
            ajaxRes.setMes("分配成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("分配失败，不存在当前业务员");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;

    }


    @RequestMapping("/download")
    @ResponseBody
    public void download(HttpServletResponse response){
        try{
            QueryVo queryVo = new QueryVo();
            queryVo.setPage(1);
            queryVo.setRows(100);
            List<Customer> list = customerService.customerList(queryVo);
            //        创建Excel
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("客户信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("客户姓名");
            row.createCell(2).setCellValue("拜访日期");
            row.createCell(3).setCellValue("电话");
            row.createCell(4).setCellValue("地址");
            HSSFRow employeeRow =null;
            //取出每一个员工
            for(int i=0 ; i< list.size(); i++){
                Customer customer = list.get(i);
                employeeRow = sheet.createRow(i+1);
                employeeRow.createCell(0).setCellValue(customer.getId());
                employeeRow.createCell(1).setCellValue(customer.getCusName());
                if(customer.getCusVisitTime()!=null){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = simpleDateFormat.format(customer.getCusVisitTime());
                    employeeRow.createCell(2).setCellValue(format);
                }else {
                    employeeRow.createCell(2).setCellValue("");
                }
                employeeRow.createCell(3).setCellValue(customer.getCusTel());
                employeeRow.createCell(4).setCellValue(customer.getCusAddress());
            }
            String fileName = new String("客户信息.xls".getBytes("utf-8"),"iso8859-1");
            response.setHeader("content-Disposition","attachment;filename="+fileName);
            workbook.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping("/downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest request , HttpServletResponse response){
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("员工信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("用户名");
            row.createCell(2).setCellValue("入职日期");
            row.createCell(3).setCellValue("电话");
            row.createCell(4).setCellValue("邮件");
            String fileName = new String("模板信息.xls".getBytes("utf-8"),"iso8859-1");
            response.setHeader("content-Disposition","attachment;filename="+fileName);
            workbook.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            ajaxRes.setMes("导入成功");
            ajaxRes.setSuccess(true);
            HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row employeeRow =null;
            for(int i=1;i<=lastRowNum;i++){
                employeeRow = sheet.getRow(i);
                Customer customer = new Customer();
                customer.setCusName(getCellValue(employeeRow.getCell(1))==null?null:(String)getCellValue(employeeRow.getCell(1)));
                //貌似是类型转换问题  待解决
                //employee.setInputtime(getCellValue(employeeRow.getCell(2))==null?null:(Date)getCellValue(employeeRow.getCell(2)));
                customer.setCusTel(getCellValue(employeeRow.getCell(3))==null?null:(String)getCellValue(employeeRow.getCell(3)));
                customer.setCusAddress(getCellValue(employeeRow.getCell(4))==null?null:(String)getCellValue(employeeRow.getCell(4)));
                customerService.saveCustomer(customer);
            }
        }catch (Exception e){
            ajaxRes.setMes("导入失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    private Object getCellValue(Cell cell){
        switch(cell.getCellType()){
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    return cell.getDateCellValue();
                }else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }

}
