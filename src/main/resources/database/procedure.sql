DELIMITER $$
CREATE PROCEDURE `addOrderItem`(IN `_idOrder` INT, IN `_idFoodItem` INT, IN `_idTopping` INT, IN `_quantity` INT, IN `_unitPrice` INT)
    MODIFIES SQL DATA
INSERT INTO `order_item` (`idOrder`, `idFoodItem`, `idTopping`, `quantity`, `unitPrice`) VALUES (_idOrder, _idFoodItem, _idTopping, _quantity, _unitPrice)  ON DUPLICATE KEY UPDATE    
`quantity`= `quantity` + _quantity AND `unitPrice` = _unitPrice$$
DELIMITER ;

SET @p0='1'; SET @p1='1'; SET @p2='0'; SET @p3='1'; SET @p4='10000'; CALL `addOrderItem`(@p0, @p1, @p2, @p3, @p4);