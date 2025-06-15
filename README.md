# Apekama Project

This repository contains a very small demo implementation of the Apekama
community platform.  It shows a React front‑end talking to a Spring Boot
back‑end with an in‑memory H2 database.  The example includes communities with a
few posts and comments seeded on start up.

## Structure

```
frontend/  # React single page application
backend/   # Spring Boot REST API
```

## Running

1. **Backend**

   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. **Frontend**

   ```bash
   cd frontend
   npm install
   npm start
   ```

The React dev server proxies API requests to the Spring Boot backend running on
port `8080`.

## Demo Features

The backend seeds a couple of communities and posts.  Visiting the frontend will
display the community list and posts.  Clicking a community loads its posts.

### Searching Posts

You can search posts across all communities using the search box at the top of
the page. This sends a request to `/api/posts/search?q=term` and displays any
matching posts by title or content.
