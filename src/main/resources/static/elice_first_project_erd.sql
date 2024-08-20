CREATE TABLE `item` (
  `item_id` INTEGER PRIMARY KEY,
  `seller_id` INTEGER NOT NULL,
  `image` Image NOT NULL,
  `category` ENUM ('PET_ITEM', 'CLOTHES', 'GADGET', 'BOOKS', 'BEAUTY') NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` INTEGER NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `upload_date` timestamp DEFAULT (now())
);

CREATE TABLE `users` (
  `user_id` Integer PRIMARY KEY AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
);

CREATE TABLE `usersExtraData` (
  `user_id` Integer PRIMARY KEY,
  `item_id` INTEGER,
  `comment_id` INTEGER
);

CREATE TABLE `comment` (
  `comment_id` Integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` Integer,
  `username` varchar(255),
  `content` varchar(255),
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `chats` (
  `chat_id` varchar(255) PRIMARY KEY,
  `seller_id` integer NOT NULL,
  `buyer_id` integer NOT NULL,
  `seller_chat_history` chats NOT NULL,
  `buyer_chat_history` chats NOT NULL
);

CREATE TABLE `seller_chat_history` (
  `seller_id` integer PRIMARY KEY,
  `chat` varchar(255)
);

CREATE TABLE `buyer_chat_history` (
  `buyer_id` integer PRIMARY KEY,
  `chat` varchar(255)
);

ALTER TABLE `chats` ADD FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `chats` ADD FOREIGN KEY (`buyer_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `usersExtraData` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`user_id`) REFERENCES `usersExtraData` (`user_id`);

ALTER TABLE `item` ADD FOREIGN KEY (`item_id`) REFERENCES `usersExtraData` (`user_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`comment_id`) REFERENCES `usersExtraData` (`comment_id`);

ALTER TABLE `chats` ADD FOREIGN KEY (`seller_id`) REFERENCES `seller_chat_history` (`seller_id`);

ALTER TABLE `buyer_chat_history` ADD FOREIGN KEY (`buyer_id`) REFERENCES `chats` (`buyer_id`);
