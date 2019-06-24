package com.xmcc.repository;

import com.xmcc.entity.OrderMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {


    @Query(nativeQuery = true,value = "select * from order_master  where buyer_openid = ?1 ")
    List<OrderMaster> findAllByPageBean(String openid, Pageable pageable);



    @Query(nativeQuery = true,value = "select * from order_master  where buyer_openid = ?1 and order_id=?2 ")
    List<OrderMaster> findByOpenid(String openid,String orderId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "UPDATE order_master SET order_status=2 WHERE buyer_openid = ?1 and order_id=?2 ")
    void updateOrderStatus(String openid, String orderId);
}