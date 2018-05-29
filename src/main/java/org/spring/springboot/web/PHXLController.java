package org.spring.springboot.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.phxl.*;
import org.spring.springboot.util.BaseResponse;
import org.spring.springboot.util.Pager;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nice on 2018/5/11.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/PHXLController")
public class PHXLController {

    /**
     * 患者计费
     *
     * @return
     */
    @RequestMapping(value = "/billing")
    public BaseResponse billing() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setReturnCode(1);
        baseResponse.setDataInfo(1000);
        baseResponse.setMessage("成功");
        return baseResponse;
    }
    /**
     * 查询产品信息
     *
     * @return
     */
    @RequestMapping(value = "/queryProductListByProductCode")
    public BaseResponse queryProductListByProductCode() {
        BaseResponse baseResponse = new BaseResponse();
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setProductCode("productCode");
        product.setBriefCode("PC");
        product.setModel("100ML");
        product.setProductName("扩大针管");
        product.setRetailPrice(new BigDecimal(10.0));
        list.add(product);
        baseResponse.setReturnCode(1);
        baseResponse.setDataInfo(list);
        baseResponse.setMessage("成功");
        return baseResponse;
    }

    /**
     * 查询产品信息列表
     *
     * @return
     */
    @RequestMapping(value = "/pageProductList")
    public BaseResponse pageProductList() {
        BaseResponse baseResponse = new BaseResponse();
        Pager pager=new Pager();
        pager.setPageNum(1);
        pager.setPageSize(5);
        pager.setTotal(21);
        pager.setTotalPage(5);
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setProductCode("productCode");
        product.setBriefCode("PC");
        product.setModel("100ML");
        product.setProductName("扩大针管");
        product.setRetailPrice(new BigDecimal(10.0));

        Product product2 = new Product();
        product2.setProductCode("productCode2");
        product2.setBriefCode("PC");
        product2.setModel("个");
        product2.setProductName("人工心脏瓣");
        product2.setRetailPrice(new BigDecimal(100000.0));

        Product product3 = new Product();
        product3.setProductCode("product3");
        product3.setBriefCode("PC");
        product3.setModel("个");
        product3.setProductName("人工心脏瓣");
        product3.setRetailPrice(new BigDecimal(100000.0));

        Product product4 = new Product();
        product4.setProductCode("product4");
        product4.setBriefCode("PC");
        product4.setModel("个");
        product4.setProductName("人工心脏瓣");
        product4.setRetailPrice(new BigDecimal(100000.0));

        Product product5 = new Product();
        product5.setProductCode("product5");
        product5.setBriefCode("PC");
        product5.setModel("个");
        product5.setProductName("人工心脏瓣");
        product5.setRetailPrice(new BigDecimal(100000.0));


        list.add(product);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        list.add(product5);
        pager.setRows(list);
        baseResponse.setReturnCode(1);
        baseResponse.setDataInfo(pager);
        baseResponse.setMessage("成功");
        return baseResponse;
    }

    /**
     * 查询患者信息
     *
     * @return
     */
    @RequestMapping(value = "/queryPatientInfo")
    public BaseResponse queryMedicalInfo() {
        BaseResponse baseResponse = new BaseResponse();
        Patient patient = new Patient();
        patient.setBedNo("B2001304");
        patient.setChargeType("1");
        patient.setChargeTypeName("自费");
        patient.setDeptId("D00D2FDADB584BDFACD3CDBAD93D6E9F");
        patient.setDeptName("手术外科");
        patient.setIdNumber("4201161993XXXXXXXXXXX");
        patient.setMedicalNumber("20171221103257003");
        patient.setOperatingTime("2017-12-22 16:40:56");
        patient.setOperatorNo("Ope201712221640560005");
        patient.setPatientAge(25);
        patient.setPatientName("张三");
        patient.setPatientSex("1");
        patient.setVisitid("ZY20171221103257003");
        patient.setRegisteredNumber("住院号码");

        List<Operation> operationList = new ArrayList<>();
        Operation operation=new Operation();
        operation.setPatientAge(25);
        operation.setPatientName("张三");
        operation.setPatientSex("1");
        operation.setOperationName("骨折手术");
        operation.setIdNumber("4201161993XXXXXXXXXXX");
        operation.setBedNo("B2001304");
        operation.setDoctor("张医生");
        operation.setOperationDate("2017-12-22 16:40:56");
        operation.setNarcosisType("局部麻醉");
        operation.setRoom("B19015");
        operation.setNurse("巡回护士");

        List<ProductParam> productList=new ArrayList<>();
        ProductParam productParam = new ProductParam();
        productParam.setProductCode("productCode");
        productParam.setBriefCode("PC");
        productParam.setModel("100ML");
        productParam.setProductName("扩大针管");
        productParam.setRetailPrice(new BigDecimal(10.0));
        productParam.setNumber(7);
        productList.add(productParam);

        operation.setProductList(productList);
        operationList.add(operation);
        patient.setOperationList(operationList);



        baseResponse.setReturnCode(1);
        baseResponse.setDataInfo(patient);
        baseResponse.setMessage("成功");
        return baseResponse;
    }

    /**
     * 查询产品信息列表
     *
     * @return
     */
    @RequestMapping(value = "/pageDeptList")
    public BaseResponse pageDeptList() {
        BaseResponse baseResponse = new BaseResponse();
        Pager pager=new Pager();
        pager.setPageNum(1);
        pager.setPageSize(5);
        pager.setTotal(21);
        pager.setTotalPage(5);
        List<Dept> list = new ArrayList<>();
        Dept dept = new Dept();
        dept.setDeptCode("deptCode1");
        dept.setBriefCode("PC");
        dept.setDeptName("deptName1");
        dept.setDeptTypeCode("deptTypeCode");
        dept.setDeptTypeName("deptTypeName");
        dept.setParentDeptGuid("parentDeptGuid");

        Dept dept2 = new Dept();
        dept2.setDeptCode("deptCode2");
        dept2.setBriefCode("PC");
        dept2.setDeptName("deptName2");
        dept2.setDeptTypeCode("deptTypeCode");
        dept2.setDeptTypeName("deptTypeName");
        dept2.setParentDeptGuid("parentDeptGuid");

        Dept dept3 = new Dept();
        dept3.setDeptCode("deptCode3");
        dept3.setBriefCode("BC");
        dept3.setDeptName("deptName3");
        dept3.setDeptTypeCode("deptTypeCode3");
        dept3.setDeptTypeName("deptTypeName3");
        dept3.setParentDeptGuid("parentDeptGuid");

        Dept dept4 = new Dept();
        dept4.setDeptCode("deptCode4");
        dept4.setBriefCode("BC");
        dept4.setDeptName("deptName4");
        dept4.setDeptTypeCode("deptTypeCode4");
        dept4.setDeptTypeName("deptTypeName4");
        dept4.setParentDeptGuid("parentDeptGuid");

        Dept dept5 = new Dept();
        dept5.setDeptCode("deptCode5");
        dept5.setBriefCode("BC");
        dept5.setDeptName("deptName5");
        dept5.setDeptTypeCode("deptTypeCode5");
        dept5.setDeptTypeName("deptTypeName5");
        dept5.setParentDeptGuid("parentDeptGuid");


        list.add(dept);
        list.add(dept2);
        list.add(dept3);
        list.add(dept4);
        list.add(dept5);
        pager.setRows(list);
        baseResponse.setReturnCode(1);
        baseResponse.setDataInfo(pager);
        baseResponse.setMessage("成功");
        return baseResponse;
    }
    @RequestMapping(value = "/queryOperatorInfo")
    public BaseResponse queryOperatorInfo(){
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setReturnCode(1);
        baseResponse.setMessage("成功");
        Operator operator=new Operator();
        operator.setDeptGuid(UUID.randomUUID().toString().replaceAll("-", ""));
        operator.setDeptName("手术科");
        operator.setOperatorIdentity("医生");
        operator.setOperatorName("赵医生");
        operator.setOperatorNo("NO9872");
        baseResponse.setDataInfo(operator);
        return baseResponse;
    }
}
