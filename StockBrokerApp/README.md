# StockBroker Project

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Database Setup](#database-setup)
- [Project Setup](#project-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The StockBroker project is a simple command-line application that allows users to manage stock orders. Users can create orders, view their holdings, exit positions, and view their profit and loss. The project uses JDBC for database interactions and MySQL as the database.

## Features
- **Create Order**: Place an order by specifying stock name, price limit, and quantity.
- **Show Orders/Holdings**: Display all orders placed by the user with details.
- **Exit Order**: Sell or exit a position partially or fully and calculate profit/loss.
- **Show Profit and Loss**: View detailed profit and loss for all exited positions.
- **Database Persistence**: All data is stored and managed in a MySQL database.

## Requirements
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- JDBC Driver for MySQL (Connector/J)
- IDE (like IntelliJ IDEA, Eclipse) or a simple text editor
- Maven (optional, for dependency management)

## Database Setup
1. **Create Database and Tables**:
   ```sql
   -- Create database
   CREATE DATABASE IF NOT EXISTS stockbrokerdb;

   -- Use the database
   USE stockbrokerdb;

   -- Create users table
   CREATE TABLE IF NOT EXISTS Users (
       user_id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL,
       password VARCHAR(50) NOT NULL
   );

   -- Create stocks table
   CREATE TABLE IF NOT EXISTS Stocks (
       stock_id INT AUTO_INCREMENT PRIMARY KEY,
       stock_name VARCHAR(100) NOT NULL,
       remaining_quantity INT DEFAULT 0
   );

   -- Create orders table
   CREATE TABLE IF NOT EXISTS Orders (
       order_id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT,
       stock_id INT,
       buy_price DECIMAL(10, 2),
       quantity INT,
       sell_price DECIMAL(10, 2),
       sell_quantity INT DEFAULT 0,
       FOREIGN KEY (user_id) REFERENCES Users(user_id),
       FOREIGN KEY (stock_id) REFERENCES Stocks(stock_id)
   );

   -- Create profit/loss table
   CREATE TABLE IF NOT EXISTS ProfitLoss (
       profit_loss_id INT AUTO_INCREMENT PRIMARY KEY,
       order_id INT,
       stock_id INT,
       buy_price DECIMAL(10, 2),
       sell_price DECIMAL(10, 2),
       quantity INT,
       net_profit_loss DECIMAL(10, 2),
       net_profit_loss_percentage DECIMAL(10, 2),
       FOREIGN KEY (order_id) REFERENCES Orders(order_id),
       FOREIGN KEY (stock_id) REFERENCES Stocks(stock_id)
   );

***********************************************************************
				Project Structure
StockBroker/
│
├── Main.java                # Main application file

├── README.md                # Project README file

├── database.sql             # Database setup script

├── src/
│   ├── db/                  # Database connection and utilities

│   ├── models/              # Data models (User, Stock, Order, ProfitLoss)

│   ├── services/            # Business logic and services

│   └── utils/               # Utility classes


************************************************************************
				Usage

Create Order: Follow the prompts to enter stock name, price limit, and quantity.
Show Orders/Holdings: Select the option to view all your orders and holdings.
Exit Order: Enter order ID or stock name, then specify the quantity to sell and the sell price.
Show Profit and Loss: View a detailed profit and loss report for all exited positions.

************************************************************************
				THANK YOU
