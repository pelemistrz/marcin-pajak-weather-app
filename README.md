# Weather Forecast Desktop Application

**Technologies Used:**
- Java
- JavaFX
- JUnit 5
- Mockito

## Overview

Weather Forecast is a desktop application designed to display weather forecasts for the upcoming days. It allows users to compare weather conditions in two different locations.

The project was developed in **Java** using the **Model-View-Controller (MVC)** design pattern and **SOLID** principles, ensuring the code is clear and easy to modify.

## Features

- Display weather forecast for the upcoming days.
- Compare weather conditions in two different locations.
- User-friendly interface built with **JavaFX**.
- Weather data fetched from the **openweathermap.org** service via API calls using **HTTP** and the **GET** method.
- JSON data is processed and stored in the application model.
- Unit tests written with **JUnit 5** and **Mockito**.

## Technologies Breakdown

- **Backend:**  
  The core logic of the application is written in **Java**, utilizing the **MVC** pattern. This structure ensures that the data, user interface, and application logic are cleanly separated for better maintainability and scalability.

- **Frontend:**  
  The user interface is designed using **JavaFX**, providing a sleek and intuitive experience.

- **Weather Data:**  
  Weather information is retrieved from **openweathermap.org** via API requests. Data is parsed from **JSON** format and used to display relevant forecast details.

- **Testing:**  
  The application includes tests written with **JUnit 5** and **Mockito**, ensuring functionality and reliability.
