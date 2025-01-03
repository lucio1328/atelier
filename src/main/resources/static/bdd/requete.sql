-- Calcul du reste en stock par piece et prix unitaire
CREATE OR REPLACE VIEW nombreEnStock as
SELECT 
    ap.id_pieces_detachee,
    ap.prix_unitaire,
    SUM(CASE  
            WHEN ms.id_type_mouvement = 1 THEN ms.quantite  -- Entrée
            WHEN ms.id_type_mouvement = 2 THEN -ms.quantite -- Sortie
            ELSE 0
        END) AS quantite_reste
FROM pieces_detachees pd
LEFT JOIN achat_pieces ap ON pd.id = ap.id_pieces_detachee
LEFT JOIN mouvements_stock ms ON ap.id = ms.id_achat_pieces
GROUP BY 
    pd.id, ap.prix_unitaire;


-- Enregistrer sortie de stock
DELIMITER $$

CREATE PROCEDURE EnregistrerSortieStock (
    IN p_Id_pieces_detachees INT,
    IN p_quantite INT,
    IN p_date_mouvement DATE,
    IN p_Id_reparations INT,
    IN p_Id_type_mouvement INT
)
BEGIN
    DECLARE remaining_quantity INT;
    DECLARE allocated_quantity INT;

    -- Boucle pour consommer les quantités disponibles par détail d'achat (FIFO)
    WHILE p_quantite > 0 DO
        SELECT Id_details_achat_pieces, quantite_disponible
        INTO @current_id_details, @current_disponible
        FROM details_achat_pieces
        WHERE Id_pieces_detachees = p_Id_pieces_detachees
          AND quantite_disponible > 0
        ORDER BY date_achat ASC
        LIMIT 1;

        -- Vérifie si une quantité est disponible
        IF @current_disponible IS NULL THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Stock insuffisant pour cette pièce.';
        END IF;

        -- Alloue la quantité maximale possible
        SET allocated_quantity = LEAST(p_quantite, @current_disponible);
        SET p_quantite = p_quantite - allocated_quantity;

        -- Crée un mouvement de stock pour cette allocation
        INSERT INTO mouvements_stock (
            quantite, date_mouvement, Id_reparations, Id_type_mouvement, Id_details_achat_pieces
        ) VALUES (
            allocated_quantity, p_date_mouvement, p_Id_reparations, p_Id_type_mouvement, @current_id_details
        );

        -- Met à jour la quantité disponible dans le détail d'achat
        UPDATE details_achat_pieces
        SET quantite_disponible = quantite_disponible - allocated_quantity
        WHERE Id_details_achat_pieces = @current_id_details;
    END WHILE;
END$$

DELIMITER ;

