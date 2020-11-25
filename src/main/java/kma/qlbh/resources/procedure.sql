DELIMITER $$
DROP FUNCTION IF EXISTS getTranslatorGroupsFromIdManga;
CREATE FUNCTION getCategoriesFromIdManga(id VARCHAR(50)) RETURNS INT
BEGIN
    DECLARE result INT;
    SELECT GROUP_CONCAT(
        DISTINCT CONCAT(`id`, '|', `slug`, '|' , `name`)
        ORDER BY `idManga` SEPARATOR ','
        )
    INTO result 
    FROM `manga_categories`
	JOIN `categories`
	ON `manga_categories`.`idCategory` = `categories`.`id`
	WHERE `idManga` = id
	GROUP BY `idManga`;
    RETURN result;
END;
DELIMITER ;