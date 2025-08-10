//package Govinda.Snack.service;
//
//import Govinda.Snack.model.Order;
//import Govinda.Snack.model.SnackItem;
//import Govinda.Snack.model.SnackProduct;
//import Govinda.Snack.model.dto.OrderDashboardDTO;
//import Govinda.Snack.repository.AdminOrderRepository;
//import Govinda.Snack.repository.SnackProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class OrderDashboardService {
//
//    private final AdminOrderRepository adminOrderRepository;
//    private final SnackProductRepository snackProductRepository;
//
//    public List<OrderDashboardDTO> getAllOrdersWithTotal() {
//        List<Order> orders = adminOrderRepository.findAll();
//        List<OrderDashboardDTO> result = new ArrayList<>();
//
//        for (Order order : orders) {
//            double totalAmount = 0.0;
//
//            if (order.getItems() != null) {
//                for (SnackItem item : order.getItems()) {
//                    Optional<SnackProduct> productOpt =
//                            snackProductRepository.findByNameIgnoreCase(item.getSnackName());
//                    if (productOpt.isPresent()) {
//                        totalAmount += productOpt.get().getPrice() * item.getQuantity();
//                    }
//                }
//            }
//
//            result.add(new OrderDashboardDTO(
//                    order.getId(),
//                    order.getCustomerName(),
//                    order.getEmail(),
//                    order.getContactNumber(),
//                    order.getAddress(),
//                    order.getItems(),
//                    totalAmount,
//                    order.isDelivered(),
//                    order.getCreatedAt()
//            ));
//        }
//
//        return result;
//    }
//}