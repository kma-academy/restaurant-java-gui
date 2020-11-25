INSERT INTO `table` (`id`, `name`, `status`)
VALUES (NULL, 'Bàn 1', 'free'),
       (NULL, 'Bàn 2', 'free'),
       (NULL, 'Bàn 3', 'free');


INSERT INTO `customer` (`id`, `phoneNumber`, `name`, `address`, `birthday`)
VALUES (NULL, '0911175581', 'Cường', 'Nghệ An', '2000-04-10 00:00:00'),
       (NULL, '0911175581', 'Linh', NULL, NULL);


INSERT INTO `employee` (`id`, `username`, `password`, `name`, `phoneNumber`, `startDate`, `permissionName`, `permissionId`)
VALUES (NULL, 'admin', 'admin', 'Admin', NULL, '2020-11-24 00:00:00','Quản lý', 1),
       (NULL, 'nhanvien', '12345', 'Nhân Viên 1', '0911175581', '2020-11-24 12:15:08','Nhân viên', 2);


INSERT INTO `food_category` (`id`, `name`, `slug`)
VALUES (NULL, 'Đồ Ăn', 'do-an'),
       (NULL, 'Trà Sữa', 'tra-sua'),
       (NULL, 'Cà Phê', 'ca-phe'),
       (NULL, 'Topping', 'topping');


INSERT INTO `food_item` (`id`, `name`, `description`, `urlImage`, `unitName`, `unitPrice`, `idCategory`)
VALUES (NULL, 'Trà Sữa Trân Châu', NULL, NULL, 'Ly', 50000, 2),
       (NULL, 'Trà Sữa Sương Sáo', NULL, NULL, 'Ly', 45000, 2),
       (NULL, 'Trà Sữa Matcha(L)', NULL, NULL, 'Ly', 50000, 2),
       (NULL, 'Sữa Tươi Trân Châu Đường Đen', NULL, NULL, 'Ly', 45000, 2),
       (NULL, 'Trân Châu Tuyết Sợi', NULL, NULL, 'Phần', 10000, 4),
       (NULL, 'Trân Châu Đen', NULL, NULL, 'Phần', 10000, 4),
       (NULL, 'Trân Châu Trắng', NULL, NULL, 'Phần', 10000, 4),
       (NULL, 'Bánh Flan', NULL, NULL, 'Cái', 20000, 1),
       (NULL, 'Hướng dương', NULL, NULL, 'Túi', 10000, 1),
       (NULL, 'Cafe truyền thống', NULL, NULL, 'Cốc', 35000, 3),
       (NULL, 'Espresso', NULL, NULL, 'Cốc', 45000, 3);




INSERT INTO `order` (`id`, `idEmployee`, `idTable`, `type`, `orderDate`, `status`, `payDate`, `paidAmount`, `totalAmount`) VALUES
(1, 1, 1, 'local', '2020-11-24 14:28:41', 'unpaid', NULL, 0, 0),
(2, 1, 1, 'online', '2020-11-24 15:05:08', 'unpaid', '2020-11-24 00:00:00', 0, 0);


INSERT INTO `order_item` (`id`, `idOrder`, `idFoodItem`, `idTopping`, `quantity`, `unitPrice`) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 8, NULL, 1, 0),
(3, 1, 3, 6, 2, 0);


INSERT INTO `shipment` (`idOrder`, `idCustomer`, `shipperName`, `shipperPhoneNumber`, `status`, `notice`, `startDate`, `endDate`)
VALUES
(2, 1, 'Nguyễn Văn B', '09421321323', 'toreceive', NULL, '2020-11-24 00:00:00', NULL);
