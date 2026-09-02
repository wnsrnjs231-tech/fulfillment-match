package com.fulfillment.match.config;

import com.fulfillment.match.domain.CurrentLogisticsMethod;
import com.fulfillment.match.domain.ProductCategory;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.repository.ShippingRequestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class DataInitializer implements CommandLineRunner {

    private final ShippingRequestRepository shippingRequestRepository;

    public DataInitializer(ShippingRequestRepository shippingRequestRepository) {
        this.shippingRequestRepository = shippingRequestRepository;
    }

    @Override
    public void run(String... args) {

        ShippingRequest request = new ShippingRequest();

        request.setProductCategory(ProductCategory.FASHION);
        request.setMonthlyOrders(3000);
        request.setSkuCount(150);
        request.setColdStorageRequired(false);
        request.setDesiredRegion("경기");
        request.setReturnInspectionRequired(true);
        request.setSpecialPackingRequired(false);
        request.setCurrentLogisticsMethod(CurrentLogisticsMethod.SELF_FULFILLMENT);
        request.setDescription("테스트 화주 요청입니다.");

        shippingRequestRepository.save(request);

    }
}