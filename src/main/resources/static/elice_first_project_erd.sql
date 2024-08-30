CREATE TABLE `item` (
  `item_id` INTEGER PRIMARY KEY,
  `user_id` INTEGER NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` INTEGER NOT NULL,
  `categoryId` Long NOT NULL,
  `category` VARCHAR(255) NOT NULL,
  `image_path` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `created_at` timestamp DEFAULT (now()),
  `updated_at` timestamp DEFAULT (now())
);

CREATE TABLE `Category` (
  `category_id` Integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp DEFAULT (now()),
  `updated_at` timestamp DEFAULT (now())
);

CREATE TABLE `users` (
  `user_id` Integer PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rate` double
);

CREATE TABLE `comment` (
  `comment_id` Integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` Integer,
  `reviewername` varchar(255),
  `content` varchar(255),
  `created_at` timestamp,
  `rating` Long
);

ALTER TABLE `item` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `Category` ADD FOREIGN KEY (`category_id`) REFERENCES `item` (`categoryId`);
