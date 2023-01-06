package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;


@Repository
public class OrderRepository {

	@Autowired
    private JdbcTemplate template;

	public Optional<Order> findCustomerByNameInOrder(String name) {
		SqlRowSet rs = template.queryForRowSet(SQL_SELECT_CUSTOMER_BY_NAME_IN_ORDERS, name);

        final List<Order> orderNameList = new LinkedList<>();
        
		while (rs.next()) {
            orderNameList.add(Order.create(rs));
        }
        
        if (orderNameList.size() > 0) {
            return Optional.of(orderNameList.get(0));
        } else {
            return Optional.empty();
        }
    }

	public boolean insertPurchaseOrder(Order order) {
        return  template.update(SQL_INSERT_ORDER, 
            order.getOrderId(), order.getDeliveryId(), order.getName(), order.getAddress(), order.getEmail(), order.getStatus(), order.getOrderDate()) > 0;
    }

    public void addLineItems(Order order) {
        addLineItems(order.getLineItems(), order.getOrderId());
    }

    public void addLineItems(List<LineItem> lineItems, String orderId) {
        
        List<Object[]> data = new LinkedList<>();
        for (LineItem li: lineItems) {
            Object[] l = new Object[3];
            l[0] = li.getItem();
            l[1] = li.getQuantity();
            l[2] = orderId;
            data.add(l);
        }
      

        // Batch update
        template.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }
    
}

