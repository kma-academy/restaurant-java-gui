-- Employee --

--Hiển thị thông tin các nhân viên
SELECT e.id, e.name, e.username, password, phoneNumber, startDate, ep.name as permissionName, ep.slug as permissionSlug, ep.id as permissionId FROM `employee` e JOIN `employee_position` ep ON e.idPosition = ep.id
--Thêm nhân viên
INSERT INTO `employee` (`id`, `username`, `password`, `name`, `phoneNumber`, `startDate`, `idPosition`) VALUES (NULL, 'nhanvien2', '12345', 'Nhân Viên 2', NULL, current_timestamp(), '2')
--Sửa nhân viên
UPDATE `employee` SET `username` = 'nhanvien2', `phoneNumber` = '' WHERE `employee`.`id` = 2;
--Xóa nhân viên
DELETE FROM `employee` WHERE `employee`.`id` = 3

-- Food Item --

--Thêm 