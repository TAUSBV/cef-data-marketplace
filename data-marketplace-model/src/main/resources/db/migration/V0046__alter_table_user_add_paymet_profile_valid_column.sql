ALTER TABLE `user`
    ADD COLUMN `payment_profile_valid` BIT(1) NOT NULL AFTER `terms_of_service_consent`;