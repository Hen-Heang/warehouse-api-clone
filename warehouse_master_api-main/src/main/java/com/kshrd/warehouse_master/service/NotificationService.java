package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.notification.NotificationRetailer;
import org.apache.ibatis.annotations.Select;

import java.text.ParseException;
import java.util.List;

public interface NotificationService {
    List<NotificationRetailer> getUserAllNotification() throws ParseException;

    String markAsRead(Integer id);

    String markAllNotificationAsRead();
}
