-- Create Tables
CREATE TABLE `Staff` (
  `Id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Phone` varchar(25) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Driver` (
  `Id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Phone` varchar(25) NOT NULL,
  `Status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Customer` (
  `Id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Product` (
  `Id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `CategoryId` int(11),
  `Name` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ProductCategory` (
  `Id` int(11) PRIMARY KEY NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `OrderHeader` (
  `Id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `CustomerId` int(11),
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `OrderDetail` (
  `HeaderId` int(11),
  `ProductId` int(11),
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`HeaderId`, `ProductId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Add foreign keys after creating tables (sehingga urutan table dalam create table tidak menjadi masalah. Contoh: Jika foreign key dibuat pada create table, maka dengan urutan di atas akan terjadi error yang di mana field `CategoryId` pada table `Product` mereferensikan table `ProductCategory` padahal table `ProductCategory` belum dibuat akibat dari urutan table `ProductCategory` yg di-create setelah table `Product`)
ALTER TABLE `Product`
ADD FOREIGN KEY (`CategoryId`) REFERENCES ProductCategory(Id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `OrderHeader`
ADD FOREIGN KEY (`CustomerId`) REFERENCES Customer(Id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `OrderDetail`
ADD FOREIGN KEY (`HeaderId`) REFERENCES OrderHeader(Id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD FOREIGN KEY (`ProductId`) REFERENCES Product(Id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Insert Dummy Data
INSERT INTO `Staff` (`Name`, `Gender`, `Phone`, `Email`, `Password`) VALUES
('John Doe', 'Male', '081299001122', 'johndoe@gmail.com', 'johndoe123');

INSERT INTO `Driver` (`Name`, `Gender`, `Phone`, `Status`) VALUES
('Budi', 'Male', '082333471521', 'Available'),
('Cecep', 'Male', '082333471522', 'Available'),
('Dodi', 'Male', '082333471523', 'Available');

INSERT INTO `ProductCategory` VALUES
('1', 'Food'),
('2', 'Beverages');

INSERT INTO `Product` (`CategoryId`, `Name`, `Price`) VALUES
('1', 'Nasi Padang', '20000'),
('1', 'Nasi Kuning', '18000'),
('1', 'Ayam Penyet', '21000'),
('1', 'Ayam Bakar', '22000'),
('1', 'Ramen', '25000'),
('2', 'Coffee', '10000'),
('2', 'Iced Coffee', '12000'),
('2', 'Tea', '9000'),
('2', 'Iced Tea', '11000'),
('2', 'Boba', '20000');

