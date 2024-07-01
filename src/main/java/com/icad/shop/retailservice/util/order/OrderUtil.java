package com.icad.shop.retailservice.util.order;

import com.icad.shop.retailservice.dto.pojo.OrderDataPojo1;
import com.icad.shop.retailservice.repository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderUtil {

    private final OrderRepository orderRepository;

    public String generateOrderCode() {
        return "T-" + LocalDate.now() + UUID.randomUUID();
    }

    public void exportJasperReport(HttpServletResponse response) throws IOException, JRException {
        List<OrderDataPojo1> orderDataPojoList = orderRepository.findAllOrderDetails();
        File file = ResourceUtils.getFile("classpath:report_shop.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDataPojoList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Online Retail Service");
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
