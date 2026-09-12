# Meme Finder

A full-stack meme search application built with **React** and **Spring Boot** that searches multiple meme sources and displays the results in one place.

The project currently aggregates meme results from **Memedroid** and **Imgflip**.

## Features

- Search memes using keywords
- Fetch results from multiple meme sources
- Maximum 10 results from each source
- Continue searching even if one external source fails
- Loading and error handling
- Responsive meme grid
- Search using the Enter key
- Displays meme title and source
- REST API built with Spring Boot
- Web content extraction using Jsoup

## Tech Stack

### Frontend

- React
- JavaScript
- Vite
- CSS
- Fetch API

### Backend

- Java
- Spring Boot
- Spring Web
- Jsoup
- Maven

## Architecture

```text
React Frontend
      |
      | HTTP Request
      v
Spring Boot REST API
      |
      v
   MemeService
    /      \
   /        \
Memedroid   Imgflip
 Source      Source
   \          /
    \        /
     v      v
  MemeResult DTO
       |
       v
   JSON Response
       |
       v
 React Frontend
```

The frontend sends a search query to the Spring Boot backend.

The backend searches each configured meme source independently, converts the results into a common `MemeResult` format, combines them, and returns the results as JSON.

## API

### Search Memes

```http
GET /api/memes/search?query={keyword}
```

Example:

```http
GET /api/memes/search?query=java
```

Example response:

```json
[
  {
    "title": "Example meme",
    "imageUrl": "https://example.com/meme.jpg",
    "source": "Memedroid"
  }
]
```

## Project Structure

```text
meme-finder/
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── mvnw
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── vite.config.js
│
├── .gitignore
└── README.md
```

## Running Locally

### Backend

Go to the backend directory:

```bash
cd backend
```

Run Spring Boot:

```bash
./mvnw spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

### Frontend

Open another terminal and go to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend normally runs on:

```text
http://localhost:5173
```

## Error Handling

Each external meme source is handled independently.

If one source fails, results from the other available source can still be returned. If all configured sources fail, the backend returns an error response.

## What I Learned

This project helped me practice:

- Building REST APIs with Spring Boot
- Connecting React with a Java backend
- Working with DTOs and service layers
- Constructor dependency injection
- Parsing HTML using Jsoup
- Integrating multiple external data sources
- Handling failures from external services
- CORS
- Async requests with `fetch`
- React state management
- Loading and error states
- Responsive frontend design

## Future Improvements

- Add more meme sources
- Improve search relevance
- Add pagination
- Add caching
- Add automated tests
- Improve production logging

## Deployment

The application is deployed on Render.

Frontend: React application deployed as a Render Static Site

Backend: Spring Boot application deployed as a Dockerized Render Web Service

API Configuration: Vite environment variables are used to configure the production backend URL

CORS: Configured to allow communication between the deployed React frontend and Spring Boot backend

Note: The backend is hosted on Render's free instance. The first request after a period of inactivity may take some time while the backend service starts.
## Live Demo

**Frontend:**  
https://meme-finder-frontend.onrender.com

**Backend API:**  
https://meme-finder-r3d9.onrender.com

**Example API Request:**
```text
https://meme-finder-r3d9.onrender.com/api/memes/search?query=java
'''
